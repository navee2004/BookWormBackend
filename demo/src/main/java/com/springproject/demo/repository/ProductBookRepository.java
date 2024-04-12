package com.springproject.demo.repository;

import com.springproject.demo.model.ProductBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBookRepository extends JpaRepository<ProductBook, Long> {
    
    // Custom query to find books by their title
    @Query("SELECT pb FROM ProductBook pb WHERE pb.title = ?1")
    ProductBook findByTitle(String title);
}


