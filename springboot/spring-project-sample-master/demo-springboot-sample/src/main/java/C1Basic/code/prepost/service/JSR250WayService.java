package C1Basic.code.prepost.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250WayService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        logger.debug("初始化方法，创建时执行");
    }

    public JSR250WayService() {
        super();
        logger.debug("构造函数执行-");
    }

    @PreDestroy
    public void destroy() {
        logger.debug("销毁方法，销毁bean时执行");
    }
}
