package org.example;

import org.example.database.DatabaseConnection;
import org.example.enums.MenuEnum;
import org.example.util.MenuUtil;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        while (true){
            MenuUtil.showMenuItems();
            System.out.println("Secin: ");
            int option = sc.nextInt();
            MenuEnum.getEnumByValue(option).getStrategy().execute();
        }



    }
}