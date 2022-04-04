package tests.steps;

import ru.yandex.Steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class StepDefinition extends Steps {

    @Given("открыть браузер")
    public void openBrowser() {
        openBrowserStep();
    }

    @When("выполнить переход к следующей странице {string}")
    public void goToPage(String url) {
        goToPageStep(url);
    }

    @When("перейти по ссылке с текстом {string}")
    public void goPageWithText(String textTitle) {
        goPageWithTextStep(textTitle);
    }

    @When("перейти в каталог")
    public void goToCatalog() {
        goToCatalogStep();
    }

    @And("установить категории товаров: {string} {string}")
    public void setCategories(String categories, String subcategories) {
        setCategoriesStep(categories, subcategories);
    }

    @And("установить ценовой диапазон: {string} {string}")
    public void setPriceDiapason(String priceFrom, String priceTo) {
        setPriceDiapasonStep(priceFrom, priceTo);
    }

    @And("установить бренды:")
    public void setBrands(List<String> brands) {
        setBrandsStep(brands);
    }

    @And("установить количество элементов на странице: {string}")
    public void selectCountOnPage(String counts) {
        selectCountOnPageStep(counts);
    }

    @Then("проверить количество элементов в результате поиска: {string}")
    public void compareCountOnPage(String count) {
        compareCountOnPageStep(count);
    }

    @When("искать в каталоге элемент {int} из результатов поиска и сравнить результаты по title")
    public void findElementByIdInResultsAndFindAndCompareTitles(int id) {
        findElementByIdInResultsAndFindAndCompareTitlesStep(id);
    }

    @And("закрыть браузер")
    public void closeBrowser() {
        closeBrowserStep();
    }
}
