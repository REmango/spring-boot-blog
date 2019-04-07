package com.remango.blog.aop;

import com.alibaba.fastjson.JSON;
import com.remango.blog.entity.Log;
import com.remango.blog.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author zhengli
 * @since 2019/03/07
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.remango.blog.aop.ControllerLog)")
    public void controllerPointcut() {

    }

    @Around(value = "controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        ControllerLog syslog = method.getAnnotation(ControllerLog.class);
        String msg = syslog.value();
        log.debug(msg, "hhw");
        // 记录url、ip等http参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String ip = request.getRemoteAddr();
        // 记录输出参数
        Map<String, String[]> params = request.getParameterMap();
        log.debug("==>Request: [ip:{}, url:{}, httpMethod:{}, Parameters:{}]", ip, url, httpMethod, JSON.toJSONString(params));
        // 计算耗费时间
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long takingTime = endTime - startTime;

        // 记录输出参数
        log.debug("<==Response: {}", JSON.toJSONString(result));

        // 记录耗费时间
        log.debug("{} in {} ms", url, takingTime);

        // 输入输出参数的最大长度为2048，超过最大长度，截取前面2048个字符
        String paramsDB = JSON.toJSONString(params).length() >= 2048 ? JSON.toJSONString(params).substring(0, 2048) : JSON.toJSONString(params);
        Log log = new Log();
        log.setIp(ip);
        log.setMethod(httpMethod);
        log.setParams(paramsDB);
        log.setSpendTime((int)takingTime);
        log.setOperation(msg);
        log.setCreateTime(new Date());

        logService.saveLog(log);

        return result;
    }

}
