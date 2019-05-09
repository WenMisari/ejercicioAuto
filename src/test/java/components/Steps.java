package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Steps {
	
	public WebDriver driver; 
	String URL = "https://www.ebay.com/itm/273600137863?ul_noapp=true";

	@BeforeClass
	public void testSetUp() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void verifyEbayPageTittle() throws Throwable {
		
		Reporter.log("Se ingresa a la web de ebay");
		driver.navigate().to(URL);
		
		Reporter.log("Se valida que sea la pagina correcta");
		String getTitle = driver.getTitle();
		Assert.assertEquals(getTitle, "Laser Freckle Dark Spot Tattoo Removal Skin Mole Wart Tag Removal Pen Machine US 654936789425 | eBay");
		
		Reporter.log("Se realiza la busqueda");
		
		WebElement filtroInicial = driver.findElement(By.id("gh-shop-a"));
		filtroInicial.click();
		
		WebElement opcCalzado = driver.findElement(By.xpath("//*[@id=\"gh-sbc\"]/tbody/tr/td[1]/ul[3]/li[4]/a"));
		opcCalzado.click();
		
		try {
			WebElement opcDeportivo = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[2]/div[2]/a[6]/div[2]"));
			opcDeportivo.click();
		
			WebElement filtroUno = driver.findElement(By.xpath("//*[@id='w1-w1-w0-w0-multiselect[14]']/a/span[1]"));
			filtroUno.click();
			Thread.sleep(1000);
		
			WebElement filtroDos = driver.findElement(By.xpath("//*[@id=\"w1-w1-w0-multiselect[0]\"]/a/span[1]"));
			filtroDos.click();
		}
		
		catch(Exception e) {
			System.out.println ("El error es: " + e.getMessage());
		    e.printStackTrace();			
		}
				
		String number = driver.findElement(By.xpath("//*[@id=\"w7-w0\"]/div[2]/div/div/h2")).getText();
		Reporter.log("Se muestra el resultado de la busqueda");
		System.out.println("Number of results: " + number);
		System.out.println();
	
		//Scroll
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)"); 
		
		Thread.sleep(1000);
		Reporter.log("Se filtra la busqueda");
		Actions action = new Actions(driver);
		
		WebElement filtro = driver.findElement(By.xpath("//*[@id=\"w7-w0-w1\"]/button/div/div"));
		action.moveToElement(filtro).build().perform();
		driver.findElement(By.xpath("//*[@id=\"w7-w0-w1\"]/div/div/ul/li[4]/a")).click();
		
		js.executeScript("window.scrollBy(0,200)");
		
		Reporter.log("Se muestran los 5 primeros resultados");
		
		for(int i=0;i<5;i++) {
			int valor=i;
			WebElement zapatillas = driver.findElement(By.xpath("//*[@id='w5-items[" + valor + "]']/div/div[2]/a/h3"));
			String valorZap = zapatillas.getText();
			WebElement precio = driver.findElement(By.xpath("//*[@id='w5-items["+ valor + "]']/div/div[2]/div[2]/div[1]/span"));
			String valorPrecio = precio.getText();
			WebElement envio = driver.findElement(By.xpath("//*[@id='w5-items["+ valor + "]']/div/div[2]/div[2]/div[2]/span"));
			String valorOtro = envio.getText();
			System.out.println("Nombre producto: " + valorZap);
			System.out.println("Precio producto: " + valorPrecio);
			System.out.println("Envio u otros: " + valorOtro);
		}
		
		System.out.println();
		Thread.sleep(2000);
		
		//Productos precio descendiente
		WebElement precioDesc = driver.findElement(By.xpath("//*[@id='w5-w0-w1']/button/div"));
		action.moveToElement(precioDesc).build().perform();
		driver.findElement(By.xpath("//*[@id='w5-w0-w1']/div/div/ul/li[5]/a/span")).click();
		
		for(int i=0;i<48;i++) {
			int valor=i;
			int contador=i+1;
			WebElement zapatillas2 = driver.findElement(By.xpath("//*[@id='w5-items[" + valor + "]']/div/div[2]/a/h3"));
			String valorZap2 = zapatillas2.getText();
			WebElement precio2 = driver.findElement(By.xpath("//*[@id='w5-items["+ valor + "]']/div/div[2]/div[2]/div[1]/span"));
			String valorPrecio2 = precio2.getText();
			WebElement envio2 = driver.findElement(By.xpath("//*[@id='w5-items["+ valor + "]']/div/div[2]/div[2]/div[2]/span"));
			String valorEnvio2 = envio2.getText();
			System.out.println("Nombre producto "+contador+": " + valorZap2);
			System.out.println("Precio producto "+contador+": " + valorPrecio2);
			System.out.println("Envio u otros "+contador+": " + valorEnvio2);
		}
		
		//Wait
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
	
}
