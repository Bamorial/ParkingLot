package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.CarPhotoDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.CarPhoto;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.jws.soap.SOAPBinding;
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
    public void createCar(String license, String spot, Long userId){
       LOG.info("create Car");
       Car car=new Car();
       car.setLicensePlate(license);
       car.setParkingSpot(spot);
       User user= entityManager.find(User.class, userId);
       user.getCars().add(car);
       car.setOwner(user);
       entityManager.persist(car);
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

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("Update Car");
        Car car= entityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User oldUser=car.getOwner();
        oldUser.getCars().remove(car);
        User user=entityManager.find(User.class,userId);
        user.getCars().add(car);
        car.setOwner(user);
    }

    public CarDto findById(Long carId) {
        LOG.info("find car");
        try{
            TypedQuery<Car> typedQuery=entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars= typedQuery.getResultList();
            for (Car c:cars
                 ) {
                if(c.getId()==carId) {
                    CarDto cdt= new CarDto(c.getId(),c.getLicensePlate(),c.getParkingSpot(), c.getOwner().getUsername());
                    return cdt;
                }
                else return null;

            }
        }
        catch (Exception ex){
            throw new EJBException(ex);

        }
        return null;
    }

    public void deleteCarsByIds(List<Long> carIds) {
        LOG.info("deleteCarsByIds");
        for(Long carId: carIds){
            Car car= entityManager.find(Car.class,carId);
            entityManager.remove(car);
        }
    }
    public void addPhotoToCar(Long carId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotoToCar");
        CarPhoto photo = new CarPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Car car = entityManager.find(Car.class, carId);
        if (car.getPhoto() != null) {
            entityManager.remove(car.getPhoto());
        }
        car.setPhoto(photo);
        photo.setCar(car);
        entityManager.persist(photo);
    }
    public CarPhotoDto findPhotoByCarId(Integer carId) {
        List<CarPhoto> photos = entityManager
                .createQuery("SELECT p FROM CarPhoto p where p.car.id = :id", CarPhoto.class)
                .setParameter("id", carId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        CarPhoto photo = photos.get(0); // the first element
        return new CarPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(),
                photo.getFileContent());
    }
}
