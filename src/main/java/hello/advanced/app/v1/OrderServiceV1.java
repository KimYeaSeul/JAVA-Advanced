package hello.advanced.app.v1;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
  private final OrderRepositoryV1 orderRepository;
  private final HelloTraceV1 trace;

  public void orderItem(String itemId) {
    TraceStatus status = null;
    try {
      status = trace.begin("OrderServiceV1.orderItem()");
      orderRepository.save(itemId);
      trace.end(status); // ex 발생하면 로그가 출력이 안됨 -> try-catch로 변경
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; // 예외를 꼭 다시 던저주어야 한다. 안던지면 예외를 먹어버리고 정상흐름으로 동작
    }
  }
}
