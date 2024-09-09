package hello.advanced.trace.template;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateMethodTest {

  @Test
  void TemplateMethodV0(){
    logic1();
    logic2();
  }

  private void logic1(){
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info(" start business logic1 ");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;

    log.info("resultTime={}", resultTime);
  }

  private void logic2(){
    long startTime = System.currentTimeMillis();
    // 비즈니스 로직 실행
    log.info(" start business logic2 ");
    // 비즈니스 로직 종료
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;

    log.info("resultTime={}", resultTime);
  }

  // 템플릿 메서드 패턴 적용
  @Test
  void TemplateMethodV1() {
    AbstractTemplate template1 = new SubClassLogic1();
    template1.excute();

    AbstractTemplate template2 = new SubClassLogic2();
    template2.excute();
  }

  @Test
  void TemplateMethodV2(){
    AbstractTemplate t1 = new AbstractTemplate() {
      @Override
      protected void call() {
        // TODO Auto-generated method stub
        log.info("비즈니스 로직 1 실행");
      }
    };
    t1.excute();

    AbstractTemplate t2 = new AbstractTemplate() {
      @Override
      protected void call() {
        // TODO Auto-generated method stub
        log.info("비즈니스 로직 2 실행");
      }
    };
    t2.excute();
  }
}
