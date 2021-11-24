package com.huanghaidong.locallife.example.application.convert;

import com.huanghaidong.locallife.example.application.request.QueryShopList4PageRequest;
import com.huanghaidong.locallife.example.application.response.ShopDetailResponse;
import com.huanghaidong.locallife.example.infrastructure.ShopDO;
import com.huanghaidong.locallife.example.infrastructure.param.QueryShopCondition;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ShopConvert {

    public static QueryShopCondition convert2Condition(QueryShopList4PageRequest request) {

        QueryShopCondition condition  = new QueryShopCondition();

        condition.setName(request.getName());
        condition.setOffset((request.getPageIndex() - 1) * request.getPageSize());
        condition.setRows(request.getPageSize());

        return condition;
    }

    public static List<ShopDetailResponse> convert2Response(List<ShopDO> shopDOList) {

        if (CollectionUtils.isEmpty(shopDOList)) {
            return Lists.newArrayList();
        }

        return shopDOList.stream().map(ShopConvert::convert2Response).collect(Collectors.toList());
    }

    public static ShopDetailResponse convert2Response(ShopDO shopDO) {

        ShopDetailResponse response = new ShopDetailResponse();
        BeanUtils.copyProperties(shopDO, response);

        return response;
    }
}
