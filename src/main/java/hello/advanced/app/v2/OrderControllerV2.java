package hello.advanced.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
private final OrderServiceV2 orderService;
  private final HelloTraceV2 trace;

  @GetMapping("v2/request")
  public String getMethodName(String itemId) {

    TraceStatus status = null;
    try {
      // 함수마다 begin을 다 해서 trace 아이디가 함수마다 다 다름
      status = trace.begin("OrderController.request()");
      orderService.orderItem(status.getTraceId(), itemId);
      trace.end(status); // ex 발생하면 로그가 출력이 안됨 -> try-catch로 변경
      return "ok";
    } catch (Exception e) {
      trace.exception(status, e);
      throw e; // 예외를 꼭 다시 던저주어야 한다. 안던지면 예외를 먹어버리고 정상흐름으로 동작
    }
  }
}
