package com.selenium.seleniumBasico;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchShadowRootException;

public class ListaDeEjercicios {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Documentos\\ProyectosEclipse\\DriversWebSelenium\\DriversWebSelenium\\Chrome\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Ejercicio 1 - OK
//        abrirSitioWeb(driver, "https://www.estadiodeportivo.com");

        // Ejercicio 2 - OK
//        aceptarCookies(driver);
//        clickIconoEscudoBetis(driver);
//        listarNoticiasPrincipales(driver);

        // Ejercicio 3 - OK
//        aceptarCookies(driver);
//        clickIconoEscudoBetis(driver);
//        listarPrimeraYUltimaNoticiaCarrusel(driver);

        // Ejercicio 4 - OK
//        abrirSitioWeb(driver, "https://demoqa.com/automation-practice-form");
//        rellenarFormularioPorId(driver);

        // Ejercicio 5 - OK
//        abrirSitioWeb(driver, "https://demoqa.com/automation-practice-form");
//        rellenarFormularioPorXpath(driver);

        // Ejercicio 6 - OK
//        abrirSitioWeb(driver, "https://demoqa.com/automation-practice-form");
//        rellenarFormularioPorXpathLabel(driver);

        // Ejercicio 7 - OK
//        abrirSitioWeb(driver, "https://demoqa.com/alerts");
//        hacerClickEnBotonesYCerrarMensajes(driver);

        // Ejercicio 8 - OK
//        abrirSitioWeb(driver, "https://demoqa.com/nestedframes");
//        leerTextosDeCajas(driver);

        // Ejercicio 9 - OK
        abrirSitioWeb(driver, "http://uitestingplayground.com/shadowdom");
        hacerClickEnBotonConEngranaje(driver);

        // Ejercicio 10 - OK
//        abrirSitioWeb(driver, "https://demoqa.com/automation-practice-form");
//        confirmarValoresYComprobarModal(driver);

        // Ejercicio 11 - OK
//      abrirSitioWeb(driver, "https://demoqa.com/automation-practice-form");
//      confirmarValoresYComprobarModal(driver);

        driver.quit();
    }

    private static void abrirSitioWeb(WebDriver driver, String url) {
        driver.get(url);
    }

    private static void aceptarCookies(WebDriver driver) {
        // Agregar código para aceptar las cookies si es necesario.
        // Por ejemplo:
         WebElement botonAceptarCookies = driver.findElement(By.id("acceptAllMain"));
         botonAceptarCookies.click();
    }

    private static void clickIconoEscudoBetis(WebDriver driver) {
        // Agregar código para hacer clic en el icono del escudo del Betis.
        // Por ejemplo:
         WebElement iconoEscudoBetis = driver.findElement(By.cssSelector("a[href='/futbol/betis/']"));
         iconoEscudoBetis.click();
    }

    private static void listarNoticiasPrincipales(WebDriver driver) {
        // Agregar código para listar las noticias principales por consola.
        // Por ejemplo:
         List<WebElement> noticias = driver.findElements(By.cssSelector("article.card.pila.foto-izquierda"));
         for (WebElement noticia : noticias) {
        	 System.out.println("***NOTICIA***");
             System.out.println(noticia.getText());
             System.out.println();
         }
    }

    private static void accederANoticia(WebElement element) {
    	element.click();
    }

    private static void listarPrimeraYUltimaNoticiaCarrusel(WebDriver driver) {
        // Agregar código para listar la primera y última noticia del carrusel.
        // Por ejemplo:
    	 List<WebElement> elementosNoticias = driver.findElements(By.xpath("//article[contains(@class, 'card') and contains(@class, 'pila') and contains(@class, 'foto-izquierda')]"));

    	 if (!elementosNoticias.isEmpty()) {
    		    WebElement primerElemento = elementosNoticias.get(0); // Obtener el primer elemento
    		    WebElement ultimoElemento = elementosNoticias.get(elementosNoticias.size() - 1); // Obtener el último elemento

    		    String tituloPrimerElemento = primerElemento.findElement(By.xpath(".//h2/a")).getText();
    		    String tituloUltimoElemento = ultimoElemento.findElement(By.xpath(".//h2/a")).getText();

    		    System.out.println("Título del primer elemento: " + tituloPrimerElemento);
    		    System.out.println("Título del último elemento: " + tituloUltimoElemento);
    		    accederANoticia(ultimoElemento);
    		} else {
    		    System.out.println("No se encontraron elementos.");
    		}
    }

    private static void rellenarFormularioPorId(WebDriver driver) {
        // Agregar código para rellenar el formulario por ID.
        // Por ejemplo:
        WebElement campoEmail = driver.findElement(By.id("userEmail"));
        campoEmail.sendKeys("miemail@example.com");

        WebElement campoPrimerNombre = driver.findElement(By.id("firstName"));
        campoPrimerNombre.sendKeys("Mi Nombre");

        WebElement campoSegundoNombre = driver.findElement(By.id("lastName"));
        campoSegundoNombre.sendKeys("Mi Apellido");

        // Seleccionar género (por ejemplo, Female)
        List<WebElement> generoRadioButtons = driver.findElements(By.id("gender-radio-"));
        for (WebElement radioButton : generoRadioButtons) {
            if ("Male".equals(radioButton.getAttribute("value"))) {
                // Ejecutar JavaScript para hacer clic en el radio button con value="Male"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", radioButton);
                break;
            }
        }

        WebElement campoMovil = driver.findElement(By.id("userNumber"));
        campoMovil.sendKeys("1234567890");

        WebElement campoFechaNac = driver.findElement(By.id("dateOfBirthInput"));
        campoFechaNac.sendKeys("01-01-1990");

        WebElement campoSubjects = driver.findElement(By.id("subjectsInput"));
        campoSubjects.sendKeys("Math");

        // Seleccionar pasatiempos (por ejemplo, Sports y Reading)
        List<WebElement> checkboxes = driver.findElements(By.id("hobbies-checkbox-'"));
        for (WebElement checkbox : checkboxes) {
            String labelValue = checkbox.getAttribute("value");
            if ("Sport".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Sport".
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
            if ("Music".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Music".
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
        }
    }


    private static void rellenarFormularioPorXpath(WebDriver driver) {
    	// Utiliza XPath con clases para localizar los elementos
        WebElement campoEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        WebElement campoPrimerNombre = driver.findElement(By.xpath("//input[@id='firstName']"));
        WebElement campoApellidos = driver.findElement(By.xpath("//input[@id='lastName']"));
        List<WebElement> generoOpciones = driver.findElements(By.xpath("//input[@name='gender']"));
        WebElement campoNumeroMovil = driver.findElement(By.xpath("//input[@id='userNumber']"));
        WebElement fechaNacimiento = driver.findElement(By.xpath("//input[@id='dateOfBirthInput']"));
        WebElement selectSubjects = driver.findElement(By.xpath("//div[@id='subjectsContainer']"));
        List<WebElement> hobbiesCheckboxes = driver.findElements(By.xpath("//input[contains(@id, 'hobbies-checkbox-')]"));

        campoEmail.sendKeys("miemail@example.com");
        campoPrimerNombre.sendKeys("Mi Nombre");
        campoApellidos.sendKeys("Mis Apellidos");

        for (WebElement radioButton : generoOpciones) {
            if ("Male".equals(radioButton.getAttribute("value"))) {
                // Ejecutar JavaScript para hacer clic en el radio button con value="Male"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", radioButton);
                break;
            }
        }

        campoNumeroMovil.sendKeys("1234567890");
        fechaNacimiento.sendKeys("29 Oct 2023");
        selectSubjects.sendKeys("Math");

        // Marcar checkbox de deportes (si su valor es "Sports")
        for (WebElement checkbox : hobbiesCheckboxes) {
            String labelValue = checkbox.getAttribute("value");
            if ("Sport".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Sport"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
            if ("Music".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Music"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
        }
    }
    

    private static void rellenarFormularioPorXpathLabel(WebDriver driver) {
	   // Utiliza solo el texto de las etiquetas <label> para localizar los elementos
    	WebElement campoEmail = driver.findElement(By.xpath("//label[contains(text(),'Email')]/following::input"));
    	WebElement campoPrimerNombre = driver.findElement(By.xpath("//label[contains(text(),'Name')]/following::input[@id='firstName']"));
    	WebElement campoApellidos = driver.findElement(By.xpath("//label[contains(text(),'Name')]/following::input[@id='lastName']"));
    	List<WebElement> generoOpciones = driver.findElements(By.xpath("//div[contains(text(),'Gender')]/following::div/input"));
    	WebElement campoNumeroMovil = driver.findElement(By.xpath("//label[contains(text(),'Mobile')]/following::input"));
    	WebElement fechaNacimiento = driver.findElement(By.xpath("//label[contains(text(),'Date of Birth')]/following::div/input"));
    	WebElement selectSubjects = driver.findElement(By.xpath("//label[contains(text(),'Subjects')]/following::div"));
    	List<WebElement> hobbiesCheckboxes = driver.findElements(By.xpath("//label[contains(text(),'Sports')]/following::input"));


        campoEmail.sendKeys("miemail@example.com");
        campoPrimerNombre.sendKeys("Mi Nombre");
        campoApellidos.sendKeys("Mis Apellidos");

        for (WebElement radioButton : generoOpciones) {
            if ("Male".equals(radioButton.getAttribute("value"))) {
                // Ejecutar JavaScript para hacer clic en el radio button con value="Male"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", radioButton);
                break;
            }
        }

        campoNumeroMovil.sendKeys("1234567890");
        fechaNacimiento.sendKeys("29 Oct 2023");

        // Marcar checkbox de deportes (si su valor es "Sports")
        for (WebElement checkbox : hobbiesCheckboxes) {
            String labelValue = checkbox.getAttribute("value");
            if ("Sport".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Sport"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
            if ("Music".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Music"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
        }
    }

    private static void hacerClickEnBotonesYCerrarMensajes(WebDriver driver) {
        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        System.out.println("Mensaje de alerta: " + alert.getText());
        alert.accept();
        
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();
        // Esperar 6 segundos para que aparezca el mensaje de temporizador
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Alert timerAlert = driver.switchTo().alert();
        System.out.println("Mensaje de temporizador: " + timerAlert.getText());
        timerAlert.accept();

        WebElement confirmButton = driver.findElement(By.id("confirmButton"));
        confirmButton.click();

        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Mensaje de confirmación: " + confirmAlert.getText());
        confirmAlert.dismiss();

        WebElement promptButton = driver.findElement(By.id("promtButton"));
        promptButton.click();

        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Mensaje de entrada: " + promptAlert.getText());
        promptAlert.sendKeys("Texto de prueba");
        promptAlert.accept();
    }

    private static void leerTextosDeCajas(WebDriver driver) {
        // Cambiar al iframe
        WebElement iframe = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iframe);
        String textoBuscado = "Parent frame";
        
        // Buscar el elemento que contiene el texto
        WebElement elementoConTexto = driver.findElement(By.xpath("//*[contains(text(), '" + textoBuscado + "')]"));

        // Imprimir el valor del elemento
        String valorElemento = elementoConTexto.getText();
        System.out.println("Texto encontrado " + valorElemento);

        
        textoBuscado = "Child Iframe";
        
        // Cambiar al iframe más interno
        iframe = driver.findElement(By.cssSelector("iframe[srcdoc*='Child Iframe']"));
        driver.switchTo().frame(iframe);
        
        // Buscar el elemento que contiene el texto
        elementoConTexto = driver.findElement(By.xpath("//*[contains(text(), '" + textoBuscado + "')]"));
        
        // Imprimir el valor del elemento
        valorElemento = elementoConTexto.getText();
        System.out.println("Texto encontrado " + valorElemento);
        
        // Volver al contexto principal
        driver.switchTo().defaultContent();
    }
    
    private static void hacerClickEnBotonConEngranaje(WebDriver driver) {
        WebElement shadowHost = driver.findElement(By.tagName("guid-generator"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Ejecutar JavaScript para interactuar con elementos en el Shadow DOM
        js.executeScript("arguments[0].shadowRoot.querySelector('#buttonGenerate').click()", shadowHost);
        js.executeScript("arguments[0].shadowRoot.querySelector('#buttonCopy').click()", shadowHost);
        
        // Esperar un momento para que se complete la acción
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Obtener el valor del campo de texto generado
        String valorGenerado = (String) js.executeScript("return arguments[0].shadowRoot.querySelector('#editField').value", shadowHost);

        // Imprimir el valor generado
        System.out.println("Valor del campo de texto generado: " + valorGenerado);
    }

    
//	TODO: ERROR: Intento de hacerlo sin Javascrip mediante la libreria selenium-shutterbug.
//    private static void hacerClickEnBotonConEngranaje(WebDriver driver) {
//        // Encuentra el elemento que contiene el Shadow DOM
//    	int a = 2;
//        // Obtener el shadowRoot.
//    	WebElement shadowRoot = (WebElement) getShadowRoot(driver, driver.findElement(By.tagName("guid-generator")));
//    	// Obtener el shadowElement.
//        WebElement shadowElement = getShadowElement(driver, shadowRoot, "guid-generator");
//        
//
//        // Utilizar JavaScript para obtener los elemento y cambiar el foco al Shadow DOM
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        
//        // Encuentra y hace clic en el botón 'buttonGenerate' dentro del Shadow DOM
//        WebElement buttonGenerate = shadowRoot.findElement(By.id("buttonGenerate"));
//        buttonGenerate.click();
//
//        // Espera un momento para que se complete la acción
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Encuentra y hace clic en el botón 'buttonCopy' dentro del Shadow DOM
//        WebElement buttonCopy = shadowRoot.findElement(By.id("buttonCopy"));
//        buttonCopy.click();
//
//        // Obtén el valor del campo de texto generado desde el Shadow DOM
//        String valorGenerado = (String) js.executeScript("return arguments[0].shadowRoot.querySelector('#editField').value", shadowElement);
//
//        // Imprime el valor generado
//        System.out.println("Valor del campo de texto generado: " + valorGenerado);
//    }
//    
//    
//    private static Object getShadowRoot(WebDriver driver,WebElement shadowHost) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        return js.executeScript("return arguments[0].shadowRoot", shadowHost);
//    }
//    
//    public static WebElement getShadowElement(WebDriver driver,WebElement shadowHost, String tagName) {
//        WebElement shardowRoot = (WebElement) getShadowRoot(driver, shadowHost);
//        return shardowRoot.findElement(By.tagName(tagName));
//    }

    
    private static void confirmarValoresYComprobarModal(WebDriver driver) {
        // Crear un HashMap para almacenar los datos del estudiante
        Map<String, String> studentData = new HashMap<>();

        // Agregar las claves y valores al HashMap
        studentData.put("Student Name", "Mi Nombre Mi Apellido");
        studentData.put("Student Email", "pruebas@yopmail.com");
        studentData.put("Gender", "Male");
        studentData.put("Mobile", "1234567890");
        studentData.put("Date of Birth", "01-01-1990");
        studentData.put("Subjects", "Maths");
        studentData.put("Hobbies", "Sport");
        studentData.put("Picture", "");
        studentData.put("Address", "Address example");
        studentData.put("State and City", "NCR Delhi");
    	
    	
        // Agregar código para rellenar el formulario por ID.
        // Por ejemplo:
        WebElement campoEmail = driver.findElement(By.id("userEmail"));
        campoEmail.sendKeys(studentData.get("Student Email"));

        WebElement campoPrimerNombre = driver.findElement(By.id("firstName"));
        campoPrimerNombre.sendKeys("Mi Nombre");

        WebElement campoSegundoNombre = driver.findElement(By.id("lastName"));
        campoSegundoNombre.sendKeys("Mi Apellido");

        // Seleccionar género (por ejemplo, Female)
        List<WebElement> generoRadioButtons = driver.findElements(By.xpath("//input[starts-with(@id, 'gender-radio-')]"));
        for (WebElement radioButton : generoRadioButtons) {
            if ("Male".equals(radioButton.getAttribute("value"))) {
                // Ejecutar JavaScript para hacer clic en el radio button con value="Male"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", radioButton);
                break;
            }
        }

        WebElement campoMovil = driver.findElement(By.id("userNumber"));
        campoMovil.sendKeys("1234567890");

        WebElement campoFechaNac = driver.findElement(By.id("dateOfBirthInput"));
        campoFechaNac.sendKeys("01-01-1990");

        WebElement campoSubjects = driver.findElement(By.id("subjectsInput"));
        campoSubjects.sendKeys("Math");
        campoSubjects.sendKeys(Keys.ENTER);

     // Obtener todos los elementos <input> con name="gender"
    	List<WebElement> hobbiesCheckboxes = driver.findElements(By.xpath("//input[starts-with(@id, 'hobbies-checkbox-')]"));

        // Marcar checkbox de deportes (si su valor es "Sports")
        for (WebElement checkbox : hobbiesCheckboxes) {
            String labelValue = checkbox.getAttribute("value");
            if ("Sport".equals(labelValue)) {
                // Ejecutar JavaScript para hacer clic en el checkbox "Sport"
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", checkbox);
            }
        }
        
     // Crear un mapa para relacionar los labels con los inputs
        Map<WebElement, WebElement> labelInputMap = new HashMap<>();

        // Iterar a través de los inputs para mapearlos con los labels
        for (WebElement input : hobbiesCheckboxes) {
            WebElement label = input.findElement(By.xpath("following-sibling::label[1]"));
            labelInputMap.put(label, input);
        }

        // Buscar el label con el texto "Male"
        for (Map.Entry<WebElement, WebElement> entry : labelInputMap.entrySet()) {
            WebElement label = entry.getKey();
            String labelText = label.getText();
            if ("Sports".equals(labelText)) {
              // Ejecutar JavaScript para hacer clic en el checkbox "Sport"
              JavascriptExecutor executor = (JavascriptExecutor) driver;
              executor.executeScript("arguments[0].click();", entry.getKey());
              break;
            }
        }

        WebElement campoCurrentAddress = driver.findElement(By.id("currentAddress"));
        campoCurrentAddress.sendKeys("Address example");
        
        // Localiza el campo de búsqueda (input) dentro del "dropdown".
        WebElement stateDropdown = driver.findElement(By.id("react-select-3-input"));
        stateDropdown.sendKeys("NCR");
        stateDropdown.sendKeys(Keys.ENTER);
        
        
     // Localiza el campo de búsqueda (input) dentro del "dropdown" de ciudad.
        WebElement cityDropdown = driver.findElement(By.id("react-select-4-input"));
        cityDropdown.sendKeys("Delhi");
        cityDropdown.sendKeys(Keys.ENTER);
        
        
    	campoEmail.sendKeys(Keys.ENTER);

        // Espera a que aparezca la modal.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        By modalTitle = By.id("example-modal-sizes-title-lg");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));

        // Obtén los valores de la tabla modal.
        WebElement modal = driver.findElement(By.className("modal-content"));
        WebElement modalTable = modal.findElement(By.tagName("table"));
        List<WebElement> rows = modalTable.findElements(By.tagName("tr"));

        // Crear un HashMap para almacenar los valores de la tabla modal
        Map<String, String> tableData = new HashMap<>();
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() == 2) {
                String label = columns.get(0).getText();
                String value = columns.get(1).getText();
                tableData.put(label, value);
            }
        }

        // Comparar los valores de la tabla modal con los datos del estudiante.
        for (Map.Entry<String, String> entry : studentData.entrySet()) {
            String key = entry.getKey();
            String expectedValue = entry.getValue();
            if (tableData.containsKey(key)) {
                String actualValue = tableData.get(key);
                if (actualValue.equals(expectedValue)) {
                    System.out.println("Valor verificado: " + key + " - " + actualValue);
                } else {
                    System.out.println("Valor no coincide: " + key + " - " + actualValue);
                }
            }
        }

        // Cierra la modal.
        WebElement closeButton = modal.findElement(By.id("closeLargeModal"));
        closeButton.click();
    }

}
