package org.lds.rest.model;

public class SetterInjected extends Example {

    private String instanceNumber;

    public SetterInjected(String instanceNumber) {
        super("SETTER");
        this.instanceNumber = instanceNumber;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(String instanceNumber) {
        this.instanceNumber = instanceNumber;
    }
}
