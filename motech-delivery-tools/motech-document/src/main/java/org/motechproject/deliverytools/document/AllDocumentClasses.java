package org.motechproject.deliverytools.document;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Method;
import java.util.*;

@Repository
public class AllDocumentClasses implements BeanPostProcessor {

    private List<DocumentMethod> methods = new ArrayList<DocumentMethod>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods())
            if (method.isAnnotationPresent(Document.class))
                methods.add(new DocumentMethod(bean, method));
        return bean;
    }

    public Collection<DocumentClass> getAll() {
        Map<String, DocumentClass> classMap = new HashMap<String, DocumentClass>();
        for (DocumentMethod method : methods) {
            if (!classMap.containsKey(method.getClassName()))
                classMap.put(method.getClassName(), new DocumentClass(method.getClassName()));
            classMap.get(method.getClassName()).addMethod(method);
        }
        return classMap.values();
    }

}
