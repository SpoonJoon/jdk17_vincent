package vincent;

import org.dacapo.harness.Callback;
import org.dacapo.harness.CommandLineArgs;

import java.lang.reflect.*;

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

    Thread myThread = new Thread() {
      @Override
      public void run() {
          printVincentLogger();
       }
    };

  // Start the thread
  myThread.start();

  // Wait for the thread to finish
  try {
      myThread.join();
  } catch (InterruptedException e) {
      e.printStackTrace();
  }
      super.complete(benchmark, valid);
      System.err.println("Example callback " + (valid ? "PASSED " : "FAILED ") + (isWarmup() ? "warmup " : "") + benchmark);
      System.err.flush();
  
  };
}