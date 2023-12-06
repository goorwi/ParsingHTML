package org.example.site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;

public class Forum {
    private ChromeDriver chromeDriver = new ChromeDriver();
    private WebElement onlyTheme;
    private WebElement searchField;
    private WebElement subForum;
    private List<WebElement> subForums;
    private WebElement findButton;

    public Forum() {
        chromeDriver.get("https://forum.otzyv.ru/allforums.php");
        this.findButton = chromeDriver.findElements(new By.ByXPath("//*[@type=\"submit\"][@class=\"btn_sub\"]")).get(0);
        this.subForum = chromeDriver.findElements(new By.ByXPath("//*[@class=\"searchitems\"]//*[@name=\"f\"]")).get(0);
        this.searchField = chromeDriver.findElements(new By.ByXPath("//*[@class=\"searchitems\"]//*[@name=\"s\"]")).get(0);
        this.onlyTheme = chromeDriver.findElement(new By.ByXPath("//*[@class=\"searchitems\"]//*[@name=\"onlytheme\"]"));
        this.subForums = chromeDriver.findElements(new By.ByXPath("//*[@id=\"mainCol\"]/div[4]/form/div[4]/span/span/select/option"));
    }

    public void getForums() {
        fillSearch();
        showForums();
        chromeDriver.quit();
    }

    private void fillSearch() {
        searchField.sendKeys("Россия");
        waitTime();
        onlyTheme.click();
        waitTime();

        List<String> subForums = this.subForums.stream().map(WebElement::getText).toList();
        selectSubForum(this.subForums.get(subForums.indexOf("Новый Год")));

        findButton.submit();
    }

    private void selectSubForum(WebElement subForum) {
        this.subForum.click();
        waitTime();
        subForum.click();
        waitTime();
    }

    private void waitTime() {
        try {
            Thread.sleep(1000);
        } catch (Exception thrown) {

        }
    }

    private void showForums() {
        List<String> forums = chromeDriver.findElements(new By.ByXPath("//*/h2/a")).stream().map(WebElement::getText).toList();

        System.out.printf("Найдено %s форумов%n", forums.size());
        for (String forum : forums) {
            System.out.println(forum);
        }
    }
}
