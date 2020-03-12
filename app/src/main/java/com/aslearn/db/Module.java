package com.aslearn.db;

/**
 * Created by hannonm2 on 6/8/18.
 */

public class Module {

    //Variables
    private String moduleName;
    private String type;
    private int unlocked;
    private int completed;
    private int moduleOrder;

    //Constructs a module from the database
     Module(String moduleName, String type, int unlocked, int completed, int moduleOrder) {
        this.moduleName = moduleName;
        this.type = type;
        this.unlocked = unlocked;
        this.completed = completed;
        this.moduleOrder = moduleOrder;
    }

    //Getters and Setters
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(int unlocked) {
        this.unlocked = unlocked;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(int moduleOrder) {
        this.moduleOrder = moduleOrder;
    }
}
