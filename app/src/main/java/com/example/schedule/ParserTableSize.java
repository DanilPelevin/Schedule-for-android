package com.example.schedule;

/* В нём больше нет необходимости , так что надеюсь я не забуду его удалить когда придёт время. */
// TODO: 27.03.2020 Удали его!Когда-нибудь...

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserTableSize {
    public static int rowcount(Document doc, int trsi){
        Elements links = doc.select("table");
        for (Element table : links) {
            Elements trs = table.select("tr");
            for (int x = 0; x < trs.size(); x++) {
                trsi++;
            }
        }
        return trsi;
    }
    public static int columnscount(Document doc, int tdsi){
        Elements links = doc.select("table");
        for (Element table : links) {
            Elements trs = table.select("tr");
            Elements tds = trs.get(0).select("td");
            for (int y= 0; y < tds.size(); y++) {
                tdsi++;
            }
        }
        return tdsi;
    }
}
