package com.huanghaidong.locallife.example.application.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShopDetailResponse implements Serializable {


    private static final long serialVersionUID = -8864282218293342617L;

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
