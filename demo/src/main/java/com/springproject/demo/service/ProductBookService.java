package com.springproject.demo.service;

import com.springproject.demo.model.ProductBook;
import com.springproject.demo.repository.ProductBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBookService {

    @Autowired
    private ProductBookRepository productBookRepository;

    public List<ProductBook> getAllProductBooks() {
        return productBookRepository.findAll();
    }

    public ProductBook addProductBook(ProductBook productBook) {
        return productBookRepository.save(productBook);
    }

    public ProductBook getProductBookById(Long id) {
        Optional<ProductBook> productBookOptional = productBookRepository.findById(id);
        return productBookOptional.orElse(null);
    }

    public ProductBook updateProductBook(ProductBook productBook, Long id) {
        Optional<ProductBook> productBookOptional = productBookRepository.findById(id);
        if (productBookOptional.isPresent()) {
            productBook.setBookId(id);
            return productBookRepository.save(productBook);
        } else {
            return null;
        }
    }

    public void deleteProductBook(Long id) {
        productBookRepository.deleteById(id);
    }
    public ProductBook findByTitle(String title) {
        return productBookRepository.findByTitle(title);
    }
}
