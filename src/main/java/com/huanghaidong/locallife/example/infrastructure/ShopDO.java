package com.huanghaidong.locallife.example.infrastructure;

import lombok.Data;

import java.util.Date;

@Data
public class ShopDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 门店名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
