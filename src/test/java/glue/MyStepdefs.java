package glue;

import io.cucumber.java8.En;
import lombok.extern.apachecommons.CommonsLog;
import org.junit.Assert;

import static com.testslotegrator.automation.constants.Constants.*;

@CommonsLog
public class MyStepdefs extends TestRunner implements En {

    public MyStepdefs() {
        Before(2, TestRunner::setUp);

        After(TestRunner::tearDown);

        Given("^Открыть страницу в браузере$", () -> {
            driver.get(BASE_URL_UI);
        });
        When("^Ввести логин и пароль администратора$", () -> {
            driver.findElement(page.authPage().usernameField).sendKeys(LOGIN);
            driver.findElement(page.authPage().passwordField).sendKeys(PASSWORD);
            driver.findElement(page.authPage().signInButton).click();
        });
        Then("^Открыта админ панель$", () -> {
            Assert.assertEquals("admin1", driver.findElement(page.playerPage().adminUser).getText());
        });
        When("^Открыть таблицу игроков$", () -> {
            driver.findElement(page.playerPage().playersTable).click();
        });
        Then("^Открыта таблица игроков$", () -> {
            Assert.assertEquals("PLAYER MANAGEMENT", driver.findElement(page.playerPage().playersTableTitle).getText());
        });
        When("^Отсортировать таблицу$", () -> {
            driver.findElement(page.playerPage().usernameColumn).click();
        });
        Then("^Таблица отсортирована$", () -> {
            Assert.assertEquals("00111niki1323sv412378962", driver.findElement(page.playerPage().tableAfterSort).getText());
        });
    }
}
