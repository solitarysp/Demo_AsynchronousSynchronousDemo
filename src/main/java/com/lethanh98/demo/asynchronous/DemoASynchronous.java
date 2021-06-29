package com.lethanh98.demo.asynchronous;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")
public class DemoASynchronous {

    public static void main(String[] args) {
        System.out.println("Start app");
        DaoFakeALL.getUserId(1, dataUser -> {
            DaoFakeALL.findAddressById(dataUser.addressId, dataAddress -> {
                System.out.println("User Name: " + dataUser.name + " : Address: " + dataAddress.name);
            }, error -> {
                System.out.println("Get Address Id Error :" + error);
            });
        }, error -> {
            System.out.println("Get user Id Error :" + error);
        });

        DaoFakeALL.getUserId(2, dataUser -> {
            DaoFakeALL.findAddressById(dataUser.addressId, dataAddress -> {
                System.out.println("User Name: " + dataUser.name + " : Address: " + dataAddress.name);
            }, error -> {
                System.out.println("Get Address Id Error :" + error);
            });
        }, error -> {
            System.out.println("Get user Id Error :" + error);
        });
        System.out.println("End app");
    }

    static interface OnCallBack<T> {
        void callBack(T data);
    }

    @AllArgsConstructor
    static class User {
        int id;
        String name;
        int addressId;
    }

    @AllArgsConstructor
    static class Address {
        int id;
        String name;
    }

    static class DaoFakeALL {
        static void getAllUser(OnCallBack<List<User>> onCallBack) {
            new Thread(() -> {
                List<User> users = new ArrayList<>();
                users.add(new User(1, "Thành", 3));
                users.add(new User(2, "Nam", 2));
                users.add(new User(3, "hải", 1));

                onCallBack.callBack(users);
            }).start();
        }

        static void getUserId(int id, OnCallBack<User> onCallBack, OnCallBack<String> error) {
            new Thread(() -> {
                getAllUser(data -> {
                    new Thread(() -> {
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).id == id) {
                                System.out.println("Tìm thấy User id :" + id);
                                onCallBack.callBack(data.get(i));
                                return;
                            }
                        }
                        error.callBack("Không tìm thấy user bởi id");
                    }).start();
                });
            }).start();
        }

        static void getAllAddress(OnCallBack<List<Address>> onCallBack) {
            new Thread(() -> {
                List<Address> addresses = new ArrayList<>();
                addresses.add(new Address(1, "Hà Nội"));
                addresses.add(new Address(2, "Sai Gòn"));
                addresses.add(new Address(3, "Đà nẵng"));
                onCallBack.callBack(addresses);
            }).start();
        }

        static void findAddressById(int id, OnCallBack<Address> onCallBack, OnCallBack<String> error) {
            getAllAddress(data -> {
                new Thread(() -> {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).id == id) {
                            System.out.println("Tìm thấy Addres id :" + id);
                            onCallBack.callBack(data.get(i));
                            return;
                        }
                    }
                    error.callBack("Không tìm thấy address bởi id");
                }).start();
            });
        }
    }
}
