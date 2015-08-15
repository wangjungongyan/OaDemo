package com.vali.inteceptor;

import com.vali.bo.LoginBO;
import com.vali.dto.user.UserDTO;
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

        String requestUri = httpServletRequest.getRequestURI();

        if (excludedUrls.contains(requestUri)) {
            return true;
        }

        HttpSession session = httpServletRequest.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute(Constant.LOGIN_USER);

        if (loginUser == null) {
            httpServletResponse.sendRedirect("redirect:/login");
            return false;
        }

        LoginBO.setLoginUser(loginUser);
        return true;
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
