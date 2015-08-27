package com.vali.service.user.impl;

import com.vali.dao.setting.EmployeeHolidayDao;
import com.vali.dto.settings.EmployeeHolidayDTO;
import com.vali.dto.settings.HolidaySettingDTO;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.EmployeeHolidayPO;
import com.vali.service.setting.HolidaySettingService;
import com.vali.service.user.remote.EmployeeHolidayService;
import com.vali.util.TimeUtil;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

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
    private EmployeeHolidayDao    employeeHolidayDao;

    private BeanCopier ENTITY2DTO4Holiday = BeanCopier.create(EmployeeHolidayPO.class, EmployeeHolidayDTO.class, false);

    private BeanCopier DTO2ENTITY4Holiday = BeanCopier.create(EmployeeHolidayDTO.class, EmployeeHolidayPO.class, false);

    @Override public List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId) {
        return getEmployeeHoliday(employeeId, TimeUtil.getCurrentYear());
    }

    @Override public List<EmployeeHolidayDTO> getEmployeeHoliday(int employeeId, String year) {

        if (StringUtils.isBlank(year)) {
            year = TimeUtil.getCurrentYear();
        }

        List<EmployeeHolidayPO> pos = employeeHolidayDao.getEmployeeHoliday(employeeId, year);

        List<EmployeeHolidayDTO> dtos = new ArrayList<EmployeeHolidayDTO>(pos.size());
        List<Integer> types = new ArrayList<Integer>(10);

        for (EmployeeHolidayPO po : pos) {
            EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
            ENTITY2DTO4Holiday.copy(po, dto, null);
            LeaveTypeEnum leaveTypeEnum = LeaveTypeEnum.getLeaveType(po.getType());
            dto.setName(leaveTypeEnum.getName());
            dto.setDesc(leaveTypeEnum.getDesc());
            dto.setEnglishName(leaveTypeEnum.name());
            dtos.add(dto);
            types.add(po.getType());
        }

        LeaveTypeEnum[] leaveTypeEnums = LeaveTypeEnum.values();

        for (LeaveTypeEnum leaveTypeEnum : leaveTypeEnums) {
            if (!types.contains(leaveTypeEnum.getType()) && leaveTypeEnum != LeaveTypeEnum.ALL) {
                EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
                dto.setEmployeeId(employeeId);
                dto.setName(leaveTypeEnum.getName());
                dto.setDesc(leaveTypeEnum.getDesc());
                dto.setType(leaveTypeEnum.getType());
                dto.setUsed(new BigDecimal(0));
                dto.setOwn(new BigDecimal(0));
                dto.setSurplus(new BigDecimal(0));
                dto.setYear(year);
                dto.setEnglishName(leaveTypeEnum.name());
                dtos.add(dto);
            }
        }

        return dtos;
    }

    @Override public List<EmployeeHolidayDTO> getEmployeeHolidayWhenExists(int employeeId) {
        return getEmployeeHolidayWhenExists(employeeId, TimeUtil.getCurrentYear());
    }

    @Override public List<EmployeeHolidayDTO> getEmployeeHolidayWhenExists(int employeeId, String year) {
        if (StringUtils.isBlank(year)) {
            year = TimeUtil.getCurrentYear();
        }

        List<EmployeeHolidayPO> pos = employeeHolidayDao.getEmployeeHoliday(employeeId, year);

        List<EmployeeHolidayDTO> dtos = new ArrayList<EmployeeHolidayDTO>(pos.size());
        List<Integer> types = new ArrayList<Integer>(10);

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

    @Override public List<EmployeeHolidayDTO> getHolidayTypes() {

        List<EmployeeHolidayDTO> dtos = new ArrayList<EmployeeHolidayDTO>(12);

        LeaveTypeEnum[] leaveTypeEnums = LeaveTypeEnum.values();

        for (LeaveTypeEnum leaveTypeEnum : leaveTypeEnums) {
            EmployeeHolidayDTO dto = new EmployeeHolidayDTO();
            dto.setName(leaveTypeEnum.getName());
            dto.setDesc(leaveTypeEnum.getDesc());
            dto.setType(leaveTypeEnum.getType());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public BigDecimal caculateLeaveDays(Date beginTime, Date endTime) {

        BigDecimal dayNum = new BigDecimal("0");

        if (isOneDay(beginTime, endTime)) {
            return sameDayLeaveTime(beginTime, endTime);
        }

        DateTime oneDayEndTime = new DateTime(beginTime).withTime(18, 0, 0, 0);
        dayNum = dayNum.add(sameDayLeaveTime(beginTime, oneDayEndTime.toDate()));
        //            DateTime oneDayBeginTime = new DateTime(beginTime);
        //
        //            int secondNum=oneDayEndTime.secondOfDay().get()-oneDayBeginTime.secondOfDay().get();
        //            if(secondNum>4*60*60){//多于4小时1天
        //                dayNum.add(new BigDecimal("1"));
        //            }else if(secondNum>0){//少于4大于 0小时0.5天
        //                dayNum.add(new BigDecimal("0.5"));
        //            }
        for (int i = 1; ; i++) {
            DateTime nextDayBegin = new DateTime(beginTime).withTime(8, 0, 0, 0).plusDays(i);
            if (isOneDay(nextDayBegin.toDate(), endTime)) {
                dayNum = dayNum.add(sameDayLeaveTime(nextDayBegin.toDate(), endTime));
                break;
            }
            DateTime nextDayEnd = new DateTime(beginTime).withTime(18, 0, 0, 0).plusDays(i);
            dayNum = dayNum.add(sameDayLeaveTime(nextDayBegin.toDate(), nextDayEnd.toDate()));
        }
        return dayNum;
    }

    public BigDecimal sameDayLeaveTime(Date begin, Date end) {
        BigDecimal dayNum = new BigDecimal("0");
        if (end.before(begin)) {
            return dayNum;
        }

        if (!dayIsWorkDay(begin)) {
            return dayNum;
        }

        //是工作日且时间有间隔
        DateTime time9 = new DateTime(begin).withTime(9, 0, 0, 0);
        DateTime time13 = new DateTime(begin).withTime(13, 0, 0, 0);
        DateTime time13half = new DateTime(begin).withTime(13, 30, 0, 0);
        DateTime time18 = new DateTime(begin).withTime(17, 30, 0, 0);

        DateTime beginTime = new DateTime(begin);
        DateTime endTime = new DateTime(end);

        if (beginTime.isBefore(time9)) {
            beginTime = new DateTime(begin).withTime(9, 0, 0, 0);
        }

        if (endTime.isAfter(time18)) {
            endTime = new DateTime(begin).withTime(17, 30, 0, 0);
        }

        if (!endTime.toDate().after(beginTime.toDate())) {
            return new BigDecimal("0");
        }

        if (!endTime.isAfter(time13)) {
            return new BigDecimal("0.5");
        }

        if ((!beginTime.isBefore(time13) && !beginTime.isAfter(time13half))) {
            if (!endTime.isBefore(time13) && !endTime.isAfter(time13half)) {
                return new BigDecimal("0.0");
            }
            return new BigDecimal("0.5");
        }

        if (!beginTime.isAfter(time13)) {
            if (!endTime.isBefore(time13) && !endTime.isAfter(time13half)) {
                return new BigDecimal("0.5");
            }

            int secondNum = endTime.secondOfDay().get() - beginTime.secondOfDay().get() - 30 * 60;
            if (secondNum > 4 * 60 * 60) {
                return new BigDecimal("1.0");
            }
            return new BigDecimal("0.5");
        }

        return dayNum;
    }

    private boolean isOneDay(Date beginTime, Date endTime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(beginTime).equals(f.format(endTime));
    }

    private boolean dayIsWorkDay(Date day) {
        HolidaySettingDTO holidaySetting = holidaySettingService.queryDaySetting(day);
        if (holidaySetting != null) {
            return holidaySetting.isWorkDay();
        }
        //看是否是周六或周日
        DateTime d = new DateTime(day);
        int dayOfWeek = d.dayOfWeek().get();
        if (dayOfWeek == 6 || dayOfWeek == 7) {
            return false;
        }
        return true;
    }
    //08:30:00 09:00:00

    public static void main(String[] args) throws Exception {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new DateTime(f.parse("2015-08-22 08:30:00")).toDate();
        Date endDate = new DateTime(f.parse("2015-08-22 09:00:00")).toDate();

        EmployeeHolidayServiceImpl impl = new EmployeeHolidayServiceImpl();
        System.out.print(impl.sameDayLeaveTime(beginDate, endDate));

    }

    @Override public boolean decreaseHolidayDay(EmployeeHolidayDTO dto) {

        EmployeeHolidayPO po = new EmployeeHolidayPO();

        DTO2ENTITY4Holiday.copy(dto, po, null);

        return (employeeHolidayDao.decreaseHolidayDay(po) > 0);
    }

    @Override public boolean increaseHolidayDay(EmployeeHolidayDTO dto) {
        EmployeeHolidayPO po = new EmployeeHolidayPO();

        DTO2ENTITY4Holiday.copy(dto, po, null);

        return (employeeHolidayDao.increaseHolidayDay(po) > 0);
    }

    @Override public boolean updateHolidayOwn(EmployeeHolidayDTO dto) {

        EmployeeHolidayPO po = new EmployeeHolidayPO();

        DTO2ENTITY4Holiday.copy(dto, po, null);

        return (employeeHolidayDao.updateHolidayOwn(po) > 0);
    }
}
