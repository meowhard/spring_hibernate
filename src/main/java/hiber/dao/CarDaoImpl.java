package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl  implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(Car car) {
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(
                "select u from User u left join u.car as c where c.model = '" + car.getModel() + "' and c.series = " + car.getSeries());
//                "select c.user from Car c inner join c.user where c.model = " + car.getModel() + " and c.series = " + car.getSeries(), User.class);
        return query.getSingleResult();
    }
}
