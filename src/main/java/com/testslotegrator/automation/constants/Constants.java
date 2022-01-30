package com.testslotegrator.automation.constants;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
public class Constants {

    private Constants() {
    }

    public static final Config CONFIG = ConfigFactory.load("application.conf");
    public static final String BASE_URL_API = CONFIG.getString("url.api");
    public static final String BASE_URL_UI = CONFIG.getString("url.ui");

    public static final String LOGIN = CONFIG.getString("login.admin.ui");
    public static final String PASSWORD = CONFIG.getString("password.admin.ui");
}
