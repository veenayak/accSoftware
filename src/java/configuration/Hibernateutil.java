/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pojo.Accounts_pojo;
import pojo.Activity_pojo;
import pojo.Category_pojo;
import pojo.Collections_pojo;
import pojo.Contact_pojo;
import pojo.Items_pojo;
import pojo.Login_pojo;
import pojo.Purchases_pojo;
import pojo.Sales_pojo;
import pojo.Size_pojo;
import pojo.Subcategory_pojo;
import pojo.Transaction_pojo;
import pojo.Unit_pojo;
import pojo.Warehouse_pojo;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author winayak
 */
public class Hibernateutil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static final ThreadLocal<Session> threadLocal;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure().addAnnotatedClass(Accounts_pojo.class).addAnnotatedClass(Activity_pojo.class)
                    .addAnnotatedClass(Category_pojo.class).addAnnotatedClass(Collections_pojo.class)
                    .addAnnotatedClass(Contact_pojo.class).addAnnotatedClass(Items_pojo.class)
                    .addAnnotatedClass(Login_pojo.class).addAnnotatedClass(Purchases_pojo.class)
                    .addAnnotatedClass(Sales_pojo.class).addAnnotatedClass(Size_pojo.class)
                    .addAnnotatedClass(Subcategory_pojo.class).addAnnotatedClass(Transaction_pojo.class)
                    .addAnnotatedClass(Unit_pojo.class).addAnnotatedClass(Warehouse_pojo.class);
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            threadLocal = new ThreadLocal<Session>();

        } catch(Throwable t){
            t.printStackTrace();
            throw new ExceptionInInitializerError(t);
        }
    }
    public static Session getSession() {
        Session session = threadLocal.get();
        if(session == null){
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = threadLocal.get();
        if(session != null){
            session.close();
            threadLocal.set(null);
        }
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
      }
}
