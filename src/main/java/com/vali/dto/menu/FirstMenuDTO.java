package com.vali.dto.menu;

import lombok.Data;

import java.util.List;

/**
 * Created by vali on 15-8-13.
 */

@Data
public class FirstMenuDTO {

    private String href;

    private String name;

    private List<SecondMenuDTO> secondMenus;
}
