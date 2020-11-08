package com.ye.crawler;

import com.ye.crawler.pojo.Car;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CarPipeLine implements Pipeline {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Car> carList = resultItems.get("carList");
//        System.out.println(carList);
        try {
            String url = "jdbc:mysql://localhost:3306/crawler?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "root";
            //拿到数据库的连接
            Connection connection = DriverManager.getConnection(url, username, password);

            //创建运载sql 的工具
            StringBuilder sql = new StringBuilder("insert into car (title,price,buy_date,km,img_url,source) values ");
            for (Car car : carList) {
                sql.append("(");
                sql.append("'"+car.getTitle()+"',");
                sql.append(car.getPrice()+",");
                sql.append("'"+car.getBuyDate()+"',");
                sql.append("'"+car.getKm()+"',");
                sql.append("'"+car.getImgUrl()+"',");
                sql.append("'"+car.getSource()+"'");
                sql.append("),");
            }

            PreparedStatement statement = connection.prepareStatement(sql.toString().substring(0,sql.length()-1));
            //执行sql
            statement.execute();

            if(statement != null){
                statement.close();
            }

            if(connection != null){
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
