package tn.esprit.coexist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.coexist.entity.Product;
import tn.esprit.coexist.entity.User;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
