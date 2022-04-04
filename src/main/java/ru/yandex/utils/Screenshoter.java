package ru.yandex.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.nio.file.Files;

/**
 * @author Art
 * @version 1.0
 * Вспомогательный класс для создания скриншотов страницы.
 *
 */
public class Screenshoter {

    @Attachment
    public static byte[] getScreen(WebDriver driver){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            return Files.readAllBytes(screenshot.toPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Attachment
    public static byte[] getScreen(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            return Files.readAllBytes(screenshot.toPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }
}
