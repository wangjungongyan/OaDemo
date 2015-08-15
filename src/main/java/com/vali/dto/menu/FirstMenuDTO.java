package com.vali.dto.menu;

import lombok.Data;

import java.util.List;

/**
 * 一级菜单
 */
@Data
public class FirstMenuDTO {

    /**
     * 链接
     */
    private String href;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 对应的二级菜单
     */
    private List<SecondMenuDTO> secondMenus;
}
