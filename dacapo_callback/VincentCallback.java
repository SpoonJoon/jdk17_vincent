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
  public void stop(boolean w) {
    super.stop(w);
    System.err.println("Example callback stopping " + (isWarmup() ? "warmup" : ""));
    System.err.flush();
  };

  @Override
  public void complete(String benchmark, boolean valid) {
    super.complete(benchmark, valid);
    System.err.println("Example callback " + (valid ? "PASSED " : "FAILED ") + (isWarmup() ? "warmup " : "") + benchmark + (requests > 0 ? (", observed "+requests+" requests") : ""));
    System.err.flush();
  };
}