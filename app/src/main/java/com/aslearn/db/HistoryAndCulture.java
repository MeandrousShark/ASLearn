package com.aslearn.db;

/**
 * Created by vancoul on 6/10/18.
 */

public class HistoryAndCulture {
    private int hist_id;
    private String module;
    private String info;

    HistoryAndCulture(int hist_id, String module, String info){
        this.hist_id = hist_id;
        this.module = module;
        this.info = info;
    }

    /**
     * Getters and Setters
     */

    public int getHist_id() {
        return hist_id;
    }

    public void setHist_id(int hist_id) {
        this.hist_id = hist_id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
