package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.ContextV2;
import hello.advanced.trace.strategy.code.Strategy;
import hello.advanced.trace.strategy.code.StrategyLogic1;
import hello.advanced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2Test {

  @Test
  void strategtV1(){
    ContextV2 context = new ContextV2();
    context.execute(new StrategyLogic1());
    context.execute(new StrategyLogic2());
  }
/**
 * 익명 내부 클래스 사용
 */
  @Test
  void strategtV2(){
    ContextV2 context = new ContextV2();
    context.execute(new Strategy() {
      @Override
      public void call() {
        log.info("익명 클래스 비즈니스 로직 실행");
    }
    });
  }

  /**
 * 익명 내부 클래스 사용2
 */
@Test
void strategtV3(){
  ContextV2 context = new ContextV2();
  context.execute(() -> log.info("비즈니스 로직 실행 "));
}
}
