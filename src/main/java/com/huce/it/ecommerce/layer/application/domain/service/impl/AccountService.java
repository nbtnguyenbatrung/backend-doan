package com.huce.it.ecommerce.layer.application.domain.service.impl;

import com.huce.it.ecommerce.config.Constants;
import com.huce.it.ecommerce.layer.application.domain.dao.IAccountDao;
import com.huce.it.ecommerce.layer.application.domain.entity.Account;
import com.huce.it.ecommerce.layer.application.domain.model.dto.AccountDto;
import com.huce.it.ecommerce.layer.application.domain.service.IAccountService;
import com.huce.it.ecommerce.unitity.response.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional

public class AccountService implements IAccountService, UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IAccountDao iAccountDao;
    private final PasswordEncoder passwordEncoder;

    public AccountService(IAccountDao iAccountDao, PasswordEncoder passwordEncoder) {
        this.iAccountDao = iAccountDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createAccount(AccountDto accountDto) {
        Account account = Constants.SERIALIZER.convertValue(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        logger.info("save account " + account.getEmail());
        iAccountDao.save(account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return iAccountDao.findAccountByEmail(email);
    }

    @Override
    public ResultResponse<Account> getAccounts(Integer limit, Integer page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(0, 30, sort);
        Page<Account> accounts = iAccountDao.findAll(pageable);
        Long total = accounts.getTotalElements();
        ResultResponse<Account> resultResponse = new ResultResponse<>();
        resultResponse.setData(accounts.getContent());
        resultResponse.setLimit(limit);
        resultResponse.setPage(page);
        resultResponse.setTotal(total);
        resultResponse.setTotalPage((int) (total / limit));
        return resultResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = iAccountDao.findAccountByEmail(email);
        if (account == null) {
            logger.error("account not found in the database");
            throw new UsernameNotFoundException("account not found in the database");
        } else {
            logger.info("account found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole()));

        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
    }
}
