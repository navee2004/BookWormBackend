package com.springproject.demo.controller;

import com.springproject.demo.model.ProductBook;
import com.springproject.demo.service.ProductBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductBookController {

    @Autowired
    private ProductBookService productBookService;

    @GetMapping
    public List<ProductBook> getAllProducts() {
        return productBookService.getAllProductBooks();
    }

    @PostMapping
    public ResponseEntity<ProductBook> addProduct(@RequestBody ProductBook productBook) {
        ProductBook newProduct = productBookService.addProductBook(productBook);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBook> getProductById(@PathVariable Long id) {
        ProductBook product = productBookService.getProductBookById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBook> updateProduct(@RequestBody ProductBook productBook, @PathVariable Long id) {
        ProductBook updatedProduct = productBookService.updateProductBook(productBook, id);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productBookService.deleteProductBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<ProductBook> getBookByTitle(@PathVariable String title) {
        ProductBook book = productBookService.findByTitle(title);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
