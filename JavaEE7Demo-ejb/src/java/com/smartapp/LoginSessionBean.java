/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartapp;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Purushotham
 */
@Stateless
@LocalBean
public class LoginSessionBean {

    @PersistenceContext
    EntityManager entityManager;
    
    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean login(final String userName, final String password) {
        TypedQuery<User> typedQuery = entityManager.
                createNamedQuery("User.findByUserAndPassword",User.class)
                .setParameter("userName", userName)
                .setParameter("password", password);
        List<User> resultList = typedQuery.getResultList();
        return resultList.size()>0?true:false;
    }
    
    public void create(User user)
    {
        entityManager.persist(user);
    }
    public List<User> userList()
    {
        return entityManager.createNamedQuery("User.findAll").getResultList();
    }
    
    public User updateUser(User user)
    {
        int count = entityManager.createQuery("UPDATE User SET userName=:userName , password=:passwd WHERE userId=:uid")
                .setParameter("userName", user.getUserName())
                .setParameter("passwd", user.getPassword())
                .executeUpdate();
        System.out.println("Records Updated:"+count);
        return entityManager.find(User.class, user.getUserId());
    }
}