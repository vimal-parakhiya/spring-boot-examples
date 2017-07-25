package com.github.vp.examples.spring.dsl.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 30/06/17.
 */
@Component
public class ComponentStereotypedParameterizedConstructorBean {
    private ComponentStereotypedDefaultConstructorBean defaultConstructorBean;

    @Autowired
    public ComponentStereotypedParameterizedConstructorBean(ComponentStereotypedDefaultConstructorBean defaultConstructorBean) {
        this.defaultConstructorBean = defaultConstructorBean;
    }

    public ComponentStereotypedDefaultConstructorBean getDefaultConstructorBean() {
        return defaultConstructorBean;
    }
}
