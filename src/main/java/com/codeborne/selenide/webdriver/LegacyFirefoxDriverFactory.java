package com.codeborne.selenide.webdriver;

import com.codeborne.selenide.Browser;
import com.codeborne.selenide.Config;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

class LegacyFirefoxDriverFactory extends FirefoxDriverFactory {

  @Override
  boolean supports(Config config, Browser browser) {
    return browser.isLegacyFirefox();
  }

  @Override
  WebDriver create(Config config, Proxy proxy) {
    String logFilePath = System.getProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, logFilePath);
    FirefoxOptions firefoxOptions = createLegacyFirefoxOptions(config, proxy);
    return new FirefoxDriver(firefoxOptions);
  }

  FirefoxOptions createLegacyFirefoxOptions(Config config, Proxy proxy) {
    FirefoxOptions firefoxOptions = createFirefoxOptions(config, proxy);
    firefoxOptions.setLegacy(true);
    config.browserOptionsInterceptors().firefoxOptionsInterceptor.afterSelenideChangesOptions(firefoxOptions);
    return firefoxOptions;
  }
}
