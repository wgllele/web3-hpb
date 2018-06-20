package com.hpb.web3.tx;

import java.io.IOException;
import java.math.BigInteger;

import com.hpb.web3.crypto.Credentials;
import com.hpb.web3.protocol.Web3;
import com.hpb.web3.tx.response.TransactionReceiptProcessor;


public class FastRawTransactionManager extends RawTransactionManager {

    private volatile BigInteger nonce = BigInteger.valueOf(-1);

    public FastRawTransactionManager(Web3 web3, Credentials credentials, byte chainId) {
        super(web3, credentials, chainId);
    }

    public FastRawTransactionManager(Web3 web3, Credentials credentials) {
        super(web3, credentials);
    }

    public FastRawTransactionManager(
            Web3 web3, Credentials credentials,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(web3, credentials, ChainId.NONE, transactionReceiptProcessor);
    }

    public FastRawTransactionManager(
            Web3 web3, Credentials credentials, byte chainId,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(web3, credentials, chainId, transactionReceiptProcessor);
    }

    @Override
    protected synchronized BigInteger getNonce() throws IOException {
        if (nonce.signum() == -1) {
                        nonce = super.getNonce();
        } else {
            nonce = nonce.add(BigInteger.ONE);
        }
        return nonce;
    }

    public BigInteger getCurrentNonce() {
        return nonce;
    }

    public synchronized void resetNonce() throws IOException {
        nonce = super.getNonce();
    }

    public synchronized void setNonce(BigInteger value) {
        nonce = value;
    }
}
