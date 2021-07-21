package com.kpsec.test.model.result;

import io.swagger.annotations.ApiModelProperty;

public interface UserDealNoResult {
    @ApiModelProperty(position = 1, example = "2018")
    int getYear();
    @ApiModelProperty(position = 2, example = "pyson")
    String getAccountName();
    @ApiModelProperty(position = 3, example = "\"1111103\"")
    String getAccountNo();
}
