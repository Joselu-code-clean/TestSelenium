package com.selenium.examenSelenium.pageOrder;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.examenSelenium.utils.Utils;

public class Amazon {

	private WebDriver driver;
	
    public Amazon(WebDriver driver) {
        this.driver = driver;
    }

    public void printPriceAndDeliveryDate() {
        boolean success = false;
        int retryCount = 0;

        while (!success && retryCount < 2) { // Intenta máximo 2 veces o hasta que tenga éxito
            try {
                // Cambiamos el foco a la nueva ventana abierta de Amazon.
                Utils.switchToTabByNumber(driver, 2);

                // Intentar encontrar los elementos durante un máximo de 5 segundos
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

                // Localizar elementos en la página usando los IDs
                List<WebElement> priceElement = driver.findElements(By.id("apex_desktop_qualifiedBuybox"));
                WebElement deliveryDateElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("deliveryBlockMessage")));

                // Extraer partes del precio (parte entera, decimal, fracción y símbolo) del primer precio que obtenemos del producto.
                String priceWhole = priceElement.get(0).findElement(By.className("a-price-whole")).getText();
                String priceDecimal = priceElement.get(0).findElement(By.className("a-price-decimal")).getText();
                String priceFraction = priceElement.get(0).findElement(By.className("a-price-fraction")).getText();
                String priceSymbol = priceElement.get(0).findElement(By.className("a-price-symbol")).getText();

                // Formatear el precio completo
                String price = String.format("%s%s%s%s", priceWhole, priceDecimal, priceFraction, priceSymbol);

                // Obtener la fecha de entrega
                String deliveryDate = wait.until(ExpectedConditions.visibilityOf(deliveryDateElement)).getText();

                // Imprimir el precio y la fecha de entrega
                System.out.println("Precio: " + price);
                System.out.println("Fecha de entrega: " + deliveryDate);

                success = true; // Todo funcionó bien, sal del bucle
            } catch (Exception e) {
                System.err.println("Error al imprimir precio y fecha de entrega (Intento #" + (retryCount + 1) + "): " + e.getMessage());

                if (retryCount < 1) {
                    // Si no es el último intento, espera 500 milisegundos y recarga la página.
                    driver.navigate().refresh();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                retryCount++;
            }
        }
    }

    
    
    // TODO: No detecta el elemento, seguir averiguando = Solución chacon - Probar Handlers - Si no, palante.
    
    public void searchProductAmazon(String product) {
        try {
            // Paso 1: Comprobar que el formulario está presente y obtenerlo
            By searchFormLocator = By.xpath("//form[@id='nav-search-bar-form']");
            WebElement searchFormElement = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(ExpectedConditions.elementToBeClickable(searchFormLocator));

            // Paso 2: Buscar dentro del formulario el input con input[@id='twotabsearchtextbox']
            By searchBoxLocator = By.xpath(".//div[@class='nav-search-field ']//input[@id='twotabsearchtextbox']");
            WebElement searchBoxElement = searchFormElement.findElement(searchBoxLocator);

            // Paso 3: Escribir el producto en el input y pulsar Enter
            searchBoxElement.sendKeys(product);
            searchBoxElement.submit();
        } catch (Exception e) {
            System.out.println("Error al buscar el producto en Amazon: " + e.getMessage());
        }
    }
    
    public void filterByPrime() {
        try {
            // Creamos el objeto By mediante el xpath
            By primeFilterLink = By.xpath("//li[@id='p_n_free_shipping_eligible/20930980031']//input[@type='checkbox']");
            // Esperar a que el checkbox sea clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(primeFilterLink));
            // Hacer clic mediante Javascript porque es un input
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", checkbox);
        } catch (Exception e) {
        	System.out.println("Error al filtrar por envío gratis por Amazon: " + e.getMessage());
        }
    }

    public void sortLowToHigh() {
        try {
            // Haz clic en el enlace "Precio: de menor a mayor" utilizando el ID proporcionado
            By sortLink = By.xpath("//span[contains(@class, 'a-dropdown-container')]");
            WebElement sortElement = driver.findElement(sortLink);
            sortElement.click();

            // Esperar a que el elemento desplegable esté presente
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("s-result-sort-select")));

            // Seleccionar la opción "Precio: De menor a mayor"
            Select sortDropdown = new Select(driver.findElement(By.id("s-result-sort-select")));
            sortDropdown.selectByValue("price-asc-rank");

        } catch (Exception e) {
            System.out.println("Error al ordenar por precio de menor a mayor: " + e.getMessage());
        }
    }
    
    public void printFirstPageProducts() {
        try {
            By productSelector = By.xpath("//div[starts-with(@cel_widget_id, 'MAIN-SEARCH_RESULTS-')]");
            String productName = "";
            String productPrice = "";
            String productSymbol = "";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<WebElement> productElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productSelector));

            for (int i = 0; i < productElements.size(); i++) {
                WebElement productElement = productElements.get(i);
                // Se validará cada elemento, y si falta alguno de los componentes se establecer un valor por defecto.
                try {
                    // Espera hasta que el elemento específico dentro de cada div productElement sea visible
                    WebElement productNameElement;
                    try {
                        productNameElement = wait.until(ExpectedConditions.visibilityOf(productElement.findElement(By.xpath(".//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']"))));
                        productName = productNameElement.getText();
                    } catch (NoSuchElementException e) {
                        productName = "No encontrado: Nombre del producto";
                    }

                    WebElement productPriceElement;
                    try {
                        productPriceElement = wait.until(ExpectedConditions.visibilityOf(productElement.findElement(By.xpath(".//span[@class='a-price-whole']"))));
                        productPrice = productPriceElement.getText();
                    } catch (NoSuchElementException e) {
                        productPrice = "No encontrado: Precio del producto";
                    }

                    WebElement productSymbolElement;
                    try {
                        productSymbolElement = wait.until(ExpectedConditions.visibilityOf(productElement.findElement(By.xpath(".//span[@class='a-price-symbol']"))));
                        productSymbol = productSymbolElement.getText();
                    } catch (NoSuchElementException e) {
                        productSymbol = "No encontrado: Simbolo del producto";
                    }

                    System.out.printf("Producto: %s%nPrecio: %s %s %n", productName, productPrice, productSymbol);
                } catch (Exception e) {
                    System.out.printf("Error al procesar el producto (%d): %s%n", i, e.getMessage());
                }
                
                // Espera 500 milisegundos antes de pasar al siguiente elemento
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println("Error al imprimir los productos de la primera pantalla: " + e.getMessage());
        }
    }

}
