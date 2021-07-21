package com.kpsec.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(AccountHistoryPK.class)
public class AccountHistory {

    @Id
    @Column(name = "DEAL_DATE")
    private String dealDate;

    @Id
    @Column(name = "ACCOUNT_NO")
    private String accountNo;

    @Id
    @Column(name = "DEAL_NO")
    private String dealNo;

    private int amount;

    private int fees;

    private String dealCancel;

}
