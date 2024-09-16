package com.heima.admin.interceptor;

import com.heima.model.admin.pojos.AdUser;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.threadLocal.AdThreadLocalUtil;
import com.heima.utils.common.threadLocal.WmThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class ApTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //得到header中的信息
        String userId = request.getHeader("userId");
        Optional<String> optional = Optional.ofNullable(userId);
        if(optional.isPresent()){
            //把用户id存入threadloacl中
            AdUser adUser = new AdUser();
            adUser.setId(Integer.valueOf(userId));
            AdThreadLocalUtil.setUser(adUser);
            log.info("adTokenFilter设置用户信息到threadlocal中...");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("清理threadlocal...");
        WmThreadLocalUtil.clear();
    }
}
