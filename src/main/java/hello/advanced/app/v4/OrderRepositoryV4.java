package hello.advanced.app.v4;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.logtrace.logtrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

  private final logtrace trace;
  
  public void save( String itemId){
    AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
        @Override
        protected Void call() {
          if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
              }
            sleep(1000);
          return null;
        }
      };

      template.excute("OrderRepositoryV4.orderItem()");
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
