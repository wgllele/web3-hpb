package io.hpb.web3.protocol.core.methods.response;

import io.hpb.web3.protocol.core.Response;


public class HpbGetCode extends Response<String> {
    public String getCode() {
        return getResult();
    }
}
