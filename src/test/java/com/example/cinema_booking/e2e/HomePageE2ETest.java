package com.example.cinema_booking.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HomePageE2ETest {

    private WebDriver driver;

    @BeforeAll
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @AfterAll
    void tearDown() {
        driver.quit();
    }

    @Test
    void shouldDisplayHomePageHappyPath() {
        driver.get("http://localhost:8080/");

        assertEquals("Sigma Cinema", driver.getTitle());

        WebElement header = driver.findElement(By.tagName("h1"));
        assertEquals("Sigma Cinema", header.getText());

        WebElement nav = driver.findElement(By.tagName("nav"));
        assertTrue(nav.getText().contains("Filmy"));
        assertTrue(nav.getText().contains("Seanse"));

        WebElement repertuar = driver.findElement(By.tagName("h2"));
        assertEquals("Repertuar", repertuar.getText());

        WebElement footer = driver.findElement(By.tagName("footer"));
        assertTrue(footer.getText().contains("Sigma Cinema"));
    }
}
