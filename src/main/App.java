package main;

import dto.Test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello project!");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("resources/configuration.xml"));
            SqlSession ss = factory.openSession(true);
            List<Test> xiangjingru = ss.selectList("dealerSearch");
            xiangjingru.forEach(o->{
                System.out.println(o.getId());
            });
    }
}
