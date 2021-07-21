package com.kpsec.test.repository;

import com.kpsec.test.model.entity.Account;
import com.kpsec.test.model.result.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "(SELECT FORMATDATETIME(a.deal_date,'y') as year, b.account_name as accountName, a.account_no as accountNo, SUM(a.amount - a.fees) as sumAmt\n" +
            "FROM account_history as a \n" +
            "JOIN account as b ON a.account_no = b.account_no\n" +
            "WHERE a.deal_cancel = 'N' and FORMATDATETIME(a.deal_date,'y') = '2018' \n" +
            "GROUP BY a.account_no, year\n" +
            "ORDER BY sumAmt DESC limit 1)" +
            "UNION" +
            "(SELECT FORMATDATETIME(a.deal_date,'y') as year, b.account_name as accountName, a.account_no as accountNo, SUM(a.amount - a.fees) as sumAmt\n" +
            "FROM account_history as a \n" +
            "JOIN account as b ON a.account_no = b.account_no\n" +
            "WHERE a.deal_cancel = 'N' and FORMATDATETIME(a.deal_date,'y') = '2019' \n" +
            "GROUP BY a.account_no, year\n" +
            "ORDER BY sumAmt DESC limit 1)", nativeQuery = true)
    List<UserAmountSumResult> getUserAmountSumFind();

    @Query(value = "(SELECT '2018' as year, a.account_no as accountNo, a.account_name as accountName  from account as a LEFT OUTER JOIN \n" +
            "(SELECT a.account_no FROM account as a JOIN account_history as b ON a.account_no = b.account_no \n" +
            "WHERE FORMATDATETIME(b.deal_date, 'y') = '2018' \n" +
            "GROUP BY a.account_no ) as c ON a.account_no = c.account_no WHERE c.account_no IS NULL ) \n" +
            "UNION \n" +
            "(SELECT '2019' as year, a.account_no as accountNo, a.account_name as accountName  from account as a LEFT OUTER JOIN \n" +
            "(SELECT a.account_no FROM account as a JOIN account_history as b ON a.account_no = b.account_no \n" +
            "WHERE FORMATDATETIME(b.deal_date, 'y') = '2019' \n" +
            "GROUP BY a.account_no ) as c ON a.account_no = c.account_no WHERE c.account_no IS NULL )" ,nativeQuery = true)
    List<UserDealNoResult> getUserDealNoFind();

    @Query(value = "SELECT FORMATDATETIME(b.deal_date, 'y') as year, c.branch_name as branchName, c.branch_code as branchCode,\n" +
            "SUM(b.amount - b.fees) as sumAmt\n" +
            "FROM account as a JOIN account_history as b ON a.account_no = b.account_no\n" +
            "JOIN bank_branch c ON a.branch_code = c.branch_code \n" +
            "WHERE b.deal_cancel = 'N'\n" +
            "GROUP BY FORMATDATETIME(b.deal_date, 'y'), c.branch_name, c.branch_code\n" +
            "ORDER BY year, sumAmt DESC", nativeQuery = true)
    List<BranchAmtSumResult> getBranchAmtSumFind();

    @Query(value = "select c.branch_name as branchName, c.branch_code as branchCode, SUM(b.amount - b.fees) as sumAmt\n" +
            "FROM  (SELECT account_no, (CASE WHEN branch_code = 'B' THEN 'A' ELSE branch_code END) AS branch_code FROM account) as a\n" +
            "JOIN account_history as b ON a.account_no = b.account_no\n" +
            "JOIN bank_branch as c ON a.branch_code = c.branch_code\n" +
            "WHERE c.branch_name = :brName AND b.deal_cancel = 'N'\n" +
            "GROUP BY branchName, branchCode", nativeQuery = true)
    BranchNameAmtSumResult getBranchNameAmtSumFind(@Param("brName") String brName);

}
