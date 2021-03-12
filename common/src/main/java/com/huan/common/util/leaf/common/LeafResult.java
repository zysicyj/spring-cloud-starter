package com.huan.common.util.leaf.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Leaf响应结构
 *
 * @author huan
 */
@Slf4j
@AllArgsConstructor
@Data
public class LeafResult {
    private long id;
    private Status status;
}
