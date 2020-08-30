package org.lds.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScopeExample {

    private Integer currentValue = 0;

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public void addValue(Integer myValue) {
        this.currentValue = this.currentValue + myValue;
    }
}
