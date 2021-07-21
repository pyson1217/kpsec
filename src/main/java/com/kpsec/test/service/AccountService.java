package com.kpsec.test.service;

import com.kpsec.test.exception.NotFoundException;
import com.kpsec.test.model.result.*;
import com.kpsec.test.repository.AccountRepository;
import com.kpsec.test.vo.RequestJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService{

    @Autowired
    private AccountRepository accountRepository;

    public List<UserAmountSumResult> getUserAmountSum() {
        return accountRepository.getUserAmountSumFind();
    }

    public List<UserDealNoResult> getUserDealNo() {
        return accountRepository.getUserDealNoFind();
    }

    public List<BranchAmtSumResult> getBranchAmtSum() {
        return accountRepository.getBranchAmtSumFind();
    }

    public BranchNameAmtSumResult getBranchNameAmtSum(RequestJson brName) {
        return Optional.ofNullable(accountRepository.getBranchNameAmtSumFind(brName.getBrName())).orElseThrow(NotFoundException::new);
    }
}
