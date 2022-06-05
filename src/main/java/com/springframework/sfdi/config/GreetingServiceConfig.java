package com.springframework.sfdi.config;

import com.pets.PetService;
import com.pets.PetServiceFactory;
import com.springframework.sfdi.datasource.FakeDataSource;
import com.springframework.sfdi.repositories.EnglishGreetingRepository;
import com.springframework.sfdi.repositories.EnglishGreetingRepositoryImpl;
import com.springframework.sfdi.services.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(SfConstructorConfig.class)
@ImportResource("classpath:sfdi-config.xml")
@Configuration
public class GreetingServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(SfConstructorConfig sfConstructorConfig) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(sfConstructorConfig.getUsername());
        fakeDataSource.setPassword(sfConstructorConfig.getPassword());
        fakeDataSource.setJdbcurl(sfConstructorConfig.getJdbcurl());
        return fakeDataSource;
    }

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Profile({"cat"})
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18NEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18NEnglishGreetingService(englishGreetingRepository);
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishGreetingService i18NSpanishGreetingService() {
        return new I18NSpanishGreetingService();
    }

    @Bean
    @Primary
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

//    @Bean
//    ConstructorInjectedGreetingService constructorInjectedGreetingService() {
//        return new ConstructorInjectedGreetingService();
//    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }
}
