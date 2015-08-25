package com.vali.service.setting.impl;

import com.vali.dao.setting.EmployeeHolidayDao;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.EmployeeHolidayPO;
import com.vali.service.setting.YearHolidayInitService;
import com.vali.service.user.remote.EmployeeService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 15/8/24.
 */
@Service("yearHolidayInitService")
public class YearHolidayInitServiceImpl  implements YearHolidayInitService {
    @Resource(name = "employeeHolidayDao")
    EmployeeHolidayDao employeeHolidayDao;
    @Resource(name = "employeeService")
    EmployeeService employeeService;
    @Override
    public void initUserSettings(Integer year, EmployeeDTO userDTO)throws Exception{

        LeaveTypeEnum [] leaveTypeEnums = LeaveTypeEnum.values();
        for (LeaveTypeEnum leaveTypeEnum:leaveTypeEnums){
            if(!CollectionUtils.isEmpty(employeeHolidayDao.getEmployeeYearHoliday(userDTO.getId(), year.toString(),leaveTypeEnum.getType()))){
                continue;
            }
            EmployeeHolidayPO po = new EmployeeHolidayPO();
            po.setEmployeeId(userDTO.getId());
            po.setYear(year.toString());
            po.setUsed(new BigDecimal("0"));
            po.setAddTime(new Date());
            po.setType(leaveTypeEnum.getType());
            po.setUpdateTime(new Date());
            po.setOwn(new BigDecimal(leaveTypeEnum.getDefaultDayNum()));
            if(leaveTypeEnum.getType() == LeaveTypeEnum.AnnualLeaveEntitlement.getType()){
                po.setOwn(mathUserAnnualLeaveNum(year,userDTO.getJoinDate()));
            }
            employeeHolidayDao.saveHolidaySetting(po);
        }
    }

    public static BigDecimal mathUserAnnualLeaveNum(Integer year, Date joinDate) {
        DateTime joinDateTime = new DateTime(joinDate);
        if(joinDateTime.year().get()==year.intValue()){
            //当年入职
            if(joinDate.after(new DateTime().withDate(year, 6, 30).withTime(23, 59, 59, 999).toDate())){
                return new BigDecimal("2");
            }else {
                int monthNum = joinDateTime.monthOfYear().get();
                Double dayNum = (12-monthNum)*1.25d;
                if((dayNum-dayNum.intValue())>0.5){
                    return new BigDecimal(dayNum.intValue()+1);
                }
                if(dayNum-dayNum.intValue()<0.5&&dayNum-dayNum.intValue()>0){
                    return new BigDecimal(dayNum.intValue()).subtract(new BigDecimal("0.5"));
                }
                return new BigDecimal(dayNum);
            }
        }
        if(year.intValue()>joinDateTime.year().get()){
            int joinYear = joinDateTime.year().get();
            int yearNum = year-joinYear+1;
            if(yearNum<5){
                return new BigDecimal("10");
            }
            if(yearNum>=5){
                int num = 15+yearNum-5;
                if(num>20){
                    num=20;
                }
                return new BigDecimal(num);
            }
        }
        return new BigDecimal("0");
    }

    public static void main(String[] args){
        Date joinDate =new DateTime().withDate(2004,7,1).toDate();
        System.out.println(joinDate);
        System.out.println(mathUserAnnualLeaveNum(2015,joinDate));
    }


    @Override
    public void initSettings(Integer year) throws Exception{
        List<EmployeeDTO> userList = employeeService.queryAllEmployee();
        if(CollectionUtils.isEmpty(userList)){
            return ;
        }
        for (EmployeeDTO dto:userList){
            initUserSettings(year,dto);
        }
    }
}
