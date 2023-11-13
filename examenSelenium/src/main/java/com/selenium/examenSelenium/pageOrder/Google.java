package com.selenium.examenSelenium.pageOrder;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Google {

	private WebDriver driver;

    public Google(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProductGoogle(String product) {
        try {        	
            By searchBox = By.xpath("//textarea[@class='gLFyf']");
            WebElement searchBoxElement = driver.findElement(searchBox);
            searchBoxElement.sendKeys(product);
            searchBoxElement.submit();
        } catch (Exception e) {
        	System.out.println("Error al buscar el producto en Google: " + e.getMessage());
        }
    }
    
    public void clickResultLink() {
        try {
            By resultList = By.xpath(".//a[contains(@href, 'https://www.amazon.es')]");
            
            // Espera a que al menos un enlace con "https://www.amazon.es" esté presente en la lista de resultados
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
            List<WebElement> links = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultList));
            
            if (!links.isEmpty()) {
                // Utiliza JavascriptExecutor para hacer clic en el enlace
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", links.get(0));
            }
        } catch (Exception e) {
        	System.out.println("Error al hacer clic en el enlace de resultado: " + e.getMessage());
            
        }
    }
    
}
