package com.testslotegrator.automation.pages;

import org.openqa.selenium.By;

public class PlayerPage {

    public By adminUser = By.xpath(".//*[contains(text(),\"admin1\")]");
    public By playersTable = By.xpath(".//*[text()=\"Players online / total\"]");
    public By playersTableTitle = By.xpath(".//*[text()=\" Player management\t\t\"]");
    public By usernameColumn = By.xpath(".//*[text()=\"Username\"]");
    public By tableAfterSort = By.xpath(".//*[text()=\"1\"]/parent::*//*[text()=\"00111niki1323sv412378962\"]");
}
