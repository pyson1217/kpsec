package com.kpsec.test.model.result;

import io.swagger.annotations.ApiModelProperty;

public interface BranchAmtSumResult {

    @ApiModelProperty(position = 1, example = "2018")
    int getYear();
    @ApiModelProperty(position = 2, example = "판교점")
    String getBranchName();
    @ApiModelProperty(position = 3, example = "AA")
    String getBranchCode();
    @ApiModelProperty(position = 4, example = "100000")
    int getSumAmt();
}
