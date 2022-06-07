package com.huce.it.ecommerce.layer.presentation.controller;

import com.huce.it.ecommerce.layer.application.domain.model.dto.AccountDto;
import com.huce.it.ecommerce.layer.application.domain.service.IAccountService;
import com.huce.it.ecommerce.unitity.response.Response;
import com.huce.it.ecommerce.unitity.response.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huce/admin/account")
public class AccountController {
    private final IAccountService iAccountService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @GetMapping("/list")
    public Response getListAccount(@RequestParam(name = "limit", defaultValue = "10", required = false) Integer limit,
                                   @RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
        try {
            return ResponseFactory.getSuccessResponse(Response.SUCCESS, iAccountService.getAccounts(limit, page));

        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }


    @PostMapping("/create")
    public Response createAccount(AccountDto accountDto) {
        try {
            iAccountService.createAccount(accountDto);
            return ResponseFactory.getSuccessResponse(Response.SUCCESS);
        } catch (Exception exception) {
            return ResponseFactory.getClientErrorResponse(exception.getMessage());
        }
    }
}
