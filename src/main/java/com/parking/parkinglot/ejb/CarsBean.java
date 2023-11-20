package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG= Logger.getLogger(CarsBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<CarDto> copyCarsToDtO( List<Car> cars){
        List<CarDto> carsDto=new ArrayList<CarDto>();
        for (Car c: cars) {
            CarDto cdt= new CarDto(c.getId(),c.getLicensePlate(),c.getParkingSpot(), c.getOwner().getUsername());
            carsDto.add(cdt);
        }
        return carsDto;
    }

    public List<CarDto> findAllCars(){
        LOG.info("find all cars");
        try{
            TypedQuery<Car> typedQuery=entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars= typedQuery.getResultList();
            return copyCarsToDtO(cars) ;
        }
        catch (Exception ex){
            throw new EJBException(ex);
        }
    }
}
