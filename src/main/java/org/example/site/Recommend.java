package org.example.site;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Recommend {
    private ChromeDriver chromeDriver;

    public Recommend() {
        this.chromeDriver = new ChromeDriver();
        chromeDriver.get("https://irecommend.ru/");
    }

    public void logIn() {
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"headerLoginButton\"]")).click();
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"button\"]")).click();
        waitTime();
        fillData();
        waitTime();
        //chromeDriver.findElement(new By.ByXPath("//*[@id=\"edit-submit\"]")).click();
        waitTime();
        chromeDriver.quit();
    }

    private void fillData() {
        chromeDriver.findElement(new By.ByXPath("//*[@id=\"edit-name-wrapper\"]/input")).sendKeys("goorwi");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@id=\"edit-mail-wrapper\"]/input")).sendKeys("russkih.vlad123@gmail.com");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@id=\"edit-pass-wrapper\"]/div[1]/input")).sendKeys("CoolPASS1");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@id=\"edit-pass-wrapper\"]/div[2]/input")).sendKeys("CoolPASS1");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@id=\"edit-reg-policy-wrapper\"]/input")).click();
        waitTime();
        //chromeDriver.findElement(new By.ByXPath("//*[@class=\"recaptcha-checkbox-checkmark\"]")).click();
        //waitTime();
    }

    private void waitTime() {
        try {
            Thread.sleep(1000);
        } catch (Exception thrown) {

        }
    }

    private void waitTime(int x) {
        try {
            Thread.sleep(x);
        } catch (Exception thrown) {

        }
    }
}
