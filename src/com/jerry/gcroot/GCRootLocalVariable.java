package com.jerry.gcroot;


public class GCRootLocalVariable {

    private int _10MB = 10 * 1024 *1024;
    private byte[] memory = new byte[8 * _10MB]; //80MB

    public static void main(String[] args) {
        System.out.println("start: ");
        printMemory();
        test();

        System.gc();
        System.out.println("第二次GC完成");
        printMemory();
    }

    /**
     * 虚拟机栈-局部变量引用的对象 作为gc root
     */
    public static void test(){
        //创建对象，占用80M内存
        GCRootLocalVariable local = new GCRootLocalVariable();
        System.gc();
        System.out.println("第一次GC完成");
        printMemory();
    }

    /**
     * 打印内存
     */
    public static void printMemory(){
        System.out.println("free is :"+Runtime.getRuntime().freeMemory()/1024/1024 +"M,");
        System.out.println("total is :"+Runtime.getRuntime().totalMemory()/1024/1024+"M,");
    }

}
