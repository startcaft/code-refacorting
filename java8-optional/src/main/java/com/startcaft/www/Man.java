package com.startcaft.www;

/**
 * Created by startcaft on 2017/6/16.
 */
public class Man {

    private Godness godness;

    public Godness getGodness() {
        return godness;
    }

    public void setGodness(Godness godness) {
        this.godness = godness;
    }

    public Man(Godness godness) {
        this.godness = godness;
    }

    public Man() {
    }

    @Override
    public String toString() {
        return "Man{" +
                "godness=" + godness +
                '}';
    }
}
