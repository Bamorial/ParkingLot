package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UsersDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {
    private static final Logger LOG= Logger.getLogger(UsersBean.class.getName());
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
}
