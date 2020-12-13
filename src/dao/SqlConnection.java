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
/*
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
    */
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

    /*
    public List<Dealer> SearchDealer() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("resources/configuration.xml"));
        SqlSession ss = factory.openSession(true);
        return ss.selectList("dealerSearch");
    }


    public List<Customer> SearchCustomer()	throws IOException{
    	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    	SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("resources/configuration.xml"));
    	SqlSession ss = factory.openSession(true);
    	return ss.selectList("customerSearch");
    }

     */
}
