package com.vali.action.purchase;

import com.vali.bo.LoginBO;
import com.vali.dto.purchase.PurchaseDTO;
import com.vali.dto.purchase.PurchaseItemDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PurchaseAction {

    @RequestMapping(value = "/purchase/applyIndex")
    public ModelAndView index() {
        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee", employee);
        return new ModelAndView("purchase/purchaseIndex", model);
    }

    @RequestMapping(value = "/purchase/apply", method = RequestMethod.POST)
    public ModelAndView apply(PurchaseDTO purchaseDTO, HttpServletRequest request) {
        List<PurchaseItemDTO> items = this.preparePurchaseItems(request);
        purchaseDTO.setPurchaseItemDTOs(items);

        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/purchaseApply", model);
    }

    private List<PurchaseItemDTO> preparePurchaseItems(HttpServletRequest request) {
        String[] itemNames = request.getParameterValues("itemNames");
        String[] quantitys = request.getParameterValues("quantitys");
        String[] currencys = request.getParameterValues("currencys");
        String[] unitPrices = request.getParameterValues("unitPrices");
        String[] extendedPrices = request.getParameterValues("extendedPrices");
        String[] expDelDates = request.getParameterValues("expDelDates");

        List<PurchaseItemDTO> items = new ArrayList<PurchaseItemDTO>(20);

        if (itemNames == null || itemNames.length == 0) {
            return items;
        }

        for (int i = 0; i < itemNames.length; i++) {
            PurchaseItemDTO item = new PurchaseItemDTO();

            if (StringUtils.isBlank(itemNames[i])) {
                continue;
            }

            item.setItemName(itemNames[i]);
            item.setCurrency(currencys[i]);
            item.setExpDelDate(TimeUtil.format2Date(expDelDates[i], "yyyy-MM-dd"));
            item.setExtendedPrice(new BigDecimal(extendedPrices[i]));
            item.setQuantity(new Integer(quantitys[i]));
            item.setUnitPrice(new BigDecimal(unitPrices[i]));

            items.add(item);
        }

        return items;
    }

    @RequestMapping(value = "/purchase/myPurchaseApplyList")
    public ModelAndView myApplyList() {
        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/apply", model);
    }

    @RequestMapping(value = "/purchase/myPurchaseAudits")
    public ModelAndView myAudits() {
        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/apply", model);
    }

}
