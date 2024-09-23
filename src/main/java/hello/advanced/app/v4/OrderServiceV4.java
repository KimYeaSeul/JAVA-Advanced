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
  }
}
