package com.lethanh98.demo.promises;

@SuppressWarnings("all")
public class PromisesFake {

    public static void main(String[] args) {
        PromisesFakeObj fakeObj = new PromisesFakeObj();
        fakeObj.then(s -> {
            System.out.println("Thành công : " + s);
        });
        fakeObj.error(s -> {
            System.out.println("Lỗi : " + s);
        });
        new Thread(() -> {
            fakeObj.resolve("OK đúng data r");
        }).start();
    }

    public static interface SetupPromises {
        void runCallBack(Action action);


    }

    public static interface Action {
        void resolve(Object var1);

        void resolve();

        void reject(Object var1);

        void reject();
    }

    public static interface CallBack<T> {
        public void runCallBack(T t);
    }

    static class PromisesFakeObj {
        CallBack<Object> done;
        CallBack<Object> error;

        void then(CallBack<Object> done) {
            this.done = done;
        }

        void error(CallBack<Object> error) {
            this.error = error;
        }

        void resolve(Object data) {
            done.runCallBack(data);
        }

        void error(Object data) {
            error.runCallBack(data);
        }

    }
}
