package com.testslotegrator.automation.pages;

import org.openqa.selenium.By;

public class AuthPage {

    public By usernameField = By.xpath(".//*[@id=\"UserLogin_username\"]");
    public By passwordField = By.xpath(".//*[@id=\"UserLogin_password\"]");
    public By signInButton = By.xpath(".//input[@type=\"submit\"]");
}
