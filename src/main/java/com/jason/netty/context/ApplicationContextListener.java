package com.jason.netty.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * date: 2017/7/2
 * description : spring启动监控,需要自定加载项配置
 *
 * @author : jason
 */
public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 系统入口初始化
        Map<String, Initialize> baseInterfaceBeans = contextRefreshedEvent.getApplicationContext().getBeansOfType(Initialize.class);
        for (Object service : baseInterfaceBeans.values()) {
            logger.debug(" {}.init()", service.getClass().getName());
            try {
                Method init = service.getClass().getMethod("init");
                init.invoke(service);
            } catch (Exception e) {
                logger.error("初始化BaseInterface的init方法异常", e);
                e.printStackTrace();
            }
        }
    }
}
