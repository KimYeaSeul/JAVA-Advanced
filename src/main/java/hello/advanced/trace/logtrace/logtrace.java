package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;

public interface logtrace {

  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);
}
