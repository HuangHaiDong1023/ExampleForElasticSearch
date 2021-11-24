package com.huanghaidong.locallife.example.application.controller;

import com.huanghaidong.locallife.example.application.convert.ShopConvert;
import com.huanghaidong.locallife.example.application.request.QueryShopList4PageRequest;
import com.huanghaidong.locallife.example.application.response.ShopDetailResponse;
import com.huanghaidong.locallife.example.common.Result;
import com.huanghaidong.locallife.example.infrastructure.ShopDO;
import com.huanghaidong.locallife.example.infrastructure.mapper.ShopMapper;
import com.huanghaidong.locallife.example.infrastructure.param.QueryShopCondition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/shop")
public class ShopController {

    @Resource
    private ShopMapper shopMapper;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Result<List<ShopDetailResponse>> list(@RequestBody QueryShopList4PageRequest request) {

        QueryShopCondition queryShopCondition = ShopConvert.convert2Condition(request);

        List<ShopDO> shopDOList = shopMapper.selectByCondition(queryShopCondition);

        Result<List<ShopDetailResponse>> result = new Result<List<ShopDetailResponse>>();

        result.setData(ShopConvert.convert2Response(shopDOList));

        return result;
    }
}
