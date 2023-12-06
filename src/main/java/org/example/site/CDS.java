package org.example.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CDS {
    private ChromeDriver chromeDriver = new ChromeDriver();
    private WebElement marshList;
    private List<WebElement> listOfBus;
    private WebElement findButton;

    public CDS() {
        chromeDriver.get("https://m.cdsvyatka.com/");
        this.marshList = chromeDriver.findElement(new By.ByXPath("//*[@id=\"marshlist\"]"));
        this.listOfBus = chromeDriver.findElements(new By.ByXPath("//*[@id=\"marshlist\"]/option"));
        this.findButton = chromeDriver.findElement(new By.ByXPath("//*[@type=\"submit\"][@method=\"GET\"]"));
    }

    public void getMarshrut() {
        WebElement marshrut = listOfBus.get(new Random().nextInt(0, listOfBus.size()));
        System.out.println(marshrut.getText());

        selectBus(marshrut);

        List<String> stops = getListOfStops(chromeDriver.findElements(new By.ByXPath("/html/body/a")));
        for (String stop : stops) {
            System.out.println(stop);
        }

        chromeDriver.quit();
    }

    private void selectBus(WebElement bus) {
        marshList.click();
        try {
            Thread.sleep(1000);
        } catch (Exception thrown) {

        }
        bus.click();
        try {
            Thread.sleep(1000);
        } catch (Exception thrown) {

        }
        findButton.submit();
        try {
            Thread.sleep(1000);
        } catch (Exception thrown) {

        }
    }

    private List<String> getListOfStops(List<WebElement> stops) {
        List<String> stopsString = new ArrayList<>();
        for (var stop : stops) {
            stopsString.add(stop.getText());
        }
        return stopsString;
    }
}
