package ru.yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Art
 * @version 1.0
 * Класс-фабрика страницы который инкапсулирует в себе все поля и методы работы со страницей.
 *
 */
public class PageFactoryMarket {

    /**
     * Драйвер для работы с браузером Chrome
     */
    private WebDriver chromeDriver;

    /**
     * Объект для работы с ожиданиями
     */
    private WebDriverWait wait;

    /**
     * Каталог товаров
     */
//    @FindBy(how = How.XPATH, using = "//button[@aria-label='Каталог'][@id='catalogPopupButton']")
    @FindBy(how = How.XPATH, using = "//button[@id='catalogPopupButton']")
    WebElement catalog;

    /**
     * Кнопка поиска товаров
     */
    @FindBy(how = How.XPATH, using = "//button[@type='submit'][@data-r='search-button']//span[text()='Найти']/..")
    WebElement searchButton;

    /**
     * Поле поиска
     */
    @FindBy(how = How.XPATH, using = "//input[@type='text'][@id='header-search']")
    WebElement searchField;

    /**
     * Раздел фильтров по брендам
     */
    @FindBy(how = How.XPATH, using = "//div[@data-zone-name='search-filter']//legend[text()='Производитель']/..")
    WebElement allBrandsPartition;

    /**
     * Хранит результаты поиска
     */
    @FindBy(how = How.XPATH, using = "//article[@data-autotest-id='product-snippet'][@data-zone-name='snippet-card']")
    List<WebElement> results;

    public PageFactoryMarket(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        wait = new WebDriverWait(chromeDriver, 30);
    }

    /**
     * Переходит в каталог маркета
     */
    public void goToCatalog() {
        catalog.click();
    }

    /**
     * Устанавливает категории и подкатегории товаров
     */
    public void setCategories(String categories, String subcategories) {
        WebElement cat = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@data-zone-name='category-link']//span[text()='" + categories + "']/..")));
        cat.click();
        WebElement subcat = chromeDriver.findElement(By.xpath("//li//a[text()='" + subcategories + "']"));
        subcat.click();
    }

    /**
     * Метод устанавливает диапазон цен для поиска товаров.
     */
    public void setPriceDiapason(String priceFrom, String priceTo) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-filter-id='glprice']//input[@name='Цена от']"))).sendKeys(priceFrom);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-filter-id='glprice']//input[@name='Цена до']"))).sendKeys(priceTo);
    }

    /**
     * Метод устанавливает в фильтр выбранный бренд.
     */
    public void setBrand(String brand) {
        WebElement elem = allBrandsPartition.findElement(By.xpath(".//ul//li//span[text()='" + brand + "']/../.."));
        elem.click();
    }

    /**
     * Метод устанавливает количество элементов на странице.
     * Предварительно дожидается исчезновения блокирующего элемента после применения фильтров.
     */
    public void selectCountOnPage(String selectedCount) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@role='main'][@aria-label='Результаты поиска']/div[@data-tid]")));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-apiary-widget-id='/content/pager']//button[contains(@id,'dropdown-control')]")));
        WebElement counts = chromeDriver.findElement(By.xpath("//button[text()='Показывать по " + selectedCount + "']"));
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(button).click(button).click(counts).build().perform();
    }

    /**
     * Метод возвращает результаты поиска.
     * Предварительно дожидается исчезновения блокирующего элемента после применения фильтров.
     *
     * @return List<WebElement> список результатов поиска
     */
    public List<WebElement> getResults() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@role='main'][@aria-label='Результаты поиска']/div[@data-tid]")));
        return results;
    }

    /**
     * Метод возвращает результаты поиска.
     * Предварительно дожидается исчезновения блокирующего элемента после применения фильтров.
     *
     * @return List<WebElement> список результатов поиска
     */
    public List<WebElement> searchInMarketFromListElementsById(List<WebElement> list, int id) {
        WebElement title = list.get(id).findElement(By.xpath(".//h3[@data-zone-name='title']//a[@title]"));
        String titleOfProduct = title.getAttribute("title");
        searchField.sendKeys(titleOfProduct);
        searchButton.click();
        return getResults();
    }

    /**
     * Метод title искомого элемента.
     *
     * @return String title указанного элемента
     */
    public String getTitle(WebElement element) {
        return element.findElement(By.xpath(".//h3[@data-zone-name='title']//a[@title]")).getAttribute("title");
    }
}
