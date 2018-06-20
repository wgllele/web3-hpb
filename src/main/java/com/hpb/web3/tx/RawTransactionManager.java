package com.hpb.web3.tx;

import java.io.IOException;
import java.math.BigInteger;

import com.hpb.web3.crypto.Credentials;
import com.hpb.web3.crypto.RawTransaction;
import com.hpb.web3.crypto.TransactionEncoder;
import com.hpb.web3.protocol.Web3;
import com.hpb.web3.protocol.core.DefaultBlockParameterName;
import com.hpb.web3.protocol.core.methods.response.HpbGetTransactionCount;
import com.hpb.web3.protocol.core.methods.response.HpbSendTransaction;
import com.hpb.web3.tx.response.TransactionReceiptProcessor;
import com.hpb.web3.utils.Numeric;


public class RawTransactionManager extends TransactionManager {

    private final Web3 web3;
    final Credentials credentials;

    private final byte chainId;

    public RawTransactionManager(Web3 web3, Credentials credentials, byte chainId) {
        super(web3, credentials.getAddress());

        this.web3 = web3;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(
            Web3 web3, Credentials credentials, byte chainId,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(transactionReceiptProcessor, credentials.getAddress());

        this.web3 = web3;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(
            Web3 web3, Credentials credentials, byte chainId, int attempts, long sleepDuration) {
        super(web3, attempts, sleepDuration, credentials.getAddress());

        this.web3 = web3;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(Web3 web3, Credentials credentials) {
        this(web3, credentials, ChainId.NONE);
    }

    public RawTransactionManager(
            Web3 web3, Credentials credentials, int attempts, int sleepDuration) {
        this(web3, credentials, ChainId.NONE, attempts, sleepDuration);
    }

    protected BigInteger getNonce() throws IOException {
        HpbGetTransactionCount hpbGetTransactionCount = web3.hpbGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.PENDING).send();

        return hpbGetTransactionCount.getTransactionCount();
    }

    @Override
    public HpbSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value) throws IOException {

        BigInteger nonce = getNonce();

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                to,
                value,
                data);

        return signAndSend(rawTransaction);
    }

    public HpbSendTransaction signAndSend(RawTransaction rawTransaction)
            throws IOException {

        byte[] signedMessage;

        if (chainId > ChainId.NONE) {
            signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
        } else {
            signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        }

        String hexValue = Numeric.toHexString(signedMessage);

        return web3.hpbSendRawTransaction(hexValue).send();
    }
}