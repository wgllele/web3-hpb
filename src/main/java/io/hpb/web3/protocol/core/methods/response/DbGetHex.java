package io.hpb.web3.protocol.core.methods.response;

import io.hpb.web3.protocol.core.Response;


public class DbGetHex extends Response<String> {

    public String getStoredValue() {
        return getResult();
    }
}
