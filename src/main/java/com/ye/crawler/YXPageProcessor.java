package com.ye.crawler;

import com.ye.crawler.pojo.Car;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;

public class YXPageProcessor implements PageProcessor {
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        Elements cars = document.select(".ab_carlist ul li");
        ArrayList<Car> carList = new ArrayList<Car>();
//        System.out.println(cars.size());//一页40个
        for (Element car : cars) {
            Element img = car.select(".across>a>img").get(0);
            String imgUrl = img.attr("data-original");
            String title = img.attr("alt");
            String price = car.select(".pad>p>em").get(0).text();
            String text = car.select(".pad>span").get(0).text();
            String priceRel = price.replaceAll("万", "").trim();
            BigDecimal priceB = new BigDecimal(priceRel);
            String[] split = text.split("｜");
            String buyDate = split[0].trim();
            String km = split[1].trim();

            Car car1 = new Car();
            car1.setBuyDate(buyDate);
            car1.setImgUrl(imgUrl);
            car1.setKm(km);
            car1.setPrice(priceB);
            car1.setTitle(title);
            car1.setSource("优信");
            carList.add(car1);//

        }
//        System.out.println(carList.toString());
        page.putField("carList",carList);

        //获取当前页  单线程 慢
        /*String currPage = document.select(".page-current").get(0).text();
        if(!"20".equals(currPage)){
            //继续爬取下一页
            String nextUrl = page.getHtml().css(".page-current+a").links().get();
            page.addTargetRequest(nextUrl);
        }*/
    }

    private Site site = Site.me()
            .setTimeOut(5000)
            .addHeader("cookie","RELEASE_KEY=; acw_tc=b7e89cab16048495535791086e7f41d41a1b5f1d5c1ee3324327449ba8; acw_sc__v2=5fa80f918353ba29633c8ceb64953493286826c0; XIN_anti_uid=015A7E89-389D-500F-6903-2E27EE790A02; XIN_LOCATION_CITY=%7B%22cityid%22%3A%22501%22%2C%22cityname%22%3A%22%5Cu5e7f%5Cu5dde%22%2C%22ename%22%3A%22guangzhou%22%2C%22service%22%3A%221%22%2C%22zhigou%22%3A%221%22%2C%22is_visit%22%3A%221%22%2C%22city_rank%22%3A%221%22%2C%22is_gold_partner%22%3A%22-1%22%2C%22direct_rent_support%22%3A%221%22%2C%22is_wz_mortgage%22%3A%221%22%7D; uid=CvQxuV+oD5JShAAVGsnIAg==; XIN_UID_CK=b4f17097-c4e9-9a22-33ae-0e27cda74c67; SEO_SOURCE=https://www.xin.com/; SEO_REF=https://www.xin.com/; Hm_lvt_ae57612a280420ca44598b857c8a9712=1604849555; Hm_lpvt_ae57612a280420ca44598b857c8a9712=1604849555; SERVERID=0e053b057d2244a83e6d26f4478a028f|1604849555|1604849553; session_xin=rp9j9m0169amq2o8nj2harqv0v9bj2de")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
    public Site getSite() {
        return site;
    }

}
