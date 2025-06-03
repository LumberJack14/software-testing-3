import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRelative extends Init{
    String getClipboardText() throws Exception {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }
    @Test
    public void TestShare() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By searchBoxLocator = By.xpath("//input[@class='input__control _bold']");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
        WebElement searchBox = driver.findElement(searchBoxLocator);  // locate again
        searchBox.sendKeys("Samara");

        wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
        searchBox = driver.findElement(searchBoxLocator);
        searchBox.sendKeys(Keys.ENTER);

        WebElement shareBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Share']")));
        shareBtn.click();



        By shareLinkLocator = By.xpath("//div[@aria-label='Sharing link']//div[contains(@class, 'card-share-view__text')]");
        wait.until(ExpectedConditions.presenceOfElementLocated(shareLinkLocator));
        WebElement shareLinkElement = driver.findElement(shareLinkLocator);
        wait.until(ExpectedConditions.visibilityOf(shareLinkElement));
        String link = shareLinkElement.getText();
        System.out.println(link);

        By coordsLocator = By.xpath("//div[@aria-label='Coordinates']");
        wait.until(ExpectedConditions.elementToBeClickable(coordsLocator));
        WebElement coordsElement = driver.findElement(coordsLocator);
        wait.until(ExpectedConditions.visibilityOf(coordsElement));
        int tries = 0;
        while (tries < 3) {
            try {
                coordsElement.click();
                break;
            }
            catch (Exception e) {tries++;}
        }

        assertTrue(coordsElement.getText().contains("53.195878, 50.100202"), "Those are just not the correct coords. " + coordsElement.getText());

        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", link);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By titleLocator = By.xpath("(//h1[@class='card-title-view__title'])");
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(titleLocator));

        String text = title.getText();
        assertTrue(text.contains("Samara"), "First title should contain 'Samara'");
    }
    @Test
    public void TestNearby() throws InterruptedException {

    }
}
