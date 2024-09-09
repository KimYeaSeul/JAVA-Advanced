package hello.advanced.app.v4;

import org.springframework.stereotype.Service;

import hello.advanced.trace.logtrace.logtrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
  private final OrderRepositoryV4 orderRepository;
  private final logtrace trace;

  public void orderItem(String itemId) {

  AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
        @Override
        protected Void call() {
          orderRepository.save(itemId);
          return null;
        }
      };

      template.excute("OrderServiceV4.orderItem()");
    // TraceStatus status = null;
    // try {
    //   status = trace.begin("OrderServiceV4.orderItem()");
    //   orderRepository.save(itemId);
    //   trace.end(status); // ex 발생하면 로그가 출력이 안됨 -> try-catch로 변경
    // } catch (Exception e) {
    //   trace.exception(status, e);
    //   throw e; // 예외를 꼭 다시 던저주어야 한다. 안던지면 예외를 먹어버리고 정상흐름으로 동작
    // }
  }
}
