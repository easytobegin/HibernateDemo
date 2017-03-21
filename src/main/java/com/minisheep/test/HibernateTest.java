package com.minisheep.test;

import com.minisheep.domain.Customer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;


/**
 * Created by minisheep on 17/3/21.
 */
public class HibernateTest {
    private HibernateTest(){

    }

    private static final HibernateTest instance = new HibernateTest();

    public static HibernateTest getInstance(){
        return instance;
    }

    //保存一个Customer
    @Test
    public void saveCustomerTest(){
        //创建一个Customer
        Customer c = new Customer();
        c.setName("陈扬");
        c.setAddress("厦门");

        //使用Hibernate的Api来完成将Customer信息保存到mysql数据库中
        Configuration config = new Configuration().configure(); // Hibernate框架加载hibernate.cfg.xml文件
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();  //相当于得到一个connection

        //开启事务
        session.beginTransaction();

        //操作
        session.save(c);

        //事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    //根据id查询一个Customer对象
    @Test
    public void findCustomerByIdTest(){
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //开启事务
        session.beginTransaction();

        //根据具体业务编写代码
        Customer c = session.load(Customer.class,1);

        System.out.println(c.getName());

        //事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }


    //修改操作
    @Test
    public void updateCustomerTest(){
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //开启事务
        session.beginTransaction();

        //根据业务编写代码
        Customer c = session.get(Customer.class,1);
        c.setName("王王");
        session.update(c); //修改操作

        //事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    //删除操作---根据id进行删除
    @Test
    public void deleteCustomerTest(){
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //开启事务
        session.beginTransaction();

        //根据业务编写代码
        Customer c = session.get(Customer.class,1);
        session.delete(c);

        //事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    //查询所有Customer
    @Test
    public void findAllCustomerTest(){
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        //开启事务
        session.beginTransaction();

        //根据业务来编写代码
        Query query = session.createQuery("from Customer");
        List<Customer> list = query.list();
//        for(Customer cus : list){
//            System.out.println("ID:" + cus.getId() + " " + "姓名:" + cus.getName() + " " + "住址:" + cus.getAddress());
//        }
        //事务提交
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
