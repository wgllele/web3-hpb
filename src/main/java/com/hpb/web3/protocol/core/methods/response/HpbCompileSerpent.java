package com.hpb.web3.protocol.core.methods.response;

import com.hpb.web3.protocol.core.Response;


public class HpbCompileSerpent extends Response<String> {
    public String getCompiledSourceCode() {
        return getResult();
    }
}
