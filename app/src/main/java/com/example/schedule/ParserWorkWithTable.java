package com.example.schedule;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.ArrayList;


public class ParserWorkWithTable {

    public static ArrayList<ArrayList<String>> basik(Document doc) {
        Elements table = doc.select("table");
        Elements trs = table.select("tr");
        ArrayList<ArrayList<String>> taba = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            ArrayList<String> stringg = new ArrayList<String>();
            for (int j = 0; j < tds.size(); j++) {
                Element temporaryelement = tds.get(j);
                if (temporaryelement.hasAttr("rowspan") && temporaryelement.hasAttr("colspan")){
                    Element row = temporaryelement;
                    int rowspan = Integer.valueOf(row.attr("rowspan"));
                    String temporaryline = tds.get(j).text();
                    String  td = "td "+ rowspan + " ";
                    temporaryelement.removeAttr("rowspan");
                    Element col = temporaryelement;
                    int colspan = Integer.valueOf(col.attr("colspan"));
                    for (int t = 0; t < colspan; t++) {
                        stringg.add(td.concat(temporaryline));
                    }
                    temporaryelement.removeAttr("colspan");
                }else if (temporaryelement.hasAttr("rowspan")){
                    Element row = temporaryelement;
                    int rowspan = Integer.valueOf(row.attr("rowspan"));
                    String temporaryline = tds.get(j).text();
                    if (rowspan + i > trs.size()){
                        rowspan= rowspan-(rowspan+i-trs.size());
                    }
                    String  td = "td "+ rowspan + " ";
                    stringg.add(td.concat(temporaryline));
                    temporaryelement.removeAttr("rowspan");
                }else if (temporaryelement.hasAttr("colspan")) {
                    Element col = temporaryelement;
                    int colspan = Integer.valueOf(col.attr("colspan"));
                    String temporaryline = tds.get(j).text();
                    for (int t = 0; t < colspan; t++) {
                        stringg.add(temporaryline);
                    }
                    temporaryelement.removeAttr("colspan");
                } else stringg.add(temporaryelement.text());
            }
            taba.add(stringg);
        }
        ArrayList<ArrayList<String>> ttable = new ArrayList<ArrayList<String>>();
        ttable = ParserTableRevision.base(doc,taba);
        return ttable;
    }
}
