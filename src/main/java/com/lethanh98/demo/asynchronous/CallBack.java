package com.lethanh98.demo.asynchronous;

// Thực hiện tạo 1 callback asynchronous
public class CallBack {
    public static void main(String[] args) {
        System.out.println("Start app");

        TestCallBackAsync testCallBackAsync = new TestCallBackAsync();
        testCallBackAsync.runSumTest(5, 10, total -> {
            System.out.println("Callback total : " + total);
        });
        System.out.println("End app");
    }

    static interface OnCallBack {
        void callBack(int total);
    }

    static class TestCallBackAsync {
        void runSumTest(int a, int b, OnCallBack onCallBack) {
            // An Async task always executes in new thread
            new Thread(() -> {
                System.out.println("Run Task and Run CallBack");
                onCallBack.callBack(a + b);
            }).start();
        }
    }
}
