package br.org.ccee.testeTela;

import java.io.IOException;

import javax.swing.JProgressBar;

public class TesteProgressBar {
	
	private final static String pathPhantomKill =  System.getProperty("user.dir")+"\\finalizaProcesso.bat";

	public static void main(String[] args) {
//		 // Create a horizontal progress bar
//	    int min = 0;
//	    int max = 100;
//	    JProgressBar progress = new JProgressBar(min, max);
//
//	    // Play animation
//	    progress.setIndeterminate(true);
		
		try {
			Runtime.getRuntime().exec(pathPhantomKill);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}