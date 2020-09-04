package com.xtech.taskwatch;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Controller
public class MainController {

    @Autowired
    private InternalResourceViewResolver resolver;

    private String getField(String name) throws Exception {
        Class c = resolver.getClass().getSuperclass();
        System.out.println(c.getName());
        Method f = c.getMethod(name, null);
        f.setAccessible(true);
        return (String) f.invoke(resolver);
    }
    
    @GetMapping
    public String getHello() throws Exception {
        resolver.setPrefix("prefix");
        System.out.printf("prefix: %s, suffix: %s\n", getField("getPrefix"), getField("getSuffix"));
        return "hello";
    }

    @GetMapping("/error")
    public String GetError() {
        return "hello";
    }
}
