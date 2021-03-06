package com.luckin.innovation.group.front.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.luckin.innovation.group.front.entity.Logger;
import com.luckin.innovation.group.front.service.impl.LoggerService;
import com.luckin.innovation.group.front.utils.LoggerUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private static final String LOGGER_SEND_TIME = "_send_time";

    private static final String LOGGER_ENTITY = "_logger_entity";

    /**
     * 进入SpringMVC的Controller之前开始记录日志实体
     * @param request 请求对象
     * @param response 响应对象
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //创建日志实体
        Logger logger = new Logger();
        //获取请求sessionId
        String sessionId = request.getRequestedSessionId();
        //请求路径
        String url = request.getRequestURI();
        //获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        //设置客户端ip
        logger.setClientIp(LoggerUtils.getCliectIp(request));
        //设置请求方法
        logger.setMethod(request.getMethod());
        //设置请求类型（json|普通请求）
        logger.setType(LoggerUtils.getRequestType(request));
        //设置请求参数内容json字符串
        logger.setParamData(paramData);
        //设置请求地址
        logger.setUri(url);
        //设置请求时间
        logger.setCreateTime(new Date());
        //设置sessionId
        logger.setSessionId(sessionId);
        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());
        //设置请求实体到request内，方面afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY,logger);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        //获取请求错误码
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //请求开始时间
        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());
        //获取本次请求日志实体
        Logger loggerEntity = (Logger) request.getAttribute(LOGGER_ENTITY);
        //设置请求时间差
        loggerEntity.setTimeConsuming(Integer.valueOf((currentTime - time)+""));
        //设置返回时间
        loggerEntity.setReturnTime(currentTime + "");
        //设置返回错误码
        loggerEntity.setHttpStatusCode(status+"");
        //设置返回值
        loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(LoggerUtils.LOGGER_RETURN),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue));
        //执行将日志写入数据库
        LoggerService loggerService = (LoggerService) SpringContextHolder.getBean("loggerService");
        loggerService.save(loggerEntity);
    }

    /**
     * 根据传入的类型获取spring管理的对应dao
     * @param clazz 类型
     * @param request 请求对象
     * @param <T>
     * @return
     */
    private <T> T getDAO(Class<T> clazz,HttpServletRequest request)
    {
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }
}
