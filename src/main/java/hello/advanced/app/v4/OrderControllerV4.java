package hello.advanced.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.logtrace.logtrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
  private final OrderServiceV4 orderService;
  private final logtrace trace;

  @GetMapping("v4/request")
  public String getMethodName(String itemId) {

    AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
      @Override
      protected String call() {
        orderService.orderItem(itemId);
        return "ok";
      }
    };
    return template.excute("OrderController.request()");
  }
}
