package com.huanghaidong.locallife.example.infrastructure.param;

import lombok.Data;

@Data
public class QueryShopCondition {

    private String name;

    private Integer offset;

    private Integer rows;
}
