package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driver.get() == null) {

            String browser = ConfigReader.getProperty("browser");

            switch (browser.toLowerCase().trim()) {

                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    driver.set(new EdgeDriver());
                    break;

                default:
                    ChromeOptions options = new ChromeOptions();

                   // options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--incognito");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");

                    driver.set(new ChromeDriver(options));
                    break;
            }
        }

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}