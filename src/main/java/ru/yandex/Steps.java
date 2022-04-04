package ru.yandex;

import ru.yandex.pages.PageFactoryMarket;
import ru.yandex.pages.YandexPage;
import ru.yandex.utils.Screenshoter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Steps {

    private DriverManager driverManager = new DriverManager();

    private YandexPage pageYandex = new YandexPage(driverManager.getDriver());

    private PageFactoryMarket pageFactoryMarket =
            PageFactory.initElements(driverManager.getDriver(), PageFactoryMarket.class);

    public void openBrowserStep() {
        driverManager.getDriver();
    }

    public void closeBrowserStep() {
        driverManager.getDriver().quit();
    }

    public void goToPageStep(String url) {
        driverManager.getDriver().get(url);
    }

    public void goPageWithTextStep(String textTitle) {
        if(!pageYandex.goToPage(textTitle)){
            Screenshoter.getScreen(driverManager.getDriver());
            Assert.fail("Страница " + textTitle + " не найдена");
        }
    }

    public void goToCatalogStep() {
        pageFactoryMarket.goToCatalog();
    }

    public void setCategoriesStep(String categories, String subcategories) {
        pageFactoryMarket.setCategories(categories, subcategories);
    }

    public void setPriceDiapasonStep(String priceFrom, String priceTo) {
        pageFactoryMarket.setPriceDiapason(priceFrom, priceTo);
    }

    public void setBrandsStep(List<String> brands) {
        brands.forEach(brand -> pageFactoryMarket.setBrand(brand));
    }

    public void selectCountOnPageStep(String count) {
        pageFactoryMarket.selectCountOnPage(count);
    }

    public void compareCountOnPageStep(String count) {
        List<WebElement> searchResult = pageFactoryMarket.getResults();
        assertEquals("Количество элементов в результате поиска не равно " + count,
                Integer.parseInt(count), searchResult.size());
    }

    public void findElementByIdInResultsAndFindAndCompareTitlesStep(int id) {
        List<WebElement> searchResult = pageFactoryMarket.getResults();
        String titleElement = pageFactoryMarket.getTitle(searchResult.get(id));
        List<WebElement> searchResultNew = pageFactoryMarket.searchInMarketFromListElementsById(searchResult, id);
        String titleElementNew = pageFactoryMarket.getTitle(searchResultNew.get(id));
        assertEquals("Результат поиска " + titleElementNew + " не совпадает с ожидаемым " + titleElement,
                titleElement, titleElementNew);
        Screenshoter.getScreen(driverManager.getDriver(), searchResultNew.get(id));
    }

}
