package com.vali.service.setting.impl;

import com.vali.dao.setting.HolidaySettingDAO;
import com.vali.dto.settings.HolidaySettingDTO;
import com.vali.po.setting.HolidaySettingPO;
import com.vali.service.setting.HolidaySettingService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/22.
 */
@Service("holidaySettingService")
public class HolidaySettingServiceImpl implements HolidaySettingService {
    @Resource(name = "holidaySettingDao")
    private HolidaySettingDAO holidaySettingDao;
    @Override
    public List<HolidaySettingDTO> queryHolidaySettings(Integer year) {
        List<HolidaySettingPO> listPo = holidaySettingDao.queryYearSettings(year);
        if(CollectionUtils.isEmpty(listPo)){
            return new ArrayList<HolidaySettingDTO>();
        }
        List<HolidaySettingDTO> listDto = new ArrayList<HolidaySettingDTO>();
        for(HolidaySettingPO po:listPo){
            HolidaySettingDTO dto = new HolidaySettingDTO();
            BeanUtils.copyProperties(po,dto);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public int saveHolidaySetting(HolidaySettingDTO holidaySettingDTO) {
        HolidaySettingPO po = new HolidaySettingPO();
        BeanUtils.copyProperties(holidaySettingDTO,po);
        return holidaySettingDao.saveHolidaySetting(po);
    }

    @Override
    public void deleteHolidaySetting(Integer id) {
        holidaySettingDao.deleteHolidaySetting(id);
    }

    @Override
    public boolean dayHasSettinged(Date day) {
        return queryDaySetting(day)!=null;
    }

    @Override
    public HolidaySettingDTO queryDaySetting(Date day) {
        HolidaySettingPO po = holidaySettingDao.queryDaySettings(new DateTime(day).withTime(0,0,0,0).toDate());
        if(po==null){
            return null;
        }
        HolidaySettingDTO dto = new HolidaySettingDTO();
        BeanUtils.copyProperties(po,dto);
        return dto;
    }
}
