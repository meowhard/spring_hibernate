package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

public interface CarService {
    void add(Car car);

    @Transactional
    User getUserByCar(Car car);
}
