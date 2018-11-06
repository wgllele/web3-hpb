package com.hpb.web3.abi.datatypes.generated;

import java.util.List;
import com.hpb.web3.abi.datatypes.StaticArray;
import com.hpb.web3.abi.datatypes.Type;


public class StaticArray30<T extends Type> extends StaticArray<T> {
    public StaticArray30(List<T> values) {
        super(30, values);
    }

    @SafeVarargs
    public StaticArray30(T... values) {
        super(30, values);
    }
}
