package com.example.cafe_demo.controller;

import com.example.cafe_demo.entity.Product;
import com.example.cafe_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 상품 등록
    @PostMapping("/{phoneNumber}")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, @PathVariable String phoneNumber) {
        Product createdProduct = productService.createProduct(product, phoneNumber);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // 상품 부분 수정
    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    // 상품 리스트 조회 - Cursor 기반 페이지네이션 적용
    @GetMapping()
    public ResponseEntity<List<Product>> getProductsByOwnerIdWithCursor(@RequestParam String phoneNumber,
                                                                        @RequestParam(required = false) Long lastProductId,
                                                                        @RequestParam(defaultValue = "10") int size) {
        List<Product> products = productService.getProductsByOwnerphoneNumberWithCursor(phoneNumber, lastProductId, size);
        return ResponseEntity.ok(products);
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByOwnerAndNameOrInitial(@RequestParam String phoneNumber, @RequestParam String search) {
        List<Product> products = productService.search(phoneNumber, search);
        return ResponseEntity.ok(products);
    }
}
