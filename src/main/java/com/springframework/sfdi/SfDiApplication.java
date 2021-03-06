package com.springframework.sfdi;

import com.springframework.sfdi.config.SfConfiguration;
import com.springframework.sfdi.config.SfConstructorConfig;
import com.springframework.sfdi.controllers.*;
import com.springframework.sfdi.datasource.FakeDataSource;
import com.springframework.sfdi.services.PrototypeBean;
import com.springframework.sfdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfDiApplication.class, args);

		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--------The Best Pet is");
		System.out.println(petController.whichPetIsTheBest());

		I18NController i18NController = (I18NController) ctx.getBean("i18NController");

		System.out.println("--------I18N");
		System.out.println(i18NController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");

		System.out.println("--------Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("--------Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("--------Constructor");
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("--------Bean Scopes");
		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean1.getMyScope());
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean2.getMyScope());

		PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean1.getMyScope());
		PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.getMyScope());

		System.out.println("--------Fake Data Source");
		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
		System.out.println(fakeDataSource.getUsername());
		System.out.println(fakeDataSource.getPassword());
		System.out.println(fakeDataSource.getJdbcurl());

		System.out.println("--------Config Prop Bean");
		SfConfiguration sfConfiguration = ctx.getBean(SfConfiguration.class);
		System.out.println(sfConfiguration.getUsername());
		System.out.println(sfConfiguration.getPassword());
		System.out.println(sfConfiguration.getJdbcurl());

		System.out.println("--------Constructor Binding");
		SfConstructorConfig sfConstructorConfig = ctx.getBean(SfConstructorConfig.class);
		System.out.println(sfConstructorConfig.getUsername());
		System.out.println(sfConstructorConfig.getPassword());
		System.out.println(sfConstructorConfig.getJdbcurl());
	}

}
