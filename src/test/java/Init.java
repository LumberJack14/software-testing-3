import com.example.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.Duration;

public class Init {
    public WebDriver driver;
    public WebDriverWait wait;
    public final String BASE = "https://yandex.com/maps/";
    public String browser = "firefox"; // firefox

    @BeforeEach
    public void setUp() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en-US"); // or your desired language
        FirefoxOptions options = new FirefoxOptions().setProfile(profile);
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE);
        System.out.println("Started test in browser: " + browser);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}