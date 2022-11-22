import entity.Order;
import entity.OrderItem;
import entity.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.beginTransaction();

        Product p1 = new Product("Farba czerwona", 25.3f);
        Product p2 = new Product("Zaprawa murarska", 50.0f);
        session.persist(p1);
        session.persist(p2);

        OrderItem oI1 = new OrderItem(p1,3);
        OrderItem oI2 = new OrderItem(p2,1);
        session.persist(oI1);
        session.persist(oI2);

        Order o1 = new Order("Moj nowy order");
        session.persist(o1);
        o1.addItem(oI2);
        o1.addItem(oI1);
        o1.addItem(oI1);

        o1.addItem(p1,1);
        System.out.println(oI1);

        Product p3 = new Product("Klej wikol", 13.0f);
        session.persist(p3);
        o1.addItem(p3,2);

        txn.commit();
        session.close();

//        session = HibernateUtil.getSessionFactory().openSession();
//        txn = session.beginTransaction();
//        Order oFromDB = (Order) session.get(Order.class,1L);
//        System.out.println(oFromDB);
//        txn.commit();
//        session.close();

    }
}