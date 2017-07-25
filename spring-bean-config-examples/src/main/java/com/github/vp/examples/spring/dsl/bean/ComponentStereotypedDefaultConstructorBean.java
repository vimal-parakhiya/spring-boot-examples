package com.github.vp.examples.spring.dsl.bean;

import com.github.vp.examples.spring.dsl.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by vimalpar on 30/06/17.
 */
@Component
public class ComponentStereotypedDefaultConstructorBean {
    private final static Logger logger = LoggerFactory.getLogger(ComponentStereotypedDefaultConstructorBean.class);

    public ComponentStereotypedDefaultConstructorBean() {
        logger.info(AppConstants.CREATED_BEAN, ComponentStereotypedDefaultConstructorBean.class);
    }

}
