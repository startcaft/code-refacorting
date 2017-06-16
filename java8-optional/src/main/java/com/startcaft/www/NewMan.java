package com.startcaft.www;

import java.util.Optional;

/**
 * Created by startcaft on 2017/6/16.
 */
public class NewMan {

    private Optional<Godness> godness = Optional.empty();//Optional 千万不要为 null ，不然就然并卵了

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public NewMan() {
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }
}
