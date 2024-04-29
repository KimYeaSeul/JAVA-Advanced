package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.Traceid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements logtrace{

  private static final String START_PREFIX = "-->";
  private static final String COMPLETE_PREFIX = "<--";
  private static final String EX_PREFIX= "<X-";

  // private Traceid traceIdHolder; // traceId 동기화, 동시성 이슈 발생
  private ThreadLocal<Traceid> traceIdHolder = new ThreadLocal<>(); // traceId 동기화, 동시성 이슈 발생
  @Override
  public TraceStatus begin(String message) {
    syncTraceId();
    Traceid traceId = traceIdHolder.get();
    Long startTimeMs = System.currentTimeMillis();
    log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
    return new TraceStatus(traceId, startTimeMs, message);
  }

  private void syncTraceId(){
    Traceid traceId = traceIdHolder.get();
    if(traceId == null){
      traceIdHolder.set(new Traceid());
    }else {
      traceIdHolder.set(traceId.createNextId());
    }
  }
  @Override
  public void end(TraceStatus status) {
    complete(status, null);
  }

  @Override
  public void exception(TraceStatus status, Exception e) {
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

    releaseTraceId();
  }

  private void releaseTraceId() {
    Traceid traceId = traceIdHolder.get();
    if (traceId.isFirstLevel() ){
      traceIdHolder.remove(); // destroy
    } else {
      traceIdHolder.set(traceId.createPreviousId());
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
