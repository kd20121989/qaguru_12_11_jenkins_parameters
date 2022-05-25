package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.holdBrowserOpen = false;

        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

        String https= "https://";
        String credentials = config.login() + ":" + config.password() + "@";
        String selenoidServer = System.getProperty("selenoidServer", "selenoid.autotests.cloud/wd/hub");
        Configuration.remote = https + credentials + selenoidServer;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Finishing screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
