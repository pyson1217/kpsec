package com.kpsec.test.repository;

import com.kpsec.test.model.entity.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, String> {
}
