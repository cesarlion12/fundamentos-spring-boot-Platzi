package com.fundamentosPlatzi.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyOperationImplement implements MyOperation{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);

    @Override
    public int sum(int number) {
        LOGGER.info("Ingresamos al metodo sum");
        return number+1;
    }
}
