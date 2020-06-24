package com.jerry.gcroot;

public class GCRootStaticVariable {
    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;
    private static GCRootStaticVariable staticVar;

    public GCRootStaticVariable(int size){
        memory = new byte[size];
    }

    public static void main(String[] args) {
        System.out.println("start:");
        printMemory();
        GCRootStaticVariable g = new GCRootStaticVariable(4 * _10MB);
        g.staticVar = new GCRootStaticVariable(8 *_10MB);
        g = null; //gc时可以回收此对象内存 40M
        System.gc();
        System.out.println("GC完成");
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
