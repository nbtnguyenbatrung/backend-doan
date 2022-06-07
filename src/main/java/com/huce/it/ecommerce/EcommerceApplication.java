package com.huce.it.ecommerce;

import com.huce.it.ecommerce.layer.application.domain.entity.Account;
import com.huce.it.ecommerce.layer.application.domain.model.dto.AccountDto;
import com.huce.it.ecommerce.layer.application.domain.service.IAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
public class EcommerceApplication {
//    private final IAccountService iAccountService;
//
//    public EcommerceApplication(IAccountService iAccountService) {
//        this.iAccountService = iAccountService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        AccountDto account = new AccountDto();
//        account.setName("admin");
//        account.setCreateDate(new Timestamp(System.currentTimeMillis()));
//        account.setPassword("123456");
//        account.setRole("admin");
//        account.setPhoneNumber("1 k");
//        account.setStatus(1);
//        account.setEmail("admin@gmail.com");
//        iAccountService.createAccount(account);
//    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    private  Date now = new Date();
//    @Bean
//    CommandLineRunner run(IAccountService iAccountService) {
//        return args -> {
//            iAccountService.createAccount(
//                    new AccountDto(null, "admin" , "0012000" , "1" ,
//                            "admin@gmail.com" , 1 , "admin" , new Timestamp(now.getTime()) ));
//        };
//    }
}
