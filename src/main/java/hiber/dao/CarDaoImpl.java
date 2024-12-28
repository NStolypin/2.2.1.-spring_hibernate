package hiber.dao;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hiber.model.Car;
import hiber.model.User;

@Repository
public class CarDaoImpl implements CarDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getOwner(String model, int series) {
        Session session = sessionFactory.getCurrentSession();
        Optional<Car> carO = session.createQuery("FROM Car WHERE model=:model", Car.class)
                .setParameter("model", model).getResultList().stream().findAny();

        return carO.isPresent() ? carO.get().getUser() : null;
    }

    @Override
    public void add(Car car) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(car);
    }
}
