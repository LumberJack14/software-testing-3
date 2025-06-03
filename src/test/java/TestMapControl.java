import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMapControl extends Init {
    @Test
    public void TestZoom() throws InterruptedException {
        WebElement zoomInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Zoom in']")));
        zoomInBtn.click();

        WebElement scaleLabelZoomedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'map-scale-line__label')]")));
        String zoomedInText = scaleLabelZoomedIn.getText();
        assertTrue(zoomedInText.contains("200 km"), "Expected '200 km' after zoom in");

        WebElement zoomOutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Zoom out']")));
        zoomOutBtn.click();

        wait.until(driver1 -> {
            WebElement scale = driver1.findElement(By.xpath("//div[contains(@class, 'map-scale-line__label')]"));
            return scale.getText().contains("500 km");
        });
        scaleLabelZoomedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'map-scale-line__label')]")));
        String zoomedOutText = scaleLabelZoomedIn.getText();
        assertTrue(zoomedOutText.contains("500 km"), "Expected '500 km' after zoom out");
    }

    @Test
    public void TestDrag() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String originalURL = driver.getCurrentUrl();

        WebElement map = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.map-container")));

        Actions actions = new Actions(driver);
        actions.moveToElement(map)
                .clickAndHold()
                .moveByOffset(100, 0)
                .pause(Duration.ofMillis(500))
                .release()
                .perform();

        wait.until(driver1 -> !driver1.getCurrentUrl().equals(originalURL));

        String newURL = driver.getCurrentUrl();
        assertNotEquals(originalURL, newURL, "Map coordinates in URL should change after drag.");
    }
}
