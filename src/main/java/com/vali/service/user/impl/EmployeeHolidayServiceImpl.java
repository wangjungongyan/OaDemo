package com.vali.service.user.impl;

import com.vali.dao.leave.EmployeeHolidayDao;
import com.vali.dto.leave.EmployeeHolidayDTO;
import com.vali.enums.leave.LeaveTypeEnum;
import com.vali.po.leave.EmployeeHolidayPO;
import com.vali.service.user.remote.EmployeeHolidayService;
import lombok.Setter;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vali on 15-8-16.
 */
@Service("employeeHolidayService")
public class EmployeeHolidayServiceImpl implements EmployeeHolidayService {

    @Setter
    @Resource(name = "employeeHolidayDao")
    private EmployeeHolidayDao employeeHolidayDao;

    private BeanCopier ENTITY2DTO4Holiday = BeanCopier.create(EmployeeHolidayPO.class, EmployeeHolidayDTO.class, false);

    private BeanCopier DTO2ENTITY4Holiday = BeanCopier.create(EmployeeHolidayDTO.class, EmployeeHolidayPO.class, false);

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

    //TODO
    @Override public BigDecimal caculateLevaeDays(Date beginTime, Date endTime) {
        return new BigDecimal(1.5);
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
}
