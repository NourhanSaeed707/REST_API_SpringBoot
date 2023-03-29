package com.example.restfulwebservices.helloWorld;

import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public  HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //interlization (languages)
    @GetMapping("/hello-world")
    public String helloWorldInternationlized () {
        Locale local = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "default message",null);

    }
}
