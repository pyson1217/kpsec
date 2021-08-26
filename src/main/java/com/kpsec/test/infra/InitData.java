package com.kpsec.test.infra;

import com.kpsec.test.model.entity.Account;
import com.kpsec.test.model.entity.AccountHistory;
import com.kpsec.test.model.entity.BankBranch;
import com.kpsec.test.repository.AccountBankBranchRepository;
import com.kpsec.test.repository.AccountHistoryRepository;
import com.kpsec.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitData {

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountBankBranchRepository accountBankBranchRepository;

    @PostConstruct
    private void initAccount() throws IOException {
        if (accountRepository.count() == 0) {
            try (InputStream resource = new ClassPathResource("계좌정보.csv").getInputStream()) {
                List<Account> accountList =
                        new BufferedReader(new InputStreamReader(resource,
                                StandardCharsets.UTF_8)).lines().collect(Collectors.toList())
                        .stream().skip(1).map(line -> {
                            String[] split = line.split(",");
                            return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                    .build();
                        }).collect(Collectors.toList());
                accountRepository.saveAll(accountList);
            }
        }
        if(accountHistoryRepository.count() == 0){
            try (InputStream resource = new ClassPathResource("거래내역.csv").getInputStream()) {
                List<AccountHistory> accountList =
                        new BufferedReader(new InputStreamReader(resource,
                                StandardCharsets.UTF_8)).lines().collect(Collectors.toList())
                                .stream().skip(1).map(line -> {
                            String[] split = line.split(",");
                            return AccountHistory.builder()
                                    .dealDate(split[0]).accountNo(split[1]).dealNo(split[2])
                                    .amount(Integer.parseInt(split[3])).fees(Integer.parseInt(split[4])).dealCancel(split[5])
                                    .build();
                        }).collect(Collectors.toList());
                accountHistoryRepository.saveAll(accountList);
            }
        }
        if(accountBankBranchRepository.count() == 0){
            try (InputStream resource = new ClassPathResource("관리점정보.csv").getInputStream()) {
                List<BankBranch> accountList =
                        new BufferedReader(new InputStreamReader(resource,
                                StandardCharsets.UTF_8)).lines().collect(Collectors.toList())
                                .stream().skip(1).map(line -> {
                            String[] split = line.split(",");
                            return BankBranch.builder()
                                    .branchCode(split[0]).branchName(split[1])
                                    .build();
                        }).collect(Collectors.toList());
                accountBankBranchRepository.saveAll(accountList);
            }
        }
    }
}
