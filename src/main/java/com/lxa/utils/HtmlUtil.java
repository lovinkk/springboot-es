package com.lxa.utils;

import com.lxa.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlUtil {
    public static void main(String[] args) throws Exception {

//        new HtmlUtil().parseJD("手机");
//        String url = "https://uland.taobao.com/sem/tbsearch?keyword=iphone" ;
//
//        Document document = Jsoup.parse(new URL(url), 30000);
//
//        Element element = document.getElementById("J_u_root");
//        System.out.println(element.html());
//        Elements elements = element.getElementsByTag("li");
//
//        System.out.println(elements.html());

//        for (Element el : elements) {
//            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-collect");
//            System.out.println(img);
//        }
    }
    public List<Content> parseJD(String keyword) throws Exception {
        String url = "https://search.jd.com/Search?keyword=" + keyword  + "&enc=utf-8";

        Document document = Jsoup.parse(new URL(url), 30000);

        Element element = document.getElementById("J_goodsList");

        Elements elements = element.getElementsByTag("li");
        List<Content> goodList = new ArrayList<>();

        for(Element el : elements){
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content(title,img,price);
            goodList.add(content);
        }
        System.out.println(goodList.size());
        return goodList;
    }

    public List<Content> parseTB(String keyword) throws Exception {
        String url = "https://uland.taobao.com/sem/tbsearch?keyword=" + keyword  + "&enc=utf-8";

        Document document = Jsoup.parse(new URL(url), 30000);

        Element element = document.getElementById("pc-search-items-list");

        Elements elements = element.getElementsByTag("li");
        List<Content> goodList = new ArrayList<>();

        for(Element el : elements){
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-collect");
            String price = el.getElementsByClass("coupon-price-afterCoupon").eq(0).text();
            String title = el.getElementsByClass("pc-items-item-title pc-items-item-title-row2").eq(0).text();

            Content content = new Content(title,img,price);
            goodList.add(content);
        }
        return goodList;
    }
}
