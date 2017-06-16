package com.startcaft.www;

import org.junit.Test;

import java.util.Optional;

public class TestOptional2 {

    //需求：获取一个男人心中的女神的名字，报错
    @Test(expected = NullPointerException.class)
    public void test(){

        Man man = new Man();//这个男人没女神

        String godNessName = this.getGodNessName(man);

        System.out.println(godNessName);
    }

    public String getGodNessName(Man man){
        return man.getGodness().getName();
    }

    /*传统做法*/
    @Test
    public void test2(){

        Man man = new Man();

        String godNessName = this.getGodNessName2(man);

        System.out.println(godNessName);
    }

    public String getGodNessName2(Man man){
        if(man != null){
            Godness m = man.getGodness();
            if(m != null){
                return m.getName();
            }
        }
        return "苍老师";
    }

    /*使用 Optional 的做法*/
    @Test
    public void test3(){
        Optional<NewMan> op = Optional.ofNullable(null);
        String godNessName = this.getGodNessName3(op);
        System.out.println(godNessName);

        Optional<Godness> godness = Optional.ofNullable(new Godness("波多老师"));
        op = Optional.ofNullable(new NewMan(godness));
        godNessName = this.getGodNessName3(op);
        System.out.println(godNessName);

    }
    public String getGodNessName3(Optional<NewMan> man){

        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍老师"))
                .getName();
    }
}
