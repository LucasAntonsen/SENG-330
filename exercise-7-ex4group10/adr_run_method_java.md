run()
https://www.javatpoint.com/java-thread-run-method
- only works on runnable object
- when called executes code in run()
- callable multiple times
- can call via start() or calling run()
- problematic when calling run()

https://stackoverflow.com/questions/3489543/how-to-call-a-method-with-a-separate-thread-in-java
- need to create class that implements runnable interface
- create new thread passing constructor of runnable instance
- start tells JVM to create new thread and call run method in that new thread
- can be implemented using lambda expression
- can be implemented using anonymous classes
- must pass argument through object

https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
- runnable(task)
- no arguments in run method
- can use executor service with run method rather than a thread to accomplish same thing

https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
- can create a subclass of thread and implement run its own run method in the subclass

https://www3.ntu.edu.sg/home/ehchua/programming/java/j5e_multithreading.html
- invoked implicitly (not explicitly)
- start method puts thread into runnable state

https://www.tutorialspoint.com/java/lang/thread_run.htm
- does nothing and returns if thread was not constructed with runnable object

https://beginnersbook.com/2015/03/why-dont-we-call-run-method-directly-why-call-start-method/
- when start is called a thread then a new thread is allocated for the execution of the run method

https://medium.com/javarevisited/java-concurrency-thread-methods-54d12545c825
- if you call run() in main it will operate in main thread rather than in a separate thread

Threads
https://www.techbeamers.com/java-multithreading-with-examples/#pros
- can create threads via extend
- or implement runnable interface
- 8 constructors
- Thread() - thread object with default name
- Thread(String name) - Thread object with name from argument
- Thread(Runnable target) - constructs thread parameter with runnable object that defines run()
- Thread (Runnable target, String name) - same as 2 above combined
- Thread(ThreadGroup group, Runnable target) - creates thread object with runnable object and group it belongs to
- Thread (ThreadGroup group, Runnable target, String name) - see above
- Thread(ThreadGroup group, String name) - see above
- Thread(ThreadGroup group, Runnable target, String name, long stackSize) - see above + indicates size of
method-call stack
- sleep(long ms) used to suspend thread
- boolean isAlive() used to check if thread running
- join(long ms) thread waits for another to terminate
- types of threads
- user threads - perform critical tasks that must finish before application ends
- daemon - garbage collection/background jobs
- When starting thread finish, JVM sees if more threads operating. JVM doesn't stop if other threads running
- else JVM does not run daemon thread and ends

