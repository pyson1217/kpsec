package com.kpsec.test.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RequestJson {
    @ApiModelProperty(example = "관리점명", required = true)
    private String brName;

    public RequestJson(String brName) {
        this.brName = brName;
    }
}
