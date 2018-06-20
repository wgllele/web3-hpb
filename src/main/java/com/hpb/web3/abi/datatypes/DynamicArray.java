package com.hpb.web3.abi.datatypes;

import java.util.List;


public class DynamicArray<T extends Type> extends Array<T> {

    @SafeVarargs
    public DynamicArray(T... values) {
        super(values[0].getTypeAsString() + "[]", values);
    }

    public DynamicArray(List<T> values) {
        super(values.get(0).getTypeAsString() + "[]", values);
    }

    private DynamicArray(String type) {
        super(type);
    }

    public static DynamicArray empty(String type) {
        return new DynamicArray(type);
    }
}
