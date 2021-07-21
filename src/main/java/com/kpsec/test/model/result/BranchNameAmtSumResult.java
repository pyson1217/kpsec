package com.kpsec.test.model.result;

import io.swagger.annotations.ApiModelProperty;

public interface BranchNameAmtSumResult {
    @ApiModelProperty(position = 1, example = "판교점")
    String getBranchName();
    @ApiModelProperty(position = 2, example = "AA")
    String getBranchCode();
    @ApiModelProperty(position = 3, example = "100000")
    int getSumAmt();

}
