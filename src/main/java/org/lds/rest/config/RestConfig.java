package org.lds.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lds.rest.model.PropertyInjected;
import org.lds.rest.model.ScopeExample;
import org.lds.rest.model.SetterInjected;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class RestConfig {


    @Bean
    public PropertyInjected getPropertyInjected(){
        return new PropertyInjected();
    }

    @Bean("setter")
    public SetterInjected getSetterInjected(){
        return new SetterInjected("0");
    }

    @Bean("setter1")
    public SetterInjected getSetterInjected2(){
        return new SetterInjected("1");
    }

    @Bean("singletonScope")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ScopeExample singletonScope(){
        return new ScopeExample();
    }

    @Bean("prototypeScope")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ScopeExample prototypeScope(){
        return  new ScopeExample();
    }

    @Bean("requestScope")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ScopeExample requestScope(){
        return new ScopeExample();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
