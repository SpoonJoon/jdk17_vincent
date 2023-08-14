package vincent;

import org.dacapo.harness.Callback;
import org.dacapo.harness.CommandLineArgs;

public class VincentCallback extends Callback {

  public VincentCallback(CommandLineArgs args) {
    super(args);
  }

  /* Immediately prior to start of the benchmark */
  @Override
  public void start(String benchmark) {
    System.err.println("Example callback starting " + (isWarmup() ? "warmup " : "") + benchmark);
    super.start(benchmark);
  };

  /* Immediately after the end of the benchmark */
  @Override
  public void stop(long duration) {
    super.stop(duration);
    System.err.println("Example callback stopping " + (isWarmup() ? "warmup" : ""));
    System.err.flush();
  };

  @Override
  public void complete(String benchmark, boolean valid) {
    super.complete(benchmark, valid);
    System.err.println("Example callback " + (valid ? "PASSED " : "FAILED ") + (isWarmup() ? "warmup " : "") + benchmark + (requests > 0 ? (", observed "+requests+" requests") : ""));
    System.err.flush();
  };

  /**
   * The workload is about to start issuing requests (request-based workloads only).
   */
  public void requestsStarting() { /* your code here */ }

  /**
   * The workload has finished issuing requests (request-based workloads only).
   *
   */
  public void requestsFinished() { /* your code here */ }


  /* let's count the number of requests we observe */
  private int requests = 0;
  synchronized
  private void inc() {
    requests++;
  }

  /* Called immediately before each request begins (request-based workloads only) */
  @Override
  public void requestStart(int id) { inc(); /* your code here */ }

  /* Called immediately after each request ends (request-based workloads only) */
  @Override
  public void requestEnd(int id) { /* your code here */ }
}