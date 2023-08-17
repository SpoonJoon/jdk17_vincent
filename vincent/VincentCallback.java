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
    try {
      // Load the java.lang.Thread class with reflection
      Class<?> cls = Class.forName("java.lang.Thread");

      // Specify the parameters types for the printVincentLogger method (no parameters in this case).
      Class<?>[] parameterTypes = {};

      // Retrieve the printVincentLogger method.
      Method m = cls.getDeclaredMethod("printVincentLogger", parameterTypes);

      // Create and start a new Thread.
      Thread myThread = new Thread() {
          @Override
          public void run() {
              try {
                  // Invoke the printVincentLogger method from the Thread's instance.
                  m.invoke(this, (Object[]) null);
              } catch (IllegalAccessException | InvocationTargetException e) {
                  e.printStackTrace();
              }
          }
      };

      // Start the thread and invoke the native method.
      myThread.start();
      myThread.join();

  } catch (Exception e) {
      e.printStackTrace();
  }

      super.complete(benchmark, valid);
      System.err.println("Example callback " + (valid ? "PASSED " : "FAILED ") + (isWarmup() ? "warmup " : "") + benchmark);
      System.err.flush();
     
  };
}