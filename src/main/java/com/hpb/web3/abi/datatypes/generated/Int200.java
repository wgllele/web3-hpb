package com.hpb.web3.abi.datatypes.generated;

import java.math.BigInteger;

import com.hpb.web3.abi.datatypes.Int;


public class Int200 extends Int {
    public static final Int200 DEFAULT = new Int200(BigInteger.ZERO);

    public Int200(BigInteger value) {
        super(200, value);
    }

    public Int200(long value) {
        this(BigInteger.valueOf(value));
    }
}
