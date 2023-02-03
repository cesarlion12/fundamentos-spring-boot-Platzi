package com.fundamentosPlatzi.springboot.fundamentos.bean.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplements implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente");
    }
}
