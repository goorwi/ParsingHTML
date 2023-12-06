package org.example.site;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

public class Pictures {
    private ChromeDriver chromeDriver;

    public Pictures() {
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://yandex.ru/images");

    }

    private void scrollPage() {
        JavascriptExecutor js = chromeDriver;
        while (chromeDriver.findElements(new By.ByXPath("//*[@class=\"SimpleImage-Image SimpleImage-Image_clickable\"]")).size() < 200) {
            js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
                    + "document.body.scrollHeight,document.documentElement.clientHeight));");
        }
    }

    public void start() {
        searchField();
        downloadKitty();
        chromeDriver.quit();
    }

    private void searchField() {
        chromeDriver.findElement(new By.ByXPath("//*[@name=\"text\"]")).sendKeys("милые котята");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"websearch-button__text mini-suggest__button-text\"]")).click();
        waitTime();
        scrollPage();
    }

    private void downloadKitty() {
        List<String> kitties = chromeDriver.findElements(new By.ByXPath("//*[@class=\"SimpleImage-Image SimpleImage-Image_clickable\"]")).stream().map(x -> x.getAttribute("src")).toList();
        for (String kitty : kitties) {
            try {
                BufferedImage img = ImageIO.read(new URL(kitty));
                File file = new File(String.format("src/main/java/org/example/котята/%s.png", kitties.indexOf(kitty)));
                if (!file.exists()) {
                    file.createNewFile();
                }
                ImageIO.write(img, "png", file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
