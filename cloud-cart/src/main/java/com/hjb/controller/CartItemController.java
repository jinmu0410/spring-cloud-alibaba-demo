package com.hjb.controller;
import com.hjb.service.CartItemService;
import com.hjb.domain.po.CartItem;
import com.hjb.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * CartItemcontroller
 * </p>
 *
 * @author jinmu
 * @date 2020-12-09
 */
@RestController
@Api(tags = "CartItem")
@RequestMapping("/cartItem")
public class CartItemController {

    @Autowired
    public CartItemService cartItemService;

    /**
    * 根据主键id查询单条
    * @param id
    */
    @ApiOperation(value = "获取单条数据")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result getCartItemById(Long id){
        return Result.SUCCESS(cartItemService.getById(id));
    }

    /**
    * 查询全部
    * @param param 查询条件
    */
    @ApiOperation(value = "全部查询", notes = "查询CartItem全部数据")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result getCartItemAll(@RequestBody HashMap<String, Object> param){
        List<CartItem> result= cartItemService.listByMap(param);
        return Result.SUCCESS(result);
    }

    /**
    * 分页查询
    * @param param 查询条件
    */
    @ApiOperation(value = "分页查询", notes = "分页查询CartItem全部数据")
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public Result getCartItemPage(@RequestBody HashMap<String, Object> param){
        PageHelper.startPage(Integer.valueOf(param.get("pageNum").toString()), Integer.valueOf(param.get("pageSize").toString()));
        //手动构建查询条件
        List<CartItem> result= cartItemService.list();
        PageInfo pageInfo = new PageInfo(result);
        return Result.SUCCESS(pageInfo);
    }

    /**
    * 更新保存单条数据
    * @param cartItem
    */
    @ApiOperation(value = "更新保存单条数据")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result addOrUpdateCartItem(@RequestBody CartItem cartItem){
        return Result.SUCCESS(cartItemService.saveOrUpdate(cartItem));
    }

    /**
    * 批量删除
    * @param ids
    */
    @ApiOperation(value = "批量删除数据")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result deleteCartItemById(List<Long> ids){
        return Result.SUCCESS(cartItemService.removeByIds(ids));
    }

}