package org.lds.rest.controller;

import org.lds.rest.model.ConstructorInjected;
import org.lds.rest.model.PropertyInjected;
import org.lds.rest.model.SetterInjected;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/di")
public class MyDifferentTypesOfDependencyInjection {

    @Autowired
    private PropertyInjected propertyInjected;

    private SetterInjected setterInjected;

    private SetterInjected setterInjectedAgain;

    private ConstructorInjected constructorInjected;

    @Autowired
    public MyDifferentTypesOfDependencyInjection(ConstructorInjected constructorInjected){
        this.constructorInjected = constructorInjected;
    }

    @RequestMapping(value = "/constructor", method = RequestMethod.GET)
    public ConstructorInjected getConstructorInjected() {
        return constructorInjected;
    }

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    public PropertyInjected getPropertyInjected() {
        return propertyInjected;
    }

    @RequestMapping(value = "/setter", method = RequestMethod.GET)
    public SetterInjected getSetterInjected() {
        return setterInjected;
    }

    @RequestMapping(value = "/setter1", method = RequestMethod.GET)
    public SetterInjected getSetterInjectedAgain() {
        return setterInjectedAgain;
    }

    @Autowired
    private void setterInjected(@Qualifier("setter") SetterInjected setterInjected){
        this.setterInjected = setterInjected;
    }

    @Autowired
    private void setterInjectedAgain(@Qualifier("setter1") SetterInjected setterInjected){
        this.setterInjectedAgain = setterInjected;
    }
}
