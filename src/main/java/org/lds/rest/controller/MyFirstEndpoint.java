package org.lds.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MyFirstEndpoint {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, String> myGetEndpoint(){
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("objectKey", "objectValue");
        return responseMap;
    }

}
