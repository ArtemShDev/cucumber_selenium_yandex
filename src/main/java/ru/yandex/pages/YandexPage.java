package ru.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexPage {

    /**
     * Драйвер для работы с браузером Chrome
     */
    private WebDriver driver;

    public YandexPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод обеспечивает переход по ссылке, предварительно создавая ее
     */
    public boolean goToPage(String title) {
        WebElement link = driver.findElement(
                By.xpath("//li[@class='services-new__list-item']//a[@data-id='market']"));
        link.click();
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
            if (driver.getTitle().contains(title)) {
                return true;
            }
        }
        return false;
    }

}
