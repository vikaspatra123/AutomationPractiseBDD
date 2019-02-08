package automationPractise;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    static WebDriver driver;
    String baseUrl = "http://automationpractice.com";

    // Pre condition for test scenario
    // open the base url

    @Before
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver 2");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    //post conditions for test scenarios
    // close the browser
    @After
    public void quitBrowser() {
        driver.close();

    }

    @Given("^User is on the home page$")
    public void userOnHomePage() {
        Assert.assertTrue(driver.getTitle().equals("My Store"));
        Assert.assertTrue(driver.findElement(By.cssSelector("#search_query_top")).isDisplayed());

    }

    @When("^User selects the sign in link$")
    public void userSelectsTheSignInLink() {
        driver.findElement(By.cssSelector(".login")).click();

    }

    @Then("^User should be in the login page$")
    public void verifyUserIsOnTheLogin_page() {
        Assert.assertTrue(driver.findElement(By.cssSelector("form[id = 'login_form']")).isDisplayed());

    }

    @When("^User enters email as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void userEntersLoginDetails(String email, String password) {
        driver.findElement(By.cssSelector("input[id='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[id ='passwd']")).sendKeys(password);
    }

    @And("^User selects Sign in$")
    public void selectSignIn() {
        //@TODO scroll to the element and click
        driver.findElement(By.cssSelector("#SubmitLogin")).click();
    }

    @Then("^User should be logged in successfully$")
    public void isUserLoggedIn() {
        Assert.assertTrue(driver.findElement(By.cssSelector(".info-account")).isDisplayed());
    }

    @And("^User should see his name as \"([^\"]*)\"$")
    public void user_should_see_his_name_as_something(String userName) {
        //Assert.assertEquals(userName, true, driver.findElement(By.cssSelector(".account")).getText());
        String name = driver.findElement(By.cssSelector(".account")).getText();
        Assert.assertTrue(name.equals(userName));
    }

    @Then("^User should see error message as \"(.*)\"$")
    public void userWillSeeErrorMessage(String errorMsg) {
        String diplayedMSG = driver.findElement(By.xpath("//div[@class='alert alert-danger']//li")).getText();
        System.out.println(diplayedMSG);
        Assert.assertTrue(diplayedMSG.contains(errorMsg));
    }


    @When("^User selects T-shirt tab$")
    public void userSelectsTshirtTab() {
        //WebElement tshirtTab = driver.findElement(By.xpath("//a[contains(text(), 'Women')]/following-sibling::ul[@class='submenu-container clearfix first-in-line-xs']//a[contains(text(), 'T-shirts')]"));
        WebElement tshirtTab = driver.findElement(By.xpath("//div[@id='block_top_menu']//ul[@class='submenu-container clearfix first-in-line-xs']//a[contains(text(), 'T-shirts')]"));
        //driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?id_category=5&controller=category']"));
        //tshirtTab.click();
        Assert.assertTrue(tshirtTab.isDisplayed());
    }

    @Then("^User will be navigated to Tshirt page$")
    public void userVerifiesTshirtpage() {
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("T-shirts"));
    }

    @When("^User selects the required Tshirt$")
    public void userSelectsRequiredTshirt() {
        driver.findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']")).click();
    }

    @Then("^User will be navigated to the T shirt description page$")
    public void userVerifiesTshirtDescription() {
        String pageContent = driver.findElement(By.xpath("//div[@class='pb-center-column col-xs-12 col-sm-4']")).getText();
        Assert.assertTrue(pageContent.contains("Faded Short Sleeve T-shirts"));
    }

    @When("^User selects the quantity \"(.*)\" and size \"(.*)\"of the T Shirt$")
    public void userSelectsQuantityAndSizeOftshirts(String quantity, String size) {
        driver.findElement(By.id("quantity_wanted")).sendKeys(quantity);
        Select select = new Select(driver.findElement(By.xpath("//select[@id='group_1']")));
        select.selectByVisibleText(size);
    }

    @Then("^User verifies quantity \"(.*)\" and size has been updated$")
    public void userverifiesUpdatesQuantityAndSize(String quantity) {
        String displayQuantity = driver.findElement(By.id("quantity_wanted")).getAttribute("value");
        Assert.assertTrue(displayQuantity.equals(quantity));
        WebElement sizeM = driver.findElement(By.xpath("//select[@id='group_1']//option[@value='2']"));
        Assert.assertTrue(sizeM.isDisplayed());

    }

    @When("^User selects 'Add to cart'$")
    public void userClicksOnAddToCartButton() {
        driver.findElement(By.id("User selects 'Add to cart'")).click();
    }

    @Then("^User will see the pop up window with the updated basket$")
    public void userVerifiesUpdatedBasket() {
        String successMessage = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']")).getText();
        Assert.assertTrue(successMessage.contains("Product successfully added to your shopping cart"));
    }


}
