package com.lethanh98.demo.synchronous;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class DemoSynchronous {
    public static void main(String[] args) throws FileNotFoundException {
        // Với Synchronous luồng ứng dụng sẽ chạy từ trên xuống dưới, từng task sẽ hoàn thành trước khi task sau được chạy
        System.out.println("Start get data");
        InputStream is = DemoSynchronous.class.getClassLoader().getResourceAsStream("DataTest.txt");
        System.out.println("get ok data from file");

        Scanner myReader = new Scanner(is);

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            System.out.println(data);
        }
        System.out.println("End get and show data");

    }
}
