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

public class GZPageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        Elements cars = document.select(".carlist li");
        ArrayList<Car> carList = new ArrayList<Car>();
        for (Element car : cars) {
            String imgUrl = car.select(">a>img").get(0).attr("src");
            String title = car.select(">a>h2").get(0).text();
            String price = car.select(".t-price>p").get(0).text().replaceAll("万", "").trim();
            BigDecimal priceB =  new BigDecimal(price);
            String strings = car.select(".t-i").get(0).text();
            String[] split = strings.split("\\|");
            String buyDate = split[0].trim();
            String km = split[1].trim();

            Car car1 = new Car();
            car1.setBuyDate(buyDate);
            car1.setImgUrl(imgUrl);
            car1.setKm(km);
            car1.setPrice(priceB);
            car1.setTitle(title);
            car1.setSource("瓜子");
            carList.add(car1);
        }
//        System.out.println(carList.toString());
//        System.out.println(carList.size());
        page.putField("carList",carList);
    }

    private Site site = Site.me()
            .setTimeOut(5000)
            .addHeader("cookie","antipas=55e53z7514577942X64098ZX1; uuid=69657b75-7f30-4080-ff20-5105f0c0b337; cityDomain=gz; clueSourceCode=%2A%2300; user_city_id=16; ganji_uuid=6020289018923368605172; sessionid=3653a7ef-59ad-4075-edc3-6a68717b4d92; lg=1; Hm_lvt_bf3ee5b290ce731c7a4ce7a617256354=1604851283; close_finance_popup=2020-11-09; cainfo=%7B%22ca_a%22%3A%22-%22%2C%22ca_b%22%3A%22-%22%2C%22ca_s%22%3A%22seo_baidu%22%2C%22ca_n%22%3A%22default%22%2C%22ca_medium%22%3A%22-%22%2C%22ca_term%22%3A%22-%22%2C%22ca_content%22%3A%22-%22%2C%22ca_campaign%22%3A%22-%22%2C%22ca_kw%22%3A%22-%22%2C%22ca_i%22%3A%22-%22%2C%22scode%22%3A%22-%22%2C%22keyword%22%3A%22-%22%2C%22ca_keywordid%22%3A%22-%22%2C%22display_finance_flag%22%3A%22-%22%2C%22platform%22%3A%221%22%2C%22version%22%3A1%2C%22client_ab%22%3A%22-%22%2C%22guid%22%3A%2269657b75-7f30-4080-ff20-5105f0c0b337%22%2C%22ca_city%22%3A%22gz%22%2C%22sessionid%22%3A%223653a7ef-59ad-4075-edc3-6a68717b4d92%22%7D; preTime=%7B%22last%22%3A1604851286%2C%22this%22%3A1604851279%2C%22pre%22%3A1604851279%7D; Hm_lpvt_bf3ee5b290ce731c7a4ce7a617256354=1604851286")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
    public Site getSite() {
        return site;
    }

}
