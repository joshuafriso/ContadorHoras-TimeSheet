package br.org.energia.contadorhoras.ContadorHoras;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

	@SuppressWarnings("unused")
	private WebDriver driver;

	public void logar(String usr, String pwd, WebDriver driver) throws InterruptedException {
		WebElement element = driver.findElement(By.id("login"));
		element.sendKeys(usr);

		element = driver.findElement(By.id("password_sem_md5"));
		element.sendKeys(pwd);

		element.submit();

		Thread.sleep(2000);
		try {
			element = driver.findElement(By.id("td_error"));
			element.getText();
			JOptionPane.showMessageDialog(null, element.getText());
			JOptionPane.showMessageDialog(null, "Execute a aplicação novamente por favor!!!");
			System.exit(0);
		} catch (NoSuchElementException e) {
			JOptionPane.showMessageDialog(null, "Login realizado com sucesso!!!");
		}
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
