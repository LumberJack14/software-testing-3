import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearch extends Init{

    @Test
    public void TestSearch() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Поиск и выбор мест']")));
        new Actions(driver).sendKeys(searchBox, "Burger King").perform();
        searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Поиск и выбор мест']")));
        new Actions(driver).sendKeys(searchBox, Keys.ENTER).perform();


        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement firstTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@class='card-title-view__title-link'])[1]")));

        String text = firstTitle.getText();
        assertTrue(text.contains("Бургер Кинг"), "First title should contain 'Бургер Кинг'");
    }
}