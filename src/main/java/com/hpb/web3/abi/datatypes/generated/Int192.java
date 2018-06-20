package com.hpb.web3.abi.datatypes.generated;

import java.math.BigInteger;

import com.hpb.web3.abi.datatypes.Int;


public class Int192 extends Int {
    public static final Int192 DEFAULT = new Int192(BigInteger.ZERO);

    public Int192(BigInteger value) {
        super(192, value);
    }

    public Int192(long value) {
        this(BigInteger.valueOf(value));
    }
}
