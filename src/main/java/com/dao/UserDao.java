package com.dao;

import com.entity.Location;
import com.entity.Status;
import com.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void store(User user){
        Session session = sessionFactory.getCurrentSession();
        try{
            session.saveOrUpdate(user);
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();
    }
    public List<User> getAll(){
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT l FROM User l", User.class);
//                .createQuery("SELECT FROM User",User.class); -> for this line get error why?
        List<User> userList = query.list();
        return userList;
    }
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(user);
//            id = status.getId();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        session.delete(user);
        session.flush();
    }
    public User getById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
}
