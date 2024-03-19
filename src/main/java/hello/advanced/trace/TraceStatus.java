package hello.advanced.trace;

import lombok.Getter;

@Getter
public class TraceStatus {

  private Traceid traceId;
  private Long startTimeMs; // 메서드 실행 시간 구할 때 필요
  private String message;

  public TraceStatus(Traceid traceId, Long startTimeMs, String message) {
    this.traceId = traceId;
    this.startTimeMs = startTimeMs;
    this.message = message;
  }
  
}
