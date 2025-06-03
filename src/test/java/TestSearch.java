import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearch extends Init {

    @Test
    public void testSearchForOpenAI() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By searchBoxLocator = By.xpath("//input[@placeholder='Search for and select places']");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
        WebElement searchBox = driver.findElement(searchBoxLocator);  // locate again
        searchBox.sendKeys("Burger King");

        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
        searchBox = driver.findElement(searchBoxLocator);
        searchBox.sendKeys(Keys.ENTER);

        By titleLocator = By.xpath("(//a[@class='card-title-view__title-link'])[1]");
        WebElement firstTitle = wait.until(ExpectedConditions.presenceOfElementLocated(titleLocator));

        String text = firstTitle.getText();
        assertTrue(text.contains("Burger King"), "First title should contain 'Burger King'");
    }
}