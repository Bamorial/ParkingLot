package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UsersDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG= Logger.getLogger(UsersBean.class.getName());
    @Inject
    PasswordBean passwordBean;
    @PersistenceContext
    EntityManager entityManager;
    public List<UsersDto> copyUsersToDtO(List<User> users){
        List<UsersDto> usersDto=new ArrayList<UsersDto>();
        for (User c: users) {
            UsersDto cdt= new UsersDto(c.getId(), c.getUsername(), c.getEmail());
            usersDto.add(cdt);
        }
        return usersDto;
    }

    public List<UsersDto> findAllusers(){
        LOG.info("find all users");
        try{
            TypedQuery<User> typedQuery=entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users= typedQuery.getResultList();
            return copyUsersToDtO(users) ;
        }
        catch (Exception ex){
            throw new EJBException(ex);
        }
    }
    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }
    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
}
