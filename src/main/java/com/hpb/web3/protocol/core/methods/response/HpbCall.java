package com.hpb.web3.protocol.core.methods.response;

import com.hpb.web3.protocol.core.Response;


public class HpbCall extends Response<String> {
    public String getValue() {
        return getResult();
    }
}