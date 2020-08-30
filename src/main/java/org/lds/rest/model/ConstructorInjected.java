package org.lds.rest.model;

import org.springframework.stereotype.Component;

@Component
public class ConstructorInjected extends Example {

    public ConstructorInjected() {
        super("CONSTRUCTOR");
    }

}
