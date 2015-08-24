package com.vali.service.setting;

import com.vali.dto.user.EmployeeDTO;

/**
 * Created by fanshuai on 15/8/24.
 */
public interface YearHolidayInitService {

    /**
     *
     * @param year
     * @param userDTO
     */
    void initUserSettings(Integer year,EmployeeDTO userDTO)throws Exception;

    /**
     *
     * @param year
     */
    void initSettings(Integer year)throws Exception;
}
