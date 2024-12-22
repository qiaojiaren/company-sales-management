package com.company.mapper;

import com.company.pojo.entity.Customer;
import com.company.pojo.entity.Salesperson;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalespersonMapper {

    /**
     * 查询所有销售人员信息
     * @return
     */
    @Select("select * from salespersons")
    List<Salesperson> list();

    /**
     * 新增销售人员信息
     * @param salespersons
     */
    void insert(Salesperson salespersons);

    /**
     * 修改销售人员信息
     * @param salespersons
     */
    void update(Salesperson salespersons);

    /**
     * 根据id删除销售人员信息
     * @param id
     */
    @Delete("delete from salespersons where salesperson_id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据用户名密码查询销售人员
     * @param salesperson
     * @return
     */
    @Select("select * from salespersons where username = #{username} and password = #{password}")
    Salesperson getByUsernameAndPassword(Salesperson salesperson);

    /**
     * 根据id查询销售人员信息
     * @param id
     * @return
     */
    @Select("select * from salespersons where salesperson_id = #{id}")
    Salesperson findById(Integer id);
}
