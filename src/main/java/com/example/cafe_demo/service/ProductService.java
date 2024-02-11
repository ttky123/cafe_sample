package com.example.cafe_demo.service;
// ProductService.java

import com.example.cafe_demo.entity.Product;
import com.example.cafe_demo.repository.OwnerRepository;
import com.example.cafe_demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Product createProduct(Product product, String phoneNumber) {
        product.setInitialSound(extractInitialSound(product.getName()));
        product.setOwner(ownerRepository.findByPhoneNumber(phoneNumber).get());
        return productRepository.save(product);
    }


    public Product updateProduct(Long productId, Product productDetails) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setInitialSound(extractInitialSound(product.getName()));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());

        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Page<Product> getProductsByOwnerId(String phoneNumber, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findByOwnerPhoneNumber(phoneNumber, pageable);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    // 커서 기반 페이지네이션을 위한 메서드
    public List<Product> getProductsByOwnerphoneNumberWithCursor(String phoneNumber, Long lastProductId, int size) {
        if (lastProductId == null) {
            lastProductId = Long.MAX_VALUE;
        }
        return productRepository.findByOwnerPhoneNumberWithCursor(phoneNumber, lastProductId, size);
    }
    public List<Product> search(String phoneNumber, String query) {
        // query가 null이거나 빈 문자열인 경우, 모든 상품을 반환
        if (query == null || query.isEmpty()) {
                return getProductsByOwnerphoneNumberWithCursor(phoneNumber, Long.MAX_VALUE, 10);
        }

        return productRepository.findByOwnerPhoneNumberAndNameContainingOrInitialSoundContaining(phoneNumber, query, extractInitialSound(query));
    }
    private String extractInitialSound(String name) {
        log.info(name);
        StringBuilder initialSounds = new StringBuilder();

        // 초성 리스트. 00 ~ 18
        char[] initials = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};

        for (char ch : name.toCharArray()) {
            // 한글 유니코드 범위 내의 문자인지 체크
            if (ch >= '가' && ch <= '힣') {
                // 유니코드 상에서 '가'로부터의 거리를 계산하여 초성 인덱스 도출
                int index = (ch - '가') / 28 / 21;
                initialSounds.append(initials[index]);
            } else {
                initialSounds.append(ch);
            }
        }
        log.info(initialSounds.toString());
        return initialSounds.toString();
    }
}
