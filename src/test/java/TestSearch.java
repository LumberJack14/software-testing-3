import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSearch extends Init{

    @Test
    public void testSearchForOpenAI() throws InterruptedException {
        driver.get("https://yandex.com/maps");

        WebElement searchBox = driver.findElement(By.className("input__control _bold"));
        searchBox.sendKeys("");
        searchBox.submit();

        Thread.sleep(2000); // wait for results (use WebDriverWait in real tests)

        WebElement businessTitle = driver.findElement(By.className("search-business-snippet-view__title"));
        String titleText = businessTitle.getText();
        assertTrue(titleText.contains("Burger King"), "Title should contain 'Burger King'");
    }
}