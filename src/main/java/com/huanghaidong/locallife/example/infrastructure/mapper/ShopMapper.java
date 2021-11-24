package com.huanghaidong.locallife.example.infrastructure.mapper;

import com.huanghaidong.locallife.example.infrastructure.ShopDO;
import com.huanghaidong.locallife.example.infrastructure.param.QueryShopCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {

    List<ShopDO> selectByCondition(@Param("condition") QueryShopCondition condition);
}
