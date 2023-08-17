# Vincent Implementation in OpenJDK17

## Vincent Command Line Flag
The Vincent flag is a boolean production flag indicating the usage of Vincent (energy profiling for methods)

hotspot/shared/runtime/arguments.cpp & globals.hpp
```
   ./build/*/images/jdk/bin/java -XX:+Vincent -XX:+PrintVMOptions -jar dacapo-9.12-MR1-bach.jar antlr
```
to access flags try
```
#include "runtime/flags/jvmFlag.hpp"

JVMFlag* flag = JVMFlag::find_flag("Vincent");  
bool flagValue;  // Variable to store the retrieved flag value

// Call the get() function with the appropriate template arguments
JVMFlag::Error result = JVMFlag::get<bool, JVMFlag::TYPE_bool>(flag, &flagValue);
}
```

----

## OpenJDK thread

----

## Useful for codespaces
continue an unexpectedly terminated container
```
docker start  `docker ps -q -l`
```

---
## OpenJDK Hotspot src
* src/cpu: This directory contains CPU-specific code optimizations and platform-specific implementations. It includes subdirectories for different processor architectures, such as x86, ARM, SPARC, and PowerPC.
* src/share/: This is the main directory for HotSpot source code, containing many critical components:
   * classfile: Code related to class loading, verification, and manipulation.
   * compiler: The HotSpot JIT compiler, which dynamically compiles Java bytecode to native machine code for improved performance.
   * gc: Implementation of garbage collectors, including generational and concurrent collectors.
   * interpreter: The bytecode interpreter, which executes Java bytecode directly.
   * memory: Memory management-related code, including allocation, deallocation, and memory models.
   * oops: Definitions and operations related to ordinary object pointers (OOPs).
   * runtime: Core runtime services and support code, such as thread management, synchronization, exception handling, and runtime data structures.
   * services: Additional services provided by the JVM, such as debugging, profiling, and monitoring.
   * utilities: Various utility classes used throughout the HotSpot codebase.

## Downloading Dacapo
```
wget https://sourceforge.net/projects/dacapobench/files/latest/download -0 <desired_filename>
```

## Java Flight Recorder
```
./build/*/images/jdk/bin/java -XX:+Vincent -XX:+PrintVMOptions -XX:+FlightRecorder -XX:StartFlightRecording=filename=myrecording.jfr -jar <Benchmark>
```