package com.kpsec.test.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountHistoryPK implements Serializable {
    private String accountNo;
    private String dealDate;
    private String dealNo;
}
