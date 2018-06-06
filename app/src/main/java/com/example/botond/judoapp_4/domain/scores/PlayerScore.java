package com.example.botond.judoapp_4.domain.scores;

public interface PlayerScore {

    //score adders
    public void addShido(int nr);

    public void addKoka();

    public void addYuko();

    public void addWazari(int nr);

    public void addIppon(int nr);

    //score getters
    public Integer getShido();

    public Integer getKoka();

    public Integer getYuko();

    public Integer getWazari();

    public Boolean getIppon();

    public Boolean isHansokumake();

    public PlayerScore getOpponent();

    public void setShido(int shido);

    public void setOpponent(PlayerScore opponent);

}
