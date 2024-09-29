package hello.advanced.app.v5;

import org.springframework.stereotype.Service;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.logtrace;
@Service
public class OrderServiceV5 {
  private final OrderRepositoryV5 orderRepository;
  private final TraceTemplate traceTemplate;
  
  public OrderServiceV5(OrderRepositoryV5 repository, logtrace trace){
    this.orderRepository = repository;
    this.traceTemplate = new TraceTemplate(trace);
  }

  public void orderItem(String itemId) {
    traceTemplate.execute("OrderServiceV5.orderItem()", ()->{
        orderRepository.save(itemId);
        return null;
    });
  }
}
