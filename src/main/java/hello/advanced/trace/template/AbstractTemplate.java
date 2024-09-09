package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.logtrace;

public abstract class AbstractTemplate<T> {

  private final logtrace trace;

  public AbstractTemplate(logtrace trace){
    this.trace = trace;
  }

  public T excute(String message){
    TraceStatus status = null;
    try{
      status = trace.begin(message);

      // call logic
      T result = call();

      trace.end(status);

      return result;
    }catch(Exception e){
      trace.exception(status, e);
      throw e;
    }
  }

  protected abstract T call();
}
