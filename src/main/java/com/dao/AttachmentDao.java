package com.dao;

import com.entity.Attachment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AttachmentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Long insert(Attachment attachment) {
        Long id = -1L;
        Session session = sessionFactory.getCurrentSession();

        try {
            id = (Long) session.save(attachment);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();

        return id;
    }

    public void insertBulk(List<Attachment> attachments) {
        Session session = sessionFactory.getCurrentSession();
        try {
            attachments.forEach(session::save);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.flush();
    }
}
