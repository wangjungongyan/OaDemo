package com.vali.dto.menu;

import lombok.Data;

/**
 * 二级菜单
 */

@Data
public class SecondMenuDTO {

    /**
     * 链接
     */
    private String href;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 排序
     */
    private int index;

}
