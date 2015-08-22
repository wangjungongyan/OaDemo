package com.vali.service.setting;

import com.vali.dto.settings.HolidaySettingDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/22.
 */
public interface HolidaySettingService {
    List<HolidaySettingDTO> queryHolidaySettings(Integer year);

    int saveHolidaySetting(HolidaySettingDTO holidaySettingDTO);

    void deleteHolidaySetting(Integer id);

    boolean dayHasSettinged(Date day);

    HolidaySettingDTO queryDaySetting(Date day);
}
