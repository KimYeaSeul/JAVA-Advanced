package hello.advanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.logtrace;

@Configuration
public class LogTraceConfig {

  @Bean
  logtrace logTrace(){
    return new FieldLogTrace();
  }
}
