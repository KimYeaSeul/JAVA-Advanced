package hello.advanced.trace.hellotrace;

import org.springframework.stereotype.Component;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.Traceid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class HelloTraceV2 {
  /*
   * v2
   * 함수마다 traceId 가 다른것을 보완.
   */
  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX= "<X-";

  public TraceStatus begin(String message){
    Traceid traceId = new Traceid();
    Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  // traceId는 유지, Level은 증가
  public TraceStatus beginSync(Traceid beforeTraceId, String message){
    // Traceid traceId = new Traceid();
    Traceid nextId = beforeTraceId.createNextId();
    Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX, nextId.getLevel()), message);
    return new TraceStatus(nextId, startTimeMs, message);
  }

  // 로그 정상 종료 시
  public void end(TraceStatus status){
    complete(status, null);
  }

  // 로그 예외 상황 종료 시
  public void exception(TraceStatus status, Exception e){
    complete(status, e);
  }

  private void complete(TraceStatus status, Exception e){
    Long stopTImeMs = System.currentTimeMillis();
    long resultTimeMs = stopTImeMs - status.getStartTimeMs();
    Traceid traceId = status.getTraceId();
    if(e == null){
      log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
    }else{
      log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
    }
  }
  private static String addSpace(String prefix, int level) {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<level; i++){
      sb.append(i == level - 1 ? "|"+prefix : "|  ");
    }
    return sb.toString();
  }
}
