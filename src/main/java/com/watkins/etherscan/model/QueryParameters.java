package com.watkins.etherscan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryParameters {

    private String module;
    private String action;
    private String address;
    private String contractAddress;
    private String txHash;
    private String timeStamp;
    private String startDate;
    private String endDate;
    private String closest;
    private String blockNo;
    private String tag;
    private String startBlock;
    private String endBlock;
    private String to;
    private String value;
    private String gas;
    private String gasPrice;
    private String apiKey;
}
