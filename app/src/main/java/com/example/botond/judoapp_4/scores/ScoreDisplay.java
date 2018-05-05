package com.example.botond.judoapp_4.scores;

import android.widget.ImageView;
import android.widget.TextView;

public class ScoreDisplay {
    private TextView wazari;
    private TextView ippon;
    private ImageView shido;

    public ScoreDisplay() {
    }

    public ScoreDisplay(TextView wazari, TextView ippon, ImageView shido) {
        this.wazari = wazari;
        this.ippon = ippon;
        this.shido = shido;
    }

    public TextView getWazari() {
        return wazari;
    }

    public void setWazari(TextView wazari) {
        this.wazari = wazari;
    }

    public TextView getIppon() {
        return ippon;
    }

    public void setIppon(TextView ippon) {
        this.ippon = ippon;
    }

    public ImageView getShido() {
        return shido;
    }

    public void setShido(ImageView shido) {
        this.shido = shido;
    }
}
