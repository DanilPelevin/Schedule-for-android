package com.example.schedule;

/*Проблемма решение которой нашлось только таким способом.*/
/* 奇妙な何か */


import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class ParserTableRevision {
    public static ArrayList<ArrayList<String>> base(Document doc, ArrayList<ArrayList<String>> table) {
        ArrayList<ArrayList<String>> tablefin = new ArrayList<ArrayList<String>>();
        int x = 0;
        for (int i = table.size()-1; i > -1; i--) {
            ArrayList<String> stringfin = new ArrayList<String>();
            for (int j = 0; j < table.get(i).size(); j++) {
                stringfin = table.get(i);
                String str = table.get(i).get(j);
                if (str.length() > 1) {
                    String substr = str.substring(0, 2);
                    String check = "td";
                    if (substr.equals(check)) {
                        String number = str.substring(3, 4);
                        int numbe = Integer.parseInt(number);

                            String str2 = table.get(i).get(j).substring(5);
                            stringfin.set(j,str2);
                            for (int t = 1 ; t < numbe ;t++){
                                int xt = x-t;
                                tablefin.get(xt).add(j,str2);
                            }

                    }
                }
            }
            x++;
            tablefin.add(stringfin);
        }
        return table;
    }
}



/* Some things may come in handy someday. */
