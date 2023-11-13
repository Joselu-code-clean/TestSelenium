package com.selenium.examenSelenium;

import org.openqa.selenium.WebDriver;

import com.selenium.examenSelenium.pageOrder.Amazon;
import com.selenium.examenSelenium.pageOrder.Google;
import com.selenium.examenSelenium.utils.Utils;

public class Main {
    public static void main(String[] args) {
        String browser = "Chrome";

        // Obtener una instancia de WebDriver según el navegador deseado - Solo tenemos integrado Chrome.
        WebDriver driver = Utils.getWebDriver(browser);

        // Crear instancias de las clases de las páginas
        Google google = new Google(driver);
        Amazon amazon = new Amazon(driver);

        try {
            // Tarea 1: Acceder a Google
            Utils.openPage(driver, "https://www.google.com", true);

            // Aceptar las Cookies
            Utils.acceptCookies(driver, "L2AGLb");

            // Tarea 2: Buscar un producto en Google
            google.searchProductGoogle("compresor eléctrico");

            // Tarea 3: Hacer clic en el enlace de Amazon
            google.clickResultLink();

            // Tarea 4: Imprimir precio y fecha de entrega en Amazon
            // ERROR: Si aparece el detector de robot, incluir Scanner para introducir manualmente por el usuario.
            amazon.printPriceAndDeliveryDate();

            // Tarea 5: Buscar nuevamente en Amazon
         // TODO Este ejercicio no funciona.
            amazon.searchProductAmazon("compresor eléctrico");

            // Tarea 6: Filtrar por Prime en Amazon
            amazon.filterByPrime();

            // Tarea 7: Ordenar por precio de menor a mayor en Amazon
            amazon.sortLowToHigh();

            // Tarea 8: Imprimir los productos de la primera página en Amazon
            amazon.printFirstPageProducts();

        } catch (Exception e) {
            // Manejar cualquier excepción y mostrar un mensaje de error
            System.err.println("Error durante la ejecución: " + e.getMessage());
        } finally {
            // Cerrar el navegador al final
            driver.quit();
        }
    }
}
