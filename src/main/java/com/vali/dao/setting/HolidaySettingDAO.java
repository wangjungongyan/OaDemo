package com.vali.dao.setting;

import com.leya.idal.annotation.Action;
import com.leya.idal.annotation.NameSpace;
import com.leya.idal.annotation.ParamName;
import com.leya.idal.enums.ActionType;
import com.vali.po.setting.HolidaySettingPO;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/23.
 */
@NameSpace("HolidaySetting")
public interface HolidaySettingDAO {

    @Action(action = ActionType.QUERY_LIST)
    public List<HolidaySettingPO> queryYearSettings(@ParamName("year") Integer year);

    @Action(action = ActionType.INSERT)
    int saveHolidaySetting(@ParamName("po") HolidaySettingPO po);

    @Action(action = ActionType.DELETE)
    void deleteHolidaySetting(@ParamName("id") Integer id);

    @Action(action = ActionType.QUERY_OBJECT)
    HolidaySettingPO queryDaySettings(@ParamName("day") Date day);
}
