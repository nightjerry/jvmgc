package com.jerry.gcroot;

public class GCRootThread {
    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 *_10MB];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start : ");
        printMemory();

        AsyncTask task = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(task);
        thread.start();
        System.gc();
        System.out.println("main方法执行完毕，完成GC");
        printMemory();

        thread.join();
        task = null;
        System.gc();
        System.out.println("线程代码执行完毕，完成GC");
        printMemory();
    }
    private static class AsyncTask implements Runnable{
        private GCRootThread gcRootThread;
        public AsyncTask(GCRootThread gcThread){
            this.gcRootThread = gcThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 打印内存
     */
    public static void printMemory(){
        System.out.println("free is :"+Runtime.getRuntime().freeMemory()/1024/1024 +"M,");
        System.out.println("total is :"+Runtime.getRuntime().totalMemory()/1024/1024+"M,");
    }
}
