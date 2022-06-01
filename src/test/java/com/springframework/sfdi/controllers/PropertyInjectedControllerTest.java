package com.springframework.sfdi.controllers;

import com.springframework.sfdi.services.ConstructorInjectedGreetingService;
import com.springframework.sfdi.services.PropertyInjectedGreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyInjectedControllerTest {

    PropertyInjectedController controller;

    @BeforeEach
    void setUp() {
        controller = new PropertyInjectedController();

        controller.greetingService = new PropertyInjectedGreetingService();
    }

    @Test
    void getGreeting() {
        assertEquals("Hello World - Property", controller.getGreeting());
    }
}