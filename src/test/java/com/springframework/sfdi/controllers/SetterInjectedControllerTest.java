package com.springframework.sfdi.controllers;

import com.springframework.sfdi.services.ConstructorInjectedGreetingService;
import com.springframework.sfdi.services.SetterInjectedGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetterInjectedControllerTest {

    SetterInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new SetterInjectedController();
        controller.setGreetingService(new SetterInjectedGreetingService());
    }

    @Test
    void getGreeting() {
         assertEquals("Hello World - Setter", controller.getGreeting());
    }
}