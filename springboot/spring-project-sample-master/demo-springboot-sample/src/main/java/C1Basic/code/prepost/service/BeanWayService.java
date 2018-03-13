package C1Basic.code.prepost.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanWayService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init() {
        logger.debug("初始化方法，创建时执行");
    }

    public BeanWayService() {
        super();
        logger.debug("构造函数执行-");
    }

    public void destroy() {
        logger.debug("销毁方法，销毁bean时执行");
    }
}
