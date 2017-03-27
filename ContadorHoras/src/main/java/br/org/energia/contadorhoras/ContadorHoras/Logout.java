package br.org.energia.contadorhoras.ContadorHoras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Logout {

	public void exit(WebDriver driver) throws InterruptedException {
		WebElement logout = driver.findElement(By.xpath("//html/body/div[1]/div[1]/ul/li[2]/a"));
		logout.click();

		Thread.sleep(2000);
		
		driver.close();
		driver.quit();

	}

}
