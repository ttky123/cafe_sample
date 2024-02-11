package com.example.cafe_demo.repository.queryDsl;
// ProductRepositoryImpl.java

import com.example.cafe_demo.entity.Product;
import com.example.cafe_demo.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Product> findByOwnerPhoneNumberWithCursor(String phoneNumber, Long cursorId, int size) {
        QProduct product = QProduct.product;
        return jpaQueryFactory.selectFrom(product)
                .where(product.owner.phoneNumber.eq(phoneNumber)
                        .and(product.id.lt(cursorId)))
                .orderBy(product.id.desc())
                .limit(size)
                .fetch();
    }
    @Override
    public List<Product> findByOwnerPhoneNumberAndNameOrInitial(String phoneNumber, String search) {
        QProduct product = QProduct.product;
        return jpaQueryFactory
                .selectFrom(product)
                .where(
                        product.owner.phoneNumber.eq(phoneNumber)
                                .and(
                                        product.name.contains(search)
                                                .or(product.initialSound.contains(search))
                                )
                )
                .fetch();
    }
}