package com.mentoring.service;

import com.mentoring.dto.UserAccountDTO;

public interface UserAccountService {
    UserAccountDTO createUserAccount(UserAccountDTO userAccount);

    public void refillAccount(UserAccountDTO userAccount);

    UserAccountDTO getUserAccountById(long accountId);
}
