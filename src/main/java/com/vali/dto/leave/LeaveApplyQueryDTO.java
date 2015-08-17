package com.vali.dto.leave;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vali.util.TimeUtil;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * Created by vali on 15-8-17.
 */

@Data
public class LeaveApplyQueryDTO {

    private int applicantID;

    private Date startTime;

    private Date endTime;

    private int leaveType;

    public LeaveApplyQueryDTO(String queryConditions) {

        JSONObject jsonObject = this.parse2JsonObject(queryConditions);

        String startTimeStr = (String) jsonObject.get("startTime");
        String endTimeStr = (String) jsonObject.get("endTime");
        Date startTime = TimeUtil.format2Date(startTimeStr, "yyyy-MM-dd HH:mm");
        Date endTime = TimeUtil.format2Date(endTimeStr, "yyyy-MM-dd HH:mm");
        int leaveType = new Integer((String)jsonObject.get("leaveType")) ;

        this.startTime = startTime;
        this.endTime = endTime;
        this.leaveType = leaveType;
    }

    private JSONObject parse2JsonObject(String jsonBody) {

        JSONObject ob = null;
        try {
            ob = JSON.parseObject(URLDecoder.decode(jsonBody, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            ob = null;
        }

        return ob;
    }

}
