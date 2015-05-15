package org.baumann.andrew.archive.planner;

enum FileEnum {
    INCLUDE("include"), 
    EXCLUDE("exclude");
    
    private final String string;
    private FileEnum(String string) {
        this.string = string;
    }
    
    public String getString() {
        return string;
    }

}
