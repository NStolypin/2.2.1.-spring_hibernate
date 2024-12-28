package hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiber.dao.CarDAO;
import hiber.model.Car;
import hiber.model.User;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Transactional
    @Override
    public void add(Car car) {
        carDAO.add(car);
    }

    @Transactional(readOnly = true)
    @Override
    public User getOwner(String model, int series) {
        return carDAO.getOwner(model, series);
    }

}
