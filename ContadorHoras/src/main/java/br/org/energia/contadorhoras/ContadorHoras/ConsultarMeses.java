package br.org.energia.contadorhoras.ContadorHoras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConsultarMeses {

	@SuppressWarnings("unused")
	private WebDriver driver;

	List<Atividade> listaResultado = new ArrayList<Atividade>();
	List<Atividade> listaDetalhe = new ArrayList<Atividade>();
	public void listarMesAnterior(WebDriver driver) throws InterruptedException {
		WebElement btnListarPorMes = driver.findElement(
				By.xpath("//html/body/table[1]/tbody/tr/td[2]/table/tbody/tr/td/table[1]/tbody/tr/td[4]/a[5]/img"));
		btnListarPorMes.click();

		Thread.sleep(3000);

		WebElement btnVoltaUmMes = driver
				.findElement(By.xpath("//*[@id='calendarCalendario']/table/thead/tr[2]/td[2]/div"));
		btnVoltaUmMes.click();

		Thread.sleep(3000);

		selecionaPrimeiroDiaUtil(driver);

	}

	public void selecionaPrimeiroDiaUtil(WebDriver driver) {
		int count = 8;
		String diaUm = "1";
		String diaDois = "2";
		List<WebElement> primeirosDiasMes = new ArrayList<WebElement>();

		for (int i = 3; i < count; i++) {
			primeirosDiasMes.addAll(
					driver.findElements(By.xpath("//*[@id='calendarCalendario']/table/tbody/tr[1]/td[" + i + "]")));
			String dia = primeirosDiasMes.get(i - 3).getText();
			if (diaUm.equals(dia.trim())) {
				primeirosDiasMes.get(i - 3).click();
			} else if (diaDois.equals(dia.trim())) {
				primeirosDiasMes.get(i - 3).click();
			}
		}
	}

	public void listarMesAtual(WebDriver driver) throws InterruptedException {
		WebElement btnVoltaUmMes = driver
				.findElement(By.xpath("//*[@id='calendarCalendario']/table/thead/tr[2]/td[4]/div"));
		btnVoltaUmMes.click();

		Thread.sleep(3000);

		selecionaPrimeiroDiaUtil(driver);
	}

	@SuppressWarnings("unused")
	public List<Atividade> obterAtividadeEHoras(WebDriver driver) throws ParseException, InterruptedException {
		int semana = 10, dia = 5, atividade = 20;

		List<WebElement> listaSemanas = new ArrayList<WebElement>();
		List<WebElement> listaDias = new ArrayList<WebElement>();
		List<WebElement> listaAtividade = new ArrayList<WebElement>();

		String caminhoSemana;
		String caminhoDia;
		String caminhoAtividade;

		int contadorLista = 0;

		for (int contadorSemana = 2; contadorSemana <= semana; contadorSemana = contadorSemana + 2) {
			try {

				caminhoSemana = "//html/body/table/tbody/tr/td[2]/table/tbody/tr/td/table[2]/tbody/tr[" + contadorSemana
						+ "]";
				listaSemanas.addAll(driver
						.findElements(By.xpath("//html/body/table/tbody/tr/td[2]/table/tbody/tr/td/table[2]/tbody/tr["
								+ contadorSemana + "]")));

				if (listaSemanas.get(contadorLista) != null) {

					for (int contadorDia = 1; contadorDia <= dia; contadorDia++) {
						try {

							caminhoDia = caminhoSemana + "/td[" + contadorDia + "]";
							listaDias.addAll(driver.findElements(By.xpath(caminhoSemana + "/td[" + contadorDia + "]")));

							if (listaDias.get(contadorDia - 1) != null) {

								listaAtividade.clear();

								for (int contadorAtividade = 1; contadorAtividade <= atividade; contadorAtividade++) {
									Atividade ativ = new Atividade();
									try {

										caminhoAtividade = caminhoDia + "/table/tbody/tr[" + contadorAtividade
												+ "]/td[2]/a[2]";
										listaAtividade.addAll(driver.findElements(By.xpath(
												caminhoDia + "/table/tbody/tr[" + contadorAtividade + "]/td[2]/a[2]")));

										if (listaAtividade.get(contadorAtividade - 1) != null) {
											WebElement aux = driver.findElement(By.xpath(caminhoDia + "/table/tbody/tr["
													+ contadorAtividade + "]/td[2]/a[2]"));
											((JavascriptExecutor) driver).executeScript("arguments[0].click();", aux);

											Thread.sleep(2000);

											WebElement horas = driver.findElement(By.id("tempo_digitado"));
											WebElement descricao = driver.findElement(By.id("narrativa_principal"));
											WebElement data = driver.findElement(By.id("f_data_b"));

											SimpleDateFormat format = new SimpleDateFormat("HH:mm");
											java.util.Date date = format.parse(horas.getAttribute("value").toString());
											Calendar horaCorreta = Calendar.getInstance();

											horaCorreta.setTime(date);

											Long hora = (long) horaCorreta.get(Calendar.HOUR_OF_DAY);
											Long minuto = (long) horaCorreta.get(Calendar.MINUTE);

											Long totalEmMinutos = hora * 60 + minuto;

											ativ.setDescricao(descricao.getText());
											ativ.setHora(totalEmMinutos);
											ativ.setData(data.getAttribute("value").toString());

											if (!validaData(ativ)) {
												
												listaDetalhe.add(ativ);

												adicionarAtividades(ativ);
//												System.out.println("Atividade: " + ativ.getDescricao() + " - Horas: "
//														+ ativ.getHora() + " - Data: "
//														+ data.getAttribute("value").toString());
											}

											WebElement fecharPopUp = driver
													.findElement(By.xpath("html/body/div[9]/div[1]/a"));
											((JavascriptExecutor) driver).executeScript("arguments[0].click();",
													fecharPopUp);
										}

									} catch (IndexOutOfBoundsException e) {
										break;
									}
								}
							}
						} catch (IndexOutOfBoundsException e) {
							break;
						}
					}
				}
				contadorLista++;
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		return listaResultado;
	}

	public void adicionarAtividades(Atividade atividade) {

		boolean check = false;

		if (listaResultado.size() != 0) {

			for (int count = 0; count < listaResultado.size(); count++) {

				check = false;

				if (listaResultado.get(count).getDescricao().equals(atividade.getDescricao())) {

					listaResultado.get(count).setHora(listaResultado.get(count).getHora() + atividade.getHora());
					check = true;
					break;
				}
			}
			if (check == false) {
				listaResultado.add(atividade);
			}
		} else {
			listaResultado.add(atividade);
		}
	}

	public boolean validaData(Atividade atividade) {
		int validador = 0;
		if (listaDetalhe.size() != 0) {

			for (int count = 0; count < listaDetalhe.size(); count++) {

				if (listaDetalhe.get(count).getData().equals(atividade.getData())
						&& listaDetalhe.get(count).getDescricao().equals(atividade.getDescricao())) {
					validador++;
				}
			}
			if (validador > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
