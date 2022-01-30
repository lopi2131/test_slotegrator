package com.testslotegrator.automation.utils;

import com.testslotegrator.automation.pages.AuthPage;
import com.testslotegrator.automation.pages.PlayerPage;

public class StepManager {
    private AuthPage authPage;
    private PlayerPage playerPage;

    public void init(){
        authPage = new AuthPage();
        playerPage = new PlayerPage();
    }

    public AuthPage authPage(){return authPage;}
    public PlayerPage playerPage(){return playerPage;}
}
