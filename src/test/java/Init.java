import com.example.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.Duration;

public class Init {
    public WebDriver driver;
    public WebDriverWait wait;
    public final String BASE = "https://yandex.com/maps/";

    public String browser = "chrome"; // firefox
    public boolean incognito = false;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.initializeDriver(browser, incognito);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE);
        System.out.println("Started test in browser: " + browser + " | Incognito: " + incognito);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}