package com.example.botond.judoapp_4.activities.scoreboard;

import com.example.botond.judoapp_4.domain.scores.PlayerScore;
import com.example.botond.judoapp_4.domain.scores.ScoreDisplay;

public interface ScoreboardMVP {
    interface view{
        void setShidoImage(PlayerScore player, ScoreDisplay scoreDisplay);
    }
    interface presenter{
        void addWazari(int nr, PlayerScore player, ScoreDisplay scoreDisplay);
        void addIppon(int nr, PlayerScore player, ScoreDisplay scoreDisplay);
        void addShido(int nr, PlayerScore player, ScoreDisplay scoreDisplay);
    }
}
