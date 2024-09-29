package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.logtrace;

public class TraceTemplate {

  private final logtrace trace;

  public TraceTemplate(logtrace trace){
    this.trace = trace;
  }

  public <T> T execute(String message, TraceCallback<T> callback){
    TraceStatus status = null;
    try{
      status = trace.begin(message);

      // call logic
      T result = callback.call();

      trace.end(status);

      return result;
    }catch(Exception e){
      trace.exception(status, e);
      throw e;
    }
  }
}
