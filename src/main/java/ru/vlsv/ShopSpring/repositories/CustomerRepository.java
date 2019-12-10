package ru.vlsv.ShopSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vlsv.ShopSpring.entities.Customer;

import java.util.List;
import java.util.Optional;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select new Customer(c.id, c.name) from Customer c")
    List<Customer> findAllWithoutPurchases();

    @Query("select new Customer(c.id, c.name) from Customer c where c.id = :id")
    Optional<Customer> findByIdWithoutPurchases(@Param("id") Long id);

//    @Query("select distinct c " +
//            "from Customer c " +
//            "left join fetch c.purchases p " +
//            "where c.id = :id")
//    Optional<Customer> findByIdWithPurchases(@Param("id") Long id);
}
