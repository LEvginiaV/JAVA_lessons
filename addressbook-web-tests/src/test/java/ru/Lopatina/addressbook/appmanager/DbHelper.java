package ru.Lopatina.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.Contacts;
import ru.Lopatina.addressbook.model.GroupData;
import ru.Lopatina.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }
/*
  public GroupData groupById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where deprecated = '0000-00-00' and id = '" + id + "'").list();
    GroupData test = result.iterator().next();
    session.getTransaction().commit();
    session.close();
    return test;
  }
*/
  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
}
