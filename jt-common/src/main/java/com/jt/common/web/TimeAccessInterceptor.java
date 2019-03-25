package com.jt.common.web;

import com.jt.common.exception.ServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component
public class TimeAccessInterceptor extends HandlerInterceptorAdapter {
    public TimeAccessInterceptor() {
        System.out.println("TimeAccessInterceptor()");
    }

    /**
     * 控制层目标方法执行之前执行
     *
     * @return 此方法值会决定我们的请求
     * 是否要交给后端控制器执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        //获取当前时间的日历对象
        Calendar c = Calendar.getInstance();
        //修改当前时间时分秒
        c.set(Calendar.HOUR_OF_DAY, 7);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        //获取修改以后的时间的毫秒值(可以作为开始访问时间)
        long startTime = c.getTimeInMillis();
        //修改当前时间时分秒
        c.set(Calendar.HOUR_OF_DAY, 24);
        //获取修改以后的时间毫秒值(可以作为最后访问时间)
        long endTime = c.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        if (currentTime < startTime || currentTime > endTime) {
            throw new ServiceException("不在访问时间之内");
        }
        return true;//表示放行

    }


}
