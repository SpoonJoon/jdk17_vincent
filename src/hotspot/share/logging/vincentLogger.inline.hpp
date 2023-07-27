#ifndef LOGGING_VINCENTLOGGER_VINCENTLOGGERINLINE_HPP_
#define LOGGING_VINCENTLOGGER_VINCENTLOGGERINLINE_HPP_

#include "logging/vincentLogger.hpp"
#include "utilities/lockFreeQueue.inline.hpp"
#include "runtime/atomic.hpp"

#include <cstdio>

VincentLogger::VincentLogger() {
  VincentLogEntry* dummy = new VincentLogEntry("");
  _head = dummy;
  _tail = dummy;
}

void VincentLogger::enqueue(const char* message) {
  VincentLogEntry* new_entry = new VincentLogEntry(message);
  VincentLogEntry* prev_tail = _tail;

  while (prev_tail != Atomic::xchg(&_tail, new_entry)) {
    prev_tail = _tail;
  }

  prev_tail->next = new_entry;
}

const char* VincentLogger::dequeue() {
  VincentLogEntry* head_val = _head;
  VincentLogEntry* new_head_val = head_val->next;

  if (new_head_val != NULL) {
    _head = new_head_val;
    const char* message = new_head_val->message;
    delete head_val;
    return message;
  }

  return NULL;
}

void VincentLogger::print_logger() {
  FILE* file = fopen("joon.csv", "a");  // Open the file in append mode
  if (file == NULL) {
    return;
  }

  const char* message = dequeue();
  while (message != NULL) {
    fputs(message, file);  // Write the log entry to the file
    fputs("\n", file);
    message = dequeue();
  }

  fclose(file);  // Close the file
}

#endif 