package com.vali.action.purchase;

import com.leya.idal.model.PageModel;
import com.vali.bo.LoginBO;
import com.vali.dto.purchase.PurchaseApplyQueryDTO;
import com.vali.dto.purchase.PurchaseAttaDTO;
import com.vali.dto.purchase.PurchaseDTO;
import com.vali.dto.purchase.PurchaseItemDTO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.enums.purchase.PurchaseAuditStatusEnum;
import com.vali.enums.purchase.PurchaseBuyTypeEnum;
import com.vali.service.purchase.PurchaseService;
import com.vali.util.FileUtil;
import com.vali.util.TimeUtil;
import lombok.Setter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class PurchaseAction {

    @Setter
    @Value("${temp.upload.path}")
    private String tempPath;

    @Setter
    @Value("${real.upload.path}")
    private String realPath;

    @Setter
    @Value("${root.upload.path}")
    private String rootPath;

    @Resource(name = "purchaseService")
    private PurchaseService purchaseService;

    @RequestMapping(value = "/purchase/applyIndex")
    public ModelAndView index() {
        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee", employee);
        return new ModelAndView("purchase/purchaseIndex", model);
    }

    @RequestMapping(value = "/purchase/apply", method = RequestMethod.POST)
    public ModelAndView apply(HttpServletRequest request) {
        PurchaseDTO purchaseDTO = parseFileUpload(request);
        purchaseService.savePurchaseApply(purchaseDTO);

        EmployeeDTO employee = LoginBO.getLoginUser();
        Map model = new HashMap();
        model.put("employee", employee);
        return new ModelAndView("purchase/purchaseIndex", model);
    }

    private PurchaseDTO parseFileUpload(HttpServletRequest request) {

        FileUtil.createFolder(rootPath + tempPath);
        FileUtil.createFolder(rootPath + realPath);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(5 * 1024);
        factory.setRepository(new File(rootPath + tempPath));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024);

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        List<PurchaseAttaDTO> attas = new ArrayList<PurchaseAttaDTO>(5);

        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    String fieldValue = new String(item.get());

                    if (StringUtils.isBlank(fieldValue)) {
                        continue;
                    }

                    if ("applyTime".equals(field)) {
                        purchaseDTO.setApplyTime(TimeUtil.format2Date(fieldValue, "yyyy-MM-dd"));
                    }

                    if ("buyType".equals(field)) {
                        purchaseDTO.setBuyType(new Integer(fieldValue));
                    }

                    continue;
                }

                PurchaseAttaDTO atta = this.preparePurchaseAtta(item.getName());
                attas.add(atta);

                item.write(new File(rootPath + realPath + atta.getFilePath()));
            }

            purchaseDTO.setPurchaseItemDTOs(preparePurchaseItems(items));
        } catch (Exception e) {
            System.out.print("");
        }

        EmployeeDTO loginUser = LoginBO.getLoginUser();

        if (purchaseDTO.getBuyType() == PurchaseBuyTypeEnum.SELF.getType()) {
            purchaseDTO.setBuyUser(loginUser.getId());
        } else {
            //TODO 获取IT的员工号
            purchaseDTO.setBuyUser(1);
        }
        purchaseDTO.setApplicant(loginUser.getId());
        purchaseDTO.setDept(loginUser.getDeptName());
        purchaseDTO.setManager(loginUser.getManager().getId());
        purchaseDTO.setMngApproveStatus(PurchaseAuditStatusEnum.AUDITING.getType());
        purchaseDTO.setPurchaseAttaDTOs(attas);

        return purchaseDTO;
    }

    private List<PurchaseItemDTO> preparePurchaseItems(List<FileItem> items) {
        List<String> itemNames = new ArrayList<String>(10);
        List<Integer> quantitys = new ArrayList<Integer>(10);
        List<BigDecimal> unitPrices = new ArrayList<BigDecimal>(10);
        List<String> currencys = new ArrayList<String>(10);
        List<BigDecimal> extendedPrices = new ArrayList<BigDecimal>(10);
        List<Date> expDelDates = new ArrayList<Date>(10);

        List<PurchaseItemDTO> purchaseItems = new ArrayList<PurchaseItemDTO>(5);

        try {

            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    String fieldValue = new String(item.get());

                    if (StringUtils.isBlank(fieldValue)) {
                        continue;
                    }

                    if ("itemNames".equals(field)) {
                        itemNames.add(fieldValue);
                    }

                    if ("quantitys".equals(field)) {
                        quantitys.add(new Integer(fieldValue));
                    }

                    if ("unitPrices".equals(field)) {
                        unitPrices.add(new BigDecimal(fieldValue));
                    }

                    if ("currencys".equals(field)) {
                        currencys.add(fieldValue);
                    }

                    if ("extendedPrices".equals(field)) {
                        extendedPrices.add(new BigDecimal(fieldValue));
                    }

                    if ("expDelDates".equals(field)) {
                        expDelDates.add(TimeUtil.format2Date(fieldValue, "yyyy-MM-dd"));
                    }

                    continue;
                }
            }

        } catch (Exception e) {
            System.out.print("");
        }

        if (CollectionUtils.isEmpty(itemNames)) {
            return purchaseItems;
        }

        for (int i = 0; i < itemNames.size(); i++) {
            PurchaseItemDTO item = new PurchaseItemDTO();
            item.setItemName(itemNames.get(i));
            item.setUnitPrice(unitPrices.get(i));
            item.setQuantity(quantitys.get(i));
            item.setExtendedPrice(extendedPrices.get(i));
            item.setCurrency(currencys.get(i));
            item.setExpDelDate(expDelDates.get(i));

            purchaseItems.add(item);
        }

        return purchaseItems;
    }

    private PurchaseAttaDTO preparePurchaseAtta(String originalFileName) {
        String originalFileUploadPath = prepareRelativeUploadPath(originalFileName);

        PurchaseAttaDTO atta = new PurchaseAttaDTO();
        atta.setFileName(originalFileName);
        atta.setFilePath(originalFileUploadPath);

        return atta;
    }

    private String prepareRelativeUploadPath(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        StringBuffer realUploadPath = new StringBuffer();
        realUploadPath.append("/").append(uuid + originalFileName);
        return realUploadPath.toString();
    }

    @RequestMapping(value = "/purchase/myApply")
    public ModelAndView myApplyList(PurchaseApplyQueryDTO queryDTO, Integer pageNo, Integer pageSize) {

        int applicantID = LoginBO.getLoginUser().getId();
        queryDTO.setApplicantID(applicantID);

        PageModel pageModel = purchaseService.pagePurchaseApplys(queryDTO, pageNo, pageSize);

        Map model = new HashMap();
        model.put("queryDTO", queryDTO);
        model.put("pageModel", pageModel);

        return new ModelAndView("purchase/myApply", model);
    }

    @RequestMapping(value = "/purchase/myPurchaseAudits")
    public ModelAndView myAudits() {
        Map model = new HashMap();
        model.put("employeeHolidays", null);
        return new ModelAndView("purchase/apply", model);
    }

}
