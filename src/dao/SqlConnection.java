package dao;

import dto.Customer;
import dto.Dealer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class SqlConnection {

    public List<Customer> SearchCustomer() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("resources/configuration.xml"));
        SqlSession ss = factory.openSession(true);
        CustomerMapper customerMapper = ss.getMapper(CustomerMapper.class);
        return customerMapper.customerSearch();

    }

    public List<Dealer> SearchDealer() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("resources/configuration.xml"));
        SqlSession ss = factory.openSession(true);
        DealerMapper dealerMapper = ss.getMapper(DealerMapper.class);
        return dealerMapper.dealerSearch();

    }

}
