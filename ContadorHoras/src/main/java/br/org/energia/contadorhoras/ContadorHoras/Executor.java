package br.org.energia.contadorhoras.ContadorHoras;

import java.io.IOException;
import java.text.ParseException;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class Executor {

//	@SuppressWarnings("unused")
//	private final static String pathIEServer = "D:\\_developer\\Driver\\IEDriverServer.exe";
//	@SuppressWarnings("unused")
//	private final static String pathChromeServer = "D:\\_developer\\Driver\\chromedriver.exe";
	private final static String pathPhantomServer =  System.getProperty("user.dir")+"\\Drivers\\phantomjs.exe";

	public static void main(String[] args) throws InterruptedException, ParseException, IOException {

		/*
		 * EtapasTeste firefox = new EtapasTeste(); firefox.teste(new
		 * FirefoxDriver());
		 */

		/*
		 * EtapasTeste chrome = new EtapasTeste();
		 * System.setProperty("webdriver.chrome.driver", pathChromeServer);
		 * chrome.teste(new ChromeDriver());
		 */

		/*
		 * EtapasTeste ie = new EtapasTeste();
		 * System.setProperty("webdriver.ie.driver", pathIEServer); ie.teste(new
		 * InternetExplorerDriver());
		 */

		EtapasTeste phantom = new EtapasTeste();
		System.setProperty("phantomjs.binary.path", pathPhantomServer);
		phantom.teste(new PhantomJSDriver());

	}

}
