package org.lds.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lds.rest.annotation.LogMe;
import org.lds.rest.model.ScopeExample;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
public class MyScopeExample {

    private ApplicationContext applicationContext;

    public MyScopeExample(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/singleton", method = RequestMethod.GET)
    public ScopeExample getSingletonScope(){
        return applicationContext.getBean("singletonScope", ScopeExample.class);
    }

    @RequestMapping(value = "/singleton", method = RequestMethod.POST)
    public ScopeExample addSingletonScopeValue(@LogMe Integer addValue){
        ScopeExample singletonScope = applicationContext.getBean("singletonScope", ScopeExample.class);
        singletonScope.addValue(addValue);
        return singletonScope;
    }

    @RequestMapping(value = "/prototype", method = RequestMethod.GET)
    public ScopeExample getPrototypeScope(){

        return applicationContext.getBean("prototypeScope", ScopeExample.class);
    }

    @RequestMapping(value = "/prototype", method = RequestMethod.POST)
    @LogMe
    public ScopeExample addPrototypeScopeValue(Integer addValue){
        ScopeExample prototypeScope = applicationContext.getBean("prototypeScope", ScopeExample.class);
        prototypeScope.addValue(addValue);
        return prototypeScope;
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public Integer getRequestScopeValue() throws JsonProcessingException {

        ScopeExample example =  applicationContext.getBean("requestScope", ScopeExample.class);
//        ObjectMapper mapper = applicationContext.getBean("objectMapper", ObjectMapper.class);
//        String exampleString = mapper.writerFor(ScopeExample.class).writeValueAsString(example);
//        return mapper.readValue(exampleString, ScopeExample.class);
        return  example.getCurrentValue();
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
//    @LogMe
    public Integer addRequestScopeValue(Integer addValue){
        ScopeExample requestScope = applicationContext.getBean("requestScope", ScopeExample.class);
        requestScope.addValue(addValue);
//        ScopeExample responseExample = new ScopeExample();
//        responseExample.setCurrentValue(requestScope.getCurrentValue());
//        return responseExample;
        return requestScope.getCurrentValue();
    }


}
