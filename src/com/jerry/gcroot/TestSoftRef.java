package com.jerry.gcroot;

import java.lang.ref.SoftReference;

/**
 * vm args: -Xms200M
 */
public class TestSoftRef {
    static class SoftObject {
        byte[] data = new byte[120 * 1024 * 1024];
    }

    public static void main(String[] args) {
        GCRootThread.printMemory();
        SoftReference<SoftObject> cache = new SoftReference<>(new SoftObject());
        System.out.println("第一次GC前 软引用："+ cache.get());
//        System.gc();
        System.out.println("第一次GC后 软引用："+ cache.get());
        SoftObject soft = new SoftObject();
        System.out.println("再非配一次 软引用："+ cache.get());
        GCRootThread.printMemory();
    }
}
