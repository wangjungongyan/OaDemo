package com.vali.dto.common;

/**
 * Created by vali on 15-8-19.
 */

public class QueryBaseDTO {

    protected String queryCondition;

    protected void setCondition() {

    }

    public String getQueryCondition() {
        setCondition();
        return queryCondition;
    }

}
