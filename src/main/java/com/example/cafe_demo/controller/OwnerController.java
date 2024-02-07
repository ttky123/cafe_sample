package com.example.cafe_demo.controller;



import com.example.cafe_demo.entity.Owner;
import com.example.cafe_demo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/register")
    public Owner registerOwner(@RequestBody Owner owner) {
        return ownerService.registerOwner(owner.getPhoneNumber(), owner.getPassword());
    }

    // 로그인 및 로그아웃 엔드포인트 구현 필요
}