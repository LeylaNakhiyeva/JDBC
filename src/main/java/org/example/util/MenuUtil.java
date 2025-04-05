package org.example.util;

import org.example.enums.MenuEnum;

import java.util.Arrays;

public class MenuUtil {
    public static void showMenuItems(){
        Arrays.stream(MenuEnum.values()).forEach(menuEnum -> {
            System.out.println(menuEnum.getValue()+"."+menuEnum.getDescription());
        });
    }
}
