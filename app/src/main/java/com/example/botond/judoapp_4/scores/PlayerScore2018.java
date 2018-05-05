package com.example.botond.judoapp_4.scores;

public class PlayerScore2018 implements PlayerScore{
    private static final int MAX_SHIDO = 3;
    private Integer shido;
    private Integer wazari;
    private Boolean ippon;

    //no args constructor
    public PlayerScore2018(){
        shido=0;
        wazari=0;
        ippon=false;
    }

    //all args constructor
    public PlayerScore2018(Integer shido, Integer wazari, Boolean ippon) {
        this.shido = shido;
        this.wazari = wazari;
        this.ippon = ippon;
    }


    @Override
    public void addShido(int nr) {
        shido+=nr;
        if(shido<0){
            shido=0;
        }
        if(shido>MAX_SHIDO){
            shido=MAX_SHIDO;
        }
    }

    @Override
    public void addKoka() {}

    @Override
    public void addYuko() {}

    @Override
    public void addWazari(int nr) {
        wazari+=nr;
        if(wazari<0){
            wazari=0;
        }
        if(wazari>=2){
            ippon=true;
            wazari=2;
        }
    }

    @Override
    public void addIppon(int nr) {
        if(nr>0){
            ippon=true;
        }
        else {
            ippon=false;
        }
    }

    @Override
    public Integer getShido() {
        return shido;
    }

    @Override
    public Integer getKoka() {
        return null;
    }

    @Override
    public Integer getYuko() {
        return null;
    }

    @Override
    public Integer getWazari() {
        return wazari;
    }

    @Override
    public Boolean getIppon() {
        return ippon;
    }

    @Override
    public Boolean isHansokumake() {
        return shido>=3;
    }


}
