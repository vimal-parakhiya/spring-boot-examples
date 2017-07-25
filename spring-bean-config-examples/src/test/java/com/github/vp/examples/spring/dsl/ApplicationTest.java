package com.github.vp.examples.spring.dsl;

import com.github.vp.examples.spring.dsl.bean.ComponentStereotypedDefaultConstructorBean;
import com.github.vp.examples.spring.dsl.bean.ComponentStereotypedParameterizedConstructorBean;
import com.github.vp.examples.spring.dsl.bean.DefaultConstructorPojoBean;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by vimalpar on 30/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldLoadContext() {
        Assertions.assertThat(applicationContext.getBean(ComponentStereotypedDefaultConstructorBean.class)).isNotNull();
        Assertions.assertThat(applicationContext.getBean(DefaultConstructorPojoBean.class)).isNotNull();

        ComponentStereotypedParameterizedConstructorBean stereotypedParameterizedConstructorBean = applicationContext.getBean(ComponentStereotypedParameterizedConstructorBean.class);
        assertThat(stereotypedParameterizedConstructorBean).isNotNull();
        assertThat(stereotypedParameterizedConstructorBean.getDefaultConstructorBean()).isNotNull();
    }

}