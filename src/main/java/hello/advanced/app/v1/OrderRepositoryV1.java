package hello.advanced.app.v1;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

  private final HelloTraceV1 trace;

  public void save(String itemId){
    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepositoryV1.save()");
      // 저장 로직
      if(itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생");
      }
      sleep(1000);

      trace.end(status); // ex 발생하면 로그가 출력이 안됨 -> try-catch로 변경
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; // 예외를 꼭 다시 던저주어야 한다. 안던지면 예외를 먹어버리고 정상흐름으로 동작
    }
    
    
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
}
