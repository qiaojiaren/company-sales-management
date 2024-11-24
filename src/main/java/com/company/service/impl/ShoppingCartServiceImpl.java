package com.company.service.impl;

import com.company.mapper.InventoryMapper;
import com.company.mapper.ShoppingCartMapper;
import com.company.pojo.dto.ShoppingCartDTO;
import com.company.pojo.entity.Inventory;
import com.company.pojo.entity.ShoppingCart;
import com.company.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private InventoryMapper inventoryMapper;


    /**
     * 查询购物车所有信息
     * @return
     */
    @Override
    public List<ShoppingCart> list() {
        return shoppingCartMapper.list();
    }

    /**
     * 添加购物车内容
     * @param shoppingCartDTO
     */
    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {

        //创建实体，将dto放进去填写部分
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);

        Inventory inventory = inventoryMapper.findById(shoppingCart.getProductId());
        Double price = inventory.getSellPrice() * shoppingCart.getProductQuantity();

        shoppingCart.setProductName(inventory.getProductName());
        shoppingCart.setSellPrice(price);
        shoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCart.setUpdateTime(LocalDateTime.now());

        shoppingCartMapper.insert(shoppingCart);
    }

    /**
     * 清空购物车
     */
    @Override
    public void cleanShoppingCart() {
        shoppingCartMapper.clean();
    }

    /**
     * 减少购物车一个物品的数量
     * @param shoppingCartDTO
     */
    @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        if(shoppingCartDTO.getProductQuantity() > 1){
            //将表里的记录读出来
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart = shoppingCartMapper.findById(shoppingCartDTO.getShoppingCartId());

            //计算更改后价格
            Inventory inventory = inventoryMapper.findById(shoppingCart.getProductId());
            Double price = inventory.getSellPrice() * (shoppingCart.getProductQuantity() -1 );

            //设置减一后的数量
            shoppingCart.setProductQuantity(shoppingCart.getProductQuantity()-1);

            //补全剩下需要修改的
            shoppingCart.setSellPrice(price);
            shoppingCart.setUpdateTime(LocalDateTime.now());

            shoppingCartMapper.updateNumberById(shoppingCart);
        }else {
            shoppingCartMapper.deleteById(shoppingCartDTO.getShoppingCartId());
        }

    }

    /**
     * 购物车内容加一
     * @param shoppingCartDTO
     */
    @Override
    public void plusShoppingCart(ShoppingCartDTO shoppingCartDTO) {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart = shoppingCartMapper.findById(shoppingCartDTO.getShoppingCartId());

        //计算更改后价格
        Inventory inventory = inventoryMapper.findById(shoppingCart.getProductId());
        Double price = inventory.getSellPrice() * (shoppingCart.getProductQuantity() + 1 );

        //设置加一后的数量
        shoppingCart.setProductQuantity(shoppingCart.getProductQuantity() + 1);

        //补全剩下需要修改的
        shoppingCart.setSellPrice(price);
        shoppingCart.setUpdateTime(LocalDateTime.now());

        shoppingCartMapper.updateNumberById(shoppingCart);
    }

}
