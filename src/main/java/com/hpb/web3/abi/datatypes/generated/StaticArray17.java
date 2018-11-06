package com.hpb.web3.abi.datatypes.generated;

import java.util.List;
import com.hpb.web3.abi.datatypes.StaticArray;
import com.hpb.web3.abi.datatypes.Type;


public class StaticArray17<T extends Type> extends StaticArray<T> {
    public StaticArray17(List<T> values) {
        super(17, values);
    }

    @SafeVarargs
    public StaticArray17(T... values) {
        super(17, values);
    }
}
