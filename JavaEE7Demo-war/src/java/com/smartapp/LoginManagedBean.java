/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartapp;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Purushotham
 */
@Named(value = "loginManagedBean")
@RequestScoped
public class LoginManagedBean {

    @EJB
    LoginSessionBean loginBean;
    
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void loginHandler(ActionEvent event)
    {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
//        loginBean.updateUser(user);
        loginBean.create(user);
//        boolean login = loginBean.login(userName, password);
//        System.out.println("Login Status"+login);
    }
}
