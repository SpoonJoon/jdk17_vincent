#ifndef SHARE_VINCENTLOGGER_VINCENTLOGGER_HPP
#define SHARE_VINCENTLOGGER_VINCENTLOGGER_HPP

#include "memory/allocation.hpp"
#include "runtime/atomic.hpp"


//mtInternal look at allocation.hpp line 129
//we use the CHeap so the logger isnt managed by the gc
class VincentLogEntry : public CHeapObj<mtInternal> {
public:
  char* message;

  VincentLogEntry(const char* msg) {
    message = strdup(msg);
  }

  ~VincentLogEntry() {
    if (message) {
      free(message);
    }
  }
};

class VincentLogger : public CHeapObj<mtInternal> {
private:
  // Atomically store to a location
  Atomic<VincentLogEntry*> _head;
  Atomic<VincentLogEntry*> _tail;

public:
  VincentLogger();

  void enqueue(const char* message);
  const char* dequeue();

  //WARNING: This is not thread safe
  void print_logger();
};

#endif // SHARE_VincentLOGGER_VincentLOGGER_HPP