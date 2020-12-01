package dao;

import dto.Dealer;
import dto.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class SqlConnection {

    public SqlConnection() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("resources/configuration.xml"));
        SqlSession ss = factory.openSession(true);
        List<Dealer> dealerInfo = ss.selectList("dealerSearch");
        dealerInfo.forEach(o->{
            System.out.println(o.getWebid());
        });
        List<Customer> customerInfo = ss.selectList("customerSearch");
        customerInfo.forEach(o->{
        System.out.println(o.getId());
        });
    }
}
