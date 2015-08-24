package com.vali.inteceptor;

import com.vali.bo.LoginBO;
import com.vali.dto.user.EmployeeDTO;
import com.vali.util.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by vali on 15-8-15.
 */
public class LoginInteceptor implements HandlerInterceptor {

    private List<String> excludedUrls;

    @Override public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                       Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        EmployeeDTO loginUser = (EmployeeDTO) session.getAttribute(Constant.LOGIN_USER);

        if (excludedUrls.contains(getStartOfRequestUri(httpServletRequest))) {
            if (loginUser != null) {
                httpServletResponse.sendRedirect("/main");
                return false;
            }

            return true;
        }

        if (loginUser == null) {
            httpServletResponse.sendRedirect("/login");
            return false;
        }

        LoginBO.setLoginUser(loginUser);
        return true;
    }

    private String getStartOfRequestUri(HttpServletRequest httpServletRequest) {
        String requestUri = httpServletRequest.getRequestURI();
        int lastIndex = requestUri.lastIndexOf("/");
        return (lastIndex == 0) ? requestUri : requestUri.substring(0, lastIndex + 1);
    }

    @Override public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                     Object o, ModelAndView modelAndView) throws Exception {

        LoginBO.removeLoginUser();
    }

    @Override public void afterCompletion(HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {

    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

}
