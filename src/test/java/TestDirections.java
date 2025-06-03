import org.junit.jupiter.api.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class TestDirections extends Init {
    @Test
    public void TestTravelMode() throws InterruptedException {
        System.out.println("Started Directions Search test.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@placeholder='Search for and select places']")));
        searchInput.sendKeys("Samara");
        searchInput.sendKeys(Keys.ENTER);

        WebElement directionsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'action-button-view') and contains(@class, '_type_route')]//button")));
        directionsButton.click();

        WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'route-field-view__input')]//input[@placeholder='From']")));
        fromInput.sendKeys("Penza");
        fromInput.sendKeys(Keys.ENTER);

        WebElement routeList = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'route-list-view__list') and @role='list']")));

        Thread.sleep(3000);

        List<WebElement> routeItems = routeList.findElements(By.xpath(".//div[@role='listitem']"));

        if (routeItems.size() > 0) {
            System.out.println(" Route options found: " + routeItems.size());
        } else {
            System.out.println(" No route options found.");
        }
    }
}
