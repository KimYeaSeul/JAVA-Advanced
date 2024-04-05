package hello.advanced.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.logtrace;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
  private final OrderServiceV3 orderService;
  private final logtrace trace;

  @GetMapping("v3/request")
  public String getMethodName(String itemId) {

    TraceStatus status = null;
    try {
      // 함수마다 begin을 다 해서 trace 아이디가 함수마다 다 다름
      status = trace.begin("OrderController.request()");
      orderService.orderItem(itemId);
      trace.end(status); // ex 발생하면 로그가 출력이 안됨 -> try-catch로 변경
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; // 예외를 꼭 다시 던저주어야 한다. 안던지면 예외를 먹어버리고 정상흐름으로 동작
    }
  }
}
