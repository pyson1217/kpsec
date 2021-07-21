package com.kpsec.test.contoller;

import com.kpsec.test.model.result.BranchAmtSumResult;
import com.kpsec.test.model.result.UserDealNoResult;
import com.kpsec.test.model.result.UserAmountSumResult;
import com.kpsec.test.model.result.BranchNameAmtSumResult;
import com.kpsec.test.service.AccountService;
import com.kpsec.test.vo.RequestJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "과제")
@RestController
@RequestMapping("/assignment/")
public class AssignmentController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "문1) 연도별 합계 금액이 많은 고객 조회")
    @GetMapping(value = "/a1/user/amount/sum")
    public List<UserAmountSumResult> getUserAmountSum() {
        return accountService.getUserAmountSum();
    }

    @ApiOperation(value = "문2) 연도별 거래가 없는 고객 조회")
    @GetMapping(value = "/a2/user/deal/no")
    public List<UserDealNoResult> getUserDealNo() {
        return accountService.getUserDealNo();
    }

    @ApiOperation(value = "문3) 연도, 관리점별 거래금액 합계 조회")
    @GetMapping(value = "/a3/branch/amount/sum")
    public List<BranchAmtSumResult> getBranchAmtSum() {
        return accountService.getBranchAmtSum();
    }

    @ApiOperation(value = "문4) 해당지점의 거래금 합계 출력 조회")
    @PostMapping(value = "/a4/branch/name/amount/sum", headers = {"Content-type=application/json"})
    public BranchNameAmtSumResult getBranchNameAmtSum(@RequestBody RequestJson brName) {
        return accountService.getBranchNameAmtSum(brName);
    }
}
