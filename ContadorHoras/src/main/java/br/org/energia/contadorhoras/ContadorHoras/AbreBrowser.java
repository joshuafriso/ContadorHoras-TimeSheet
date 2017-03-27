package br.org.energia.contadorhoras.ContadorHoras;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AbreBrowser {
	WebDriver driver = new PhantomJSDriver();
	
	public Capabilities capabilities() {
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		return caps;
	}

	public void abrirBrowser(WebDriver driver) {

		driver.get("http://app1.multidadosti.com.br/ccee/login.php");

		driver.manage().window().maximize();

	}

}
