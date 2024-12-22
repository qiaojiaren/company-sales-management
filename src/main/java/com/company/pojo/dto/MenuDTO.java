package com.company.pojo.dto;

import com.company.pojo.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private Integer id;
    private String authName;
    private String path;
    private Integer parentId;
    private List<Menu> children;
}
