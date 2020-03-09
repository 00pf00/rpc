### Server端task任务的执行方式


#### 方案一：
&nbsp;&nbsp;放入线程池的阻塞队列中
#### 方法二：
&nbsp;&nbsp;调用线程池的submit方法执行，调用submit方法会返回Future对象，调用Future的cancel()
方法，可以停止正在运行的线程和取消在阻塞队列中的任务执行

| 测试场景  | 测试方法 | 测试结果  |
|:----------|:----------|:----------|
| ThreadPoolExecutor创建的线程池ExecutorService，<br>使用submit()执行Runnable类型的task任务，调用<br>返回值Future的cancel()取消任务的执行和停止正在<br>运行的线程| 重写ThreadPoolExecutor的beforeExecute方法在执行前输出task任务的状态        |可以停止正在运行的线程和取消task任务的执行。<br>但是Runnable的任务会封装为Futuretask的任务类型，<br>即使将Runnable类型的任务封装FutureTask的子类，在beforeExecutore中也无法将FutureTask强转为子类。<br>使用execute()方法就可以在beforeExecute()就可以将<br>FutureTask类型的任务强转为FutureTask的子类 |
| 线程池只创建一个线程，将task任务<br>放入阻塞队列,线程池是否会自动创建<br>核心线程数的线程执行任务    |    | 线程池不会创建核心线程数的线程   |
|触发线程池创建最大线程数的线程的条件|   | 在阻塞队列已满的时候  |
|线程池核心线程数的线程停止执行后是否会自动创建||核心线程数的线程并没有销毁|
|线程池最大线程数的线程停止执行后是否会自动创建|||
|线程池的AbortPolicy的拒绝策略异常如何捕获||创建类实现RejectedExecutionHandler接口，对拒绝的任务进行处理|