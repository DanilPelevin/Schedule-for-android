package com.example.schedule;

/*Временный вывод для проверки работоспособности.*/

import java.util.ArrayList;
import java.util.List;

public class ParserTestOutput {
    public static List<String> execute(ArrayList<ArrayList<String>> taba, String str, int number) {
        List<String> listt= new ArrayList<String>();
        for (int i=1;i < taba.size();i++){
            String strstr = null;
            if(str.equals(taba.get(i).get(0))){
                String srr1 = taba.get(i).get(1);
                String srr2 = taba.get(i).get(number);
                strstr = srr1 + " | " + srr2;
                listt.add(strstr);
            }
        }
        return listt;
    }
}
