package org.motechproject.deliverytools.seed;

import org.motechproject.deliverytools.util.Version;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class AllSeedMethods implements BeanPostProcessor {

    private List<SeedMethod> methods = new ArrayList<SeedMethod>();

    @Value("#{seedProperties['versions']}")
    private String allVersions;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        List<Version> allVersionList = getAllVersionList();
        List<SeedMethod> allSeedMethods = getAllSeedMethods(bean);
        addValidSeedMethodsToRun(allSeedMethods, allVersionList);
        return bean;
    }

    private void addValidSeedMethodsToRun(List<SeedMethod> allSeedMethods, List<Version> allVersionList) {
        for (Version version : allVersionList)
            for (SeedMethod method : allSeedMethods)
                if (method.shouldRunFor(version))
                    methods.add(method);
    }

    private List<Version> getAllVersionList() {
        String[] versionList = allVersions.split(",");
        List<Version> allVersionsList = new ArrayList<Version>();
        for (String s : versionList)
            allVersionsList.add(new Version(s));
        return allVersionsList;
    }

    private List<SeedMethod> getAllSeedMethods(Object bean) {
        List<SeedMethod> allSeedMethods = new ArrayList<SeedMethod>();
        for (Method method : bean.getClass().getDeclaredMethods())
            if (method.isAnnotationPresent(Seed.class))
                allSeedMethods.add(
                        new SeedMethod(bean,
                                method,
                                method.getAnnotation(Seed.class).priority(),
                                method.getAnnotation(Seed.class).version()));

        return allSeedMethods;
    }

    public void run() throws Exception {
        Collections.sort(methods);
        for (SeedMethod method : methods)
            method.run();
    }
}
