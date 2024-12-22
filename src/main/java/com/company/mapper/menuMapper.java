package com.company.mapper;

import com.company.pojo.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface menuMapper {

    /**
     * 查询所有父级菜单
     * @return
     */
    @Select("SELECT * FROM menu WHERE parent_id = 0")
    List<Menu> findParents();

    /**
     * 查询所有子菜单
     * @param id
     * @return
     */
    @Select("SELECT * FROM menu WHERE parent_id = #{Id}")
    List<Menu> findChildren(Integer parentId);
}
