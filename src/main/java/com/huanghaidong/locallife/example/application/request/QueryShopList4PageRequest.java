package com.huanghaidong.locallife.example.application.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryShopList4PageRequest implements Serializable {

    private static final long serialVersionUID = -7120649119476102955L;

    private String name;

    private Integer pageIndex = 1;

    private Integer pageSize = 10;
}
