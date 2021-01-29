package ua.restaurant.service;

import org.springframework.context.i18n.LocaleContextHolder;
import ua.restaurant.entity.Dishes;

//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.persistence.Query;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class QueryTypes {
//    EntityManagerFactory emf;
//
//    public QueryTypes() {
//        Map properties = new HashMap();
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//        emf = Persistence.createEntityManagerFactory("jpa-query-types", properties);
//    }
//
//    private EntityManager getEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public List<Dishes> getAllDishes() {
//        Query jpqlQuery = getEntityManager().createQuery("SELECT id, :name, price, category FROM dishes");
//        jpqlQuery.setParameter("name", "name_"+ LocaleContextHolder.getLocaleContext().getLocale());
//        return (List<Dishes>) jpqlQuery.getSingleResult();
//    }
//
//}
