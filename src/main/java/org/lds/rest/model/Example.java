package org.lds.rest.model;

public class Example {

    protected String value;

    public Example(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
