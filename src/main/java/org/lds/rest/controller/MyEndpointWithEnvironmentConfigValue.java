package org.lds.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/environment")
public class MyEndpointWithEnvironmentConfigValue {

    private Environment environment;

    @Autowired
    public MyEndpointWithEnvironmentConfigValue(Environment environment){
        this.environment = environment;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMyValue(@RequestParam("key") String value){
        return environment.getProperty(value, "UNKNOWN");
    }

}
