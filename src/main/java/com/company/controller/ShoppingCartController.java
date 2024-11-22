package com.company.controller;

import com.company.pojo.dto.ShoppingCartDTO;
import com.company.pojo.entity.Result;
import com.company.pojo.entity.ShoppingCart;
import com.company.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/shoppingCart")
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 查询所有购物车信息
     * @return
     */
    @GetMapping("/search")
    public Result list(){

        log.info("查询所有购物车信息");

        List<ShoppingCart> shoppingCarts = shoppingCartService.list();
        return Result.success(shoppingCarts);
    }

    /**
     * 添加购物车信息
     * @return
     */
    @PostMapping("/create")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){

        log.info("开始添加购物车内容:{}",shoppingCartDTO);

        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public Result clean(){

        log.info("清空购物车");
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }


    /**
     * 减少购物车中的一个商品，如果少于0则删除
     * @return
     */
    @PostMapping("/sub")
    public Result sub(@RequestBody ShoppingCartDTO shoppingCartDTO){

        log.info("减少购物车一个商品的数量，商品：{}",shoppingCartDTO);

        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 加一购物车里面的一个商品
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/plus")
    public Result plus(@RequestBody ShoppingCartDTO shoppingCartDTO){

        log.info("购物车商品加一，商品：{}",shoppingCartDTO);

        shoppingCartService.plusShoppingCart(shoppingCartDTO);
        return Result.success();
    }
}
