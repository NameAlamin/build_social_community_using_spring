package com.dao;

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
public class StatusDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void store(Status status){
        Session session = sessionFactory.getCurrentSession();
        try{
            session.save(status);
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();
    }
    public List<Status> getAll(){
        Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT l FROM Status l", Status.class);
//                .createQuery("SELECT FROM User",User.class); -> for this line get error why?
        List<Status> statusList = query.list();
        return statusList;
    }
    public Status getById(Long id) {
        return sessionFactory.getCurrentSession().get(Status.class, id);
    }


    public void update(Status status) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(status);
//            id = status.getId();
        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Status status = session.load(Status.class, id);

        session.delete(status);
        session.flush();
    }
}
