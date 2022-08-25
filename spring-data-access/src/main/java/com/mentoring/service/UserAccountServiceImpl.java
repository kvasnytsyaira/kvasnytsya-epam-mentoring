package com.mentoring.service;

import com.mentoring.dto.UserAccountDTO;
import com.mentoring.exception.RecordNotFoundException;
import com.mentoring.model.User;
import com.mentoring.model.UserAccount;
import com.mentoring.repository.UserAccountRepository;
import com.mentoring.utills.MainUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserServiceImpl userService;
    private final MainUtil util;

    @Override
    public UserAccountDTO createUserAccount(UserAccountDTO userAccount) {
        UserAccount account = util.convertDtoToEntity(userAccount);
        User userById = userService.getUserById(userAccount.getUserId());
        account.setUser(userById);
        UserAccount saved = userAccountRepository.save(account);
        return util.convertEntityToDto(saved);
    }

    @Override
    public void refillAccount(UserAccountDTO userAccount) {
        UserAccount account = userAccountRepository.findById(userAccount.getId())
                .orElseThrow(() -> new RecordNotFoundException("No user account with id: " + userAccount.getUserId()));
        account.setWallet(account.getWallet() + userAccount.getWallet());
        userAccountRepository.save(account);
    }

    @Override
    public UserAccountDTO getUserAccountById(long accountId) {
        return util.convertEntityToDto(userAccountRepository.findById(accountId)
                .orElseThrow(() -> new RecordNotFoundException("No user account with id: " + accountId)));
    }
}
