package com.selenium.examenSelenium.utils;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
    public static WebDriver getWebDriver(String browser) {
        WebDriver driver = null;
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "D:\\Documentos\\ProyectosEclipse\\DriversWebSelenium\\DriversWebSelenium\\Chrome\\chromedriver-win64\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "ruta_del_controlador/geckodriver.exe");
                driver = new FirefoxDriver();
            }
            // Agregar más casos para otros navegadores si es necesario
        } catch (Exception e) {
            System.err.println("Error al iniciar el navegador: " + e.getMessage());
        }
        return driver;
    }
	
    public static void openPage(WebDriver driver, String url, boolean maximize) {
        try {
            driver.get(url);
            if (maximize) {
                driver.manage().window().maximize();
            }
        } catch (Exception e) {
            System.err.println("Error al abrir la página: " + e.getMessage());
        }
    }
    
    public static WebElement waitForElement(WebDriver driver, By locator) {
        // Espera a que un elemento esté presente en la página y devuelve el elemento cuando se encuentra
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    public static void acceptCookies(WebDriver driver, String acceptCookiesID) {
        try {
            By acceptCookiesButton = By.id(acceptCookiesID);
            WebElement cookiesButtonElement = driver.findElement(acceptCookiesButton);
            cookiesButtonElement.click();
        } catch (Exception e) {
            System.err.println("Error al aceptar las cookies: " + e.getMessage());
        }
    }
    
    public static void switchToTabByNumber(WebDriver driver, int windowNumber) {
        // Obtiene los identificadores de todas las ventanas abiertas
        Set<String> allWindowHandles = driver.getWindowHandles();

        int windowCount = allWindowHandles.size();

        if (windowNumber >= 1 && windowNumber <= windowCount) {
            // Obtén el identificador de la ventana correspondiente al número
            String[] windowHandles = allWindowHandles.toArray(new String[0]);
            String targetWindowHandle = windowHandles[windowNumber - 1];

            // Cambia al identificador de la ventana deseada
            driver.switchTo().window(targetWindowHandle);
        } else {
            System.out.println("No se ha podido cambiar de ventana: " + windowNumber);
        }
    }


}
