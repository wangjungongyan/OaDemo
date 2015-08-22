package com.vali.service.user.impl;

import com.vali.dao.leave.EmployeeHolidayDao;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.dto.settings.HolidaySettingDTO;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.EmployeeHolidayPO;
import com.vali.service.setting.HolidaySettingService;
import com.vali.service.user.remote.EmployeeHolidayService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vali on 15-8-16.
 */
@Service("employeeHolidayService")
public class EmployeeHolidayServiceImpl implements EmployeeHolidayService {

    @Resource(name = "holidaySettingService")
    private HolidaySettingService holidaySettingService;
    @Setter
    @Resource(name = "employeeHolidayDao")
    private EmployeeHolidayDao employeeHolidayDao;

    private BeanCopier ENTITY2DTO4Holiday = BeanCopier.create(EmployeeHolidayPO.class, EmployeeHolidayDTO.class, false);

    @Override public List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId) {

        List<EmployeeHolidayPO> pos = employeeHolidayDao.getEmployeeHoliday(employeeId);

        if (CollectionUtils.isEmpty(pos)) {
            return new ArrayList<EmployeeHolidayDTO>(1);
        }

        List<EmployeeHolidayDTO> dtos = new ArrayList<EmployeeHolidayDTO>(pos.size());

        for (EmployeeHolidayPO po : pos) {
            EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
            ENTITY2DTO4Holiday.copy(po, dto, null);
            LeaveTypeEnum leaveTypeEnum = LeaveTypeEnum.getLeaveType(po.getType());
            dto.setName(leaveTypeEnum.getName());
            dto.setDesc(leaveTypeEnum.getDesc());
            dtos.add(dto);
        }

        return dtos;
    }




    @Override
    public BigDecimal caculateLevaeDays(Date beginTime, Date endTime) {
        BigDecimal dayNum = new BigDecimal("0");
        if(isOneDay(beginTime,endTime)){
            return sameDayLeaveTime(beginTime,endTime);
        }
        DateTime oneDayEndTime = new DateTime(beginTime).withTime(18, 0, 0, 0);
        dayNum=dayNum.add(sameDayLeaveTime(beginTime, oneDayEndTime.toDate()));
//            DateTime oneDayBeginTime = new DateTime(beginTime);
//
//            int secondNum=oneDayEndTime.secondOfDay().get()-oneDayBeginTime.secondOfDay().get();
//            if(secondNum>4*60*60){//多于4小时1天
//                dayNum.add(new BigDecimal("1"));
//            }else if(secondNum>0){//少于4大于 0小时0.5天
//                dayNum.add(new BigDecimal("0.5"));
//            }
        for (int i = 1;;i++){
            DateTime nextDayBegin = new DateTime(beginTime).withTime(8,0,0,0).plusDays(i);
            if(isOneDay(nextDayBegin.toDate(), endTime)){
                dayNum=dayNum.add(sameDayLeaveTime(nextDayBegin.toDate(), endTime));
                break;
            }
            DateTime nextDayEnd = new DateTime(beginTime).withTime(18,0,0,0).plusDays(i);
            dayNum=dayNum.add(sameDayLeaveTime(nextDayBegin.toDate(), nextDayEnd.toDate()));
        }
        return dayNum;
    }
    private BigDecimal sameDayLeaveTime(Date begin,Date end){
        BigDecimal dayNum = new BigDecimal("0");
        if(end.before(begin)){
            return dayNum;
        }
        if(!dayIsWorkDay(begin)){
            return dayNum;
        }

        //是工作日且时间有间隔
        DateTime time9 = new DateTime(begin).withTime(9,0,0,0);
        DateTime time12 = new DateTime(begin).withTime(12,0,0,0);
        DateTime time13 = new DateTime(begin).withTime(13,0,0,0);
        DateTime time18 = new DateTime(begin).withTime(18,0,0,0);

        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);
        if(beginTime.isBefore(time9)){
            //请假时间早于8点，按从8点开始
            beginTime = new DateTime(begin).withTime(9,0,0,0);
        }
        if(endTime.isAfter(time18)){
            endTime = new DateTime(begin).withTime(18,0,0,0);
        }

        int secondNum = endTime.secondOfDay().get()-beginTime.secondOfDay().get();
        //是否排除中午休息时间
        if(secondNum>4*60*60){
            dayNum = new BigDecimal("1");
        }else if(secondNum>0){
            dayNum = new BigDecimal("0.5");
        }
        return dayNum;
    }

    private boolean isOneDay(Date beginTime, Date endTime){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(beginTime).equals(f.format(endTime));
    }
    private boolean dayIsWorkDay(Date day){
        HolidaySettingDTO holidaySetting = holidaySettingService.queryDaySetting(day);
        if(holidaySetting!=null){
            return holidaySetting.isWorkDay();
        }
        //看是否是周六或周日
        DateTime d = new DateTime(day);
        int dayOfWeek = d.dayOfWeek().get();
        if(dayOfWeek==6||dayOfWeek==7){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        try {
            EmployeeHolidayServiceImpl a = new EmployeeHolidayServiceImpl();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //System.out.println(a.caculateLevaeDays(f.parse("2015-01-01 9:0:03"), f.parse("2015-01-1 13:0:04")));
            DateTime d = new DateTime(f.parse("2015-08-22 9:0:03"));
            int dayOfWeek = d.dayOfWeek().get();
            System.out.println(dayOfWeek);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override public boolean decreaseHolidayDay(EmployeeHolidayDTO dto) {
        return true;
    }

    @Override public boolean increaseHolidayDay(EmployeeHolidayDTO dto) {
        return true;
    }
}
