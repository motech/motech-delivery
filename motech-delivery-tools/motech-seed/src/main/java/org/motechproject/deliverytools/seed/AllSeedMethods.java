package org.motechproject.deliverytools.seed;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class AllSeedMethods implements BeanPostProcessor {

    private List<SeedMethod> methods = new ArrayList<SeedMethod>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods())
            if (method.isAnnotationPresent(Seed.class))
                methods.add(new SeedMethod(bean, method, method.getAnnotation(Seed.class).priority()));
        return bean;
    }

    public void run() throws Exception {
        Collections.sort(methods);
        for (SeedMethod method : methods)
            method.run();
    }

}
