package com.vali.bo;

/**
 * Created by vali on 15-8-20.
 */
public class PageBO {

    public static int getPageNo(Integer pageNo) {
        return (pageNo == null) ? 1 : pageNo;
    }

    public static int getPageSize(Integer pageSize) {
        return (pageSize == null) ? 10 : pageSize;
    }

}
