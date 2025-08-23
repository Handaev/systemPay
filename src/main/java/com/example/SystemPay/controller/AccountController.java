package com.example.SystemPay.controller;


import com.example.SystemPay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;



}
