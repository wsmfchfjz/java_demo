package C1Basic.code.aware.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Spring Aware，与容器通信
 */
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {
    String beanName;
    ResourceLoader resourceLoader;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void outputInfo() {
        System.out.println("bean在容器中的名称是："+beanName);
        Resource resource = resourceLoader.getResource("classpath:aware/test.txt");
        try {
            System.out.println("资源文件的内容是："+IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
