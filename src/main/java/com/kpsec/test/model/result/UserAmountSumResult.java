package com.kpsec.test.model.result;

import io.swagger.annotations.ApiModelProperty;

public interface UserAmountSumResult {
    @ApiModelProperty(position = 1, example = "2018")
    int getYear();
    @ApiModelProperty(position = 2, example = "pyson")
    String getAccountName();
    @ApiModelProperty(position = 3, example = "\"1111103\"")
    String getAccountNo();
    @ApiModelProperty(position = 4, example = "1000000")
    int getSumAmt();
}
