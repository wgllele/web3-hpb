package com.hpb.web3.protocol.core.filters;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.hpb.web3.protocol.Web3;
import com.hpb.web3.protocol.core.Request;
import com.hpb.web3.protocol.core.methods.response.HpbFilter;
import com.hpb.web3.protocol.core.methods.response.HpbLog;


public class PendingTransactionFilter extends Filter<String> {

    public PendingTransactionFilter(Web3 web3, Callback<String> callback) {
        super(web3, callback);
    }

    @Override
    HpbFilter sendRequest() throws IOException {
        return web3.hpbNewPendingTransactionFilter().send();
    }

    @Override
    void process(List<HpbLog.LogResult> logResults) {
        for (HpbLog.LogResult logResult : logResults) {
            if (logResult instanceof HpbLog.Hash) {
                String blockHash = ((HpbLog.Hash) logResult).get();
                callback.onEvent(blockHash);
            } else {
                throw new FilterException(
                        "Unexpected result type: " + logResult.get() + ", required Hash");
            }
        }
    }

    
    @Override
    protected Optional<Request<?, HpbLog>> getFilterLogs(BigInteger filterId) {
        return Optional.empty();
    }
}
