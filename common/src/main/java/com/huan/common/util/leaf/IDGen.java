package com.huan.common.util.leaf;

import com.huan.common.util.leaf.common.LeafResult;

public interface IDGen {
    LeafResult get(String key);

    boolean init();
}
