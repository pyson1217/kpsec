package com.kpsec.test.repository;

import com.kpsec.test.model.entity.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBankBranchRepository extends JpaRepository<BankBranch, String> {
}
