package cc.notalk.infinityjava.jc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.UUID;
import java.util.concurrent.*;

/**
 *Java线程池的使用和原理分析
 *
 */

public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("NotalkccThread-%d").setDaemon(false).build());

        //Future<ThreadResult> future = service.submit(new Callable<ThreadResult>() {
        //
        //    public ThreadResult call() throws Exception {
        //        Thread.sleep(10000);
        //        System.out.println(">>>>>>>>>>>>>" + System.currentTimeMillis() + " run call");
        //        ThreadResult result = new ThreadResult();
        //        result.setId(UUID.randomUUID().toString());
        //        result.setThreadName(Thread.currentThread().getName() + " [" + Thread.currentThread().getId() + "]");
        //        result.setResultMsg("hi,this is call method from thread: [" + Thread.currentThread().getName() + "]");
        //        return result;
        //    }
        //});
        //try {
        //    ThreadResult threadResult = future.get();
        //    System.out.println(System.currentTimeMillis() + ">>>>>" + ":,result from " + threadResult.getThreadName() + ",result msg is :" + threadResult.getResultMsg());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}
        //future = service.submit(new Callable<ThreadResult>() {
        //
        //    public ThreadResult call() throws Exception {
        //        System.out.println(">>>>>>>>>>>>>" + System.currentTimeMillis() + " run call");
        //        ThreadResult result = new ThreadResult();
        //        result.setId(UUID.randomUUID().toString());
        //        result.setThreadName(Thread.currentThread().getName() + " [" + Thread.currentThread().getId() + "]");
        //        result.setResultMsg("hi,this is call method from thread: [" + Thread.currentThread().getName() + "]");
        //        return result;
        //    }
        //});
        //
        //try {
        //    ThreadResult threadResult = future.get();
        //    System.out.println(System.currentTimeMillis() + ">>>>>" + ":,result from " + threadResult.getThreadName() + ",result msg is :" + threadResult.getResultMsg());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}
        //future = service.submit(new Callable<ThreadResult>() {
        //
        //    public ThreadResult call() throws Exception {
        //        System.out.println(">>>>>>>>>>>>>" + System.currentTimeMillis() + " run call");
        //        ThreadResult result = new ThreadResult();
        //        result.setId(UUID.randomUUID().toString());
        //        result.setThreadName(Thread.currentThread().getName() + " [" + Thread.currentThread().getId() + "]");
        //        result.setResultMsg("hi,this is call method from thread: [" + Thread.currentThread().getName() + "]");
        //        return result;
        //    }
        //});
        //try {
        //    ThreadResult threadResult = future.get();
        //    System.out.println(System.currentTimeMillis() + ">>>>>" + threadResult.getId() + ":,result from " + threadResult.getThreadName() + ",result msg is :" + threadResult.getResultMsg());
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //} catch (ExecutionException e) {
        //    e.printStackTrace();
        //}


        service.submit(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(">>>>>>>>>>>>>" + System.currentTimeMillis() + " run at " + Thread.currentThread().getName());
            }

        });
        service.submit(new Runnable() {

            public void run() {
                System.out.println(">>>>>>>>>>>>>" + System.currentTimeMillis() + " run at " + Thread.currentThread().getName());
            }

        });
        service.submit(new Runnable() {

            public void run() {
                System.out.println(">>>>>>>>>>>>>" + System.currentTimeMillis() + " run at " + Thread.currentThread().getName());
            }

        });

        service.shutdown();
    }


}

class ThreadResult {
    private String id;
    private String threadName;
    private String resultMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}