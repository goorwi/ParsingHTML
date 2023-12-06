package org.example.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Otzovik {
    private ChromeDriver chromeDriver;
    List<WebElement> films;

    public Otzovik() {
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://otzovik.com");
        isCaptcha();

    }

    private void isCaptcha() {
        WebElement captcha = chromeDriver.findElement(new By.ByXPath("//*/h1"));
        while (captcha != null && Objects.equals(captcha.getText(), "Вы робот?")) {
            waitTime(10);
            captcha = chromeDriver.findElement(new By.ByXPath("//*/h1"));
        }
    }

    public void write() {
        selectFilm();
        waitTime();
        writeComment();
        waitTime();
        System.out.println("Отзыв опубликован(вы не заметили как была нажата кнопка \"опубликовать\", но она была нажата!)");


        chromeDriver.quit();
    }

    private void selectFilm() {
        //жмать кнопку "написать отзыв" и писать название фильма
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"header-main\"]/a[2]")).click();
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"tproduct_line\"]/input")).sendKeys("Сериал \"Викинги\" (2013)");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"tproduct_line\"]/button")).click();
        waitTime();
    }

    private void writeComment() {
        //ставим оценку
        chromeDriver.findElement(new By.ByXPath("//*[@class=\"star\"][5]")).click();
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*[@id=\"content_title\"]")).sendKeys("Потрясающий сериал, с талантливыми актёрами!");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*/textarea[2]")).sendKeys("Данный сериал поражает своей живописностью и невероятной игрой актёров." +
                "бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла бла" +
                "я не умею писать отзывы простите, у меня слишком ограничена для этого фантазия\n" +
                "но в целом сериал шикарный, мне дико нравится. я его уже почти досмотрел.");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*/textarea[@name=\"content_pros\"]")).sendKeys("много");
        waitTime();
        chromeDriver.findElement(new By.ByXPath("//*/textarea[@name=\"content_cons\"]")).sendKeys("нету");
        waitTime();
        chromeDriver.findElements(new By.ByXPath("//*[@class=\"button2023\"]")).get(0).click();
        waitTime();
        //chromeDriver.findElement(new By.ByXPath("//*/textarea[@class=\"ta2023\"]")).sendKeys("много");
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
