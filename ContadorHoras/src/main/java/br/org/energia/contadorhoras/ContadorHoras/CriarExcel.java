package br.org.energia.contadorhoras.ContadorHoras;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class CriarExcel {

	public void criarArqExcel(List<Atividade> lista) {
		try {
			String filename = "C:/Temp/Contador_de_Horas.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("FirstSheet");
			
			CellStyle cs = workbook.createCellStyle();
			
			Font f = workbook.createFont();
			
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);
			f.setFontHeightInPoints((short) 14);
			
			cs.setFont(f);
			cs.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
			cs.setFillPattern(CellStyle.SOLID_FOREGROUND);

			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("Atividade");
			rowhead.getCell(0).setCellStyle(cs);
			rowhead.createCell(1).setCellValue("Horas");
			rowhead.getCell(1).setCellStyle(cs);

			for (int i = 0; i < lista.size(); i++) {
				HSSFRow row = sheet.createRow((short) i + 1);
				row.createCell(0).setCellValue(lista.get(i).getDescricao());
				Long horaInt = lista.get(i).getHora();
				double horas = (double)horaInt/60;
				long hora = horaInt/60; 
				long minutos = (int) (horaInt % 60);
				String horaFormatada = null;
				if(hora < 10 && minutos < 10){
					horaFormatada = ("0"+hora+":0"+minutos);
					row.createCell(1).setCellValue(horaFormatada);
				}else if(hora < 10 && minutos > 10){
					horaFormatada = ("0"+hora+":"+minutos);
					row.createCell(1).setCellValue(horaFormatada);
				}else if(hora > 10 && minutos < 10){
					horaFormatada = (hora+":0"+minutos);
					row.createCell(1).setCellValue(horaFormatada);
				}else if(hora > 10 && minutos > 10){
					horaFormatada = (hora+":"+minutos);
					row.createCell(1).setCellValue(horaFormatada);
				}
				
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
//	public void criarExcelDetalhes(List<Atividade> lista) {
//		try {
//			String filename = "C:/Temp/Detalhes_de_Horas.xls";
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			HSSFSheet sheet = workbook.createSheet("FirstSheet");
//			HSSFRow rowhead = sheet.createRow((short) 0);
//			rowhead.createCell(0).setCellValue("Atividade");
//			rowhead.createCell(1).setCellValue("Horas");
//			rowhead.createCell(2).setCellValue("Data");
//
//			for (int i = 0; i < lista.size(); i++) {
//				HSSFRow row = sheet.createRow((short) i + 1);
//				row.createCell(0).setCellValue(lista.get(i).getDescricao());
//				row.createCell(1).setCellValue(lista.get(i).getHora());
//				row.createCell(2).setCellValue(lista.get(i).getData());
//			}
//
//			FileOutputStream fileOut = new FileOutputStream(filename);
//			workbook.write(fileOut);
//			fileOut.close();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
}
