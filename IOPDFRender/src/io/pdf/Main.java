package io.pdf;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import io.pdf.IOTable.Alignment;
import io.pdf.IOTable.IOTableCell;
import io.pdf.IOTable.IOTableRow;

public class Main {
	
	public static void generatePDF() throws Exception{
		
		PDFRenderer renderer = new PDFRenderer();
		PDFPage page1 = renderer.createPage();
		
		PDFImage pdfImage = new PDFImage("C:\\Users\\venkatesh_vasam\\Downloads\\image020.png");
		pdfImage.setWidth(114.3f);
		pdfImage.setHeight(18f);
		pdfImage.setMarginLeft(38.1f);
		
		page1.addWidget(pdfImage);
		
		PDFImage pdfImage1 = new PDFImage("C:\\Users\\venkatesh_vasam\\Downloads\\back_1.png");
		pdfImage1.setWidth(50.8f);
		pdfImage1.setHeight(17f);
		pdfImage1.setMarginLeft(155f);
		page1.addWidget(pdfImage1);
		
		PDFText text1 = new PDFText("PRICE PAGES");
		text1.setMarginLeft(170f);
		text1.setFontColor(Color.white);
		text1.setMarginTop(5f);
		text1.setFontSize(12f);
		text1.setBold(true);
		page1.addWidget(text1);
		
		PDFDiv deliveryDiv = getDeliveryDiv(page1);
		PDFDiv enforceDiv = getEnforceSteelDiv(page1, deliveryDiv);
		
		PDFDiv divTop = new PDFDiv();
		divTop.setWidthPercentage(100, page1);
		divTop.setHeight(30f);
		divTop.setMarginTop(25.4f);
		divTop.setBottomOf(pdfImage1);
		divTop.appendChild(deliveryDiv);
		divTop.appendChild(enforceDiv);
		
		page1.addWidget(divTop);
		
		PDFDiv divTable1 = new PDFDiv();
		divTable1.setWidthPercentage(100, page1);
		divTable1.setHeight(30f);
		divTable1.setBottomOf(divTop);
		divTable1.setMarginTop(15f);
		IOTable ioTable1 = getIOTable(page1);
		divTable1.appendChild(ioTable1);
		
		page1.addWidget(divTable1);
		
		PDFText header1 = new PDFText("COMMERCIAL/INDUSTRIAL PANELS");
		header1.setBottomOf(divTable1);
		header1.setMarginTop(10f);
		header1.setFontSize(10);
		header1.setBold(true);
		page1.addWidget(header1);
		
		PDFLine line1 = new PDFLine();
		line1.setWidthPercentage(100, page1);
		line1.setBottomOf(header1);
		page1.addWidget(line1);
		
		PDFDiv divTable2 = new PDFDiv();
		divTable2.setWidthPercentage(100, page1);
		divTable2.setHeight(30f);
		divTable2.setBottomOf(divTop);
		divTable2.setMarginTop(5f);
		divTable2.setBottomOf(line1);
		IOTable ioTable2 = getIOTable2(page1);
		divTable2.appendChild(ioTable2);
		
		page1.addWidget(divTable2);
		
		PDFDiv divTable3 = new PDFDiv();
		divTable3.setWidthPercentage(100, page1);
		divTable3.setHeight(15f);
		divTable3.setBottomOf(divTop);
		divTable3.setMarginTop(5f);
		divTable3.setBottomOf(divTable2);
		//divTable3.borders(true);
		
		PDFText header2 = new PDFText("SUPER SPAN X / LOW RIB X / MONARCH");
		header2.setBottomOf(divTable2);
		header2.setMarginTop(10f);
		header2.setBold(true);
		header2.setFontSize(10);
		
		divTable3.appendChild(header2);
		
		PDFLine line2 = new PDFLine();
		line2.setWidthPercentage(100, page1);
		line2.setBottomOf(header2);
		divTable3.appendChild(line2);
		
		page1.addWidget(divTable3);
		
		PDFDiv divTable4 = new PDFDiv();
		divTable4.setWidthPercentage(100, page1);
		divTable4.setHeight(30f);
		divTable4.setBottomOf(divTable3);
		IOTable ioTable3 = getIOTable3(page1);
		divTable4.appendChild(ioTable3);
		page1.addWidget(divTable4);
		
		PDFDiv divTable5 = new PDFDiv();
		divTable5.setWidthPercentage(100, page1);
		divTable5.setHeight(17f);
		divTable5.setAlignParentBottom(true);
		
		PDFImage pdfImage2 = new PDFImage("C:\\Users\\venkatesh_vasam\\Downloads\\back_2.png");
		pdfImage2.setWidth(50.8f);
		pdfImage2.setHeight(17f);
		pdfImage2.setAlignParentBottom(true);
		pdfImage2.setX(0);
		divTable5.appendChild(pdfImage2);
		
		PDFText text2 = new PDFText("www.cloudio.io");
		text2.setMarginLeft(10f);
		text2.setFontColor(Color.white);
		text2.setMarginTop(5f);
		text2.setFontSize(7f);
		divTable5.appendChild(text2);
		page1.addWidget(text2);
		
		page1.addWidget(divTable5);
		
		renderer.render();
		renderer.done();
		ByteArrayOutputStream stream = renderer.getContent();
		try(OutputStream outputStream = new FileOutputStream("C:\\Users\\venkatesh_vasam\\Downloads\\test2.pdf")) {
			stream.writeTo(outputStream);
		}
	}
	
	public static PDFDiv getDeliveryDiv(PDFPage page){
		
		PDFDiv child1 = new PDFDiv();//In mm
		child1.setWidthPercentage(50, page);
		child1.setHeight(30f);
		//child1.setMarginTop(25.4f);
		
		PDFText text2 = new PDFText("DELIVERY");
		text2.setBold(true);
		child1.appendChild(text2);
		
		PDFDiv hrshort = new PDFDiv();//In mm
		hrshort.setWidth(20f);
		hrshort.setHeight(0.8f);
		hrshort.setBackground(Color.red);
		hrshort.setMarginLeft(10f);
		hrshort.setBottomOf(text2);
		
		PDFDiv hrLong = new PDFDiv();//In mm
		hrLong.setWidth(35f);
		hrLong.setHeight(0.5f);
		hrLong.setBackground(Color.red);
		hrLong.setMarginLeft(10f);
		hrLong.setMarginTop(0.5f);
		hrLong.setBottomOf(text2);
		
		child1.appendChild(hrshort);
		child1.appendChild(hrLong);
		
		
		IOTable ioTable = new IOTable();
		ioTable.setFontSize(7.5f);
		ioTable.setFontType(PDType1Font.HELVETICA);
		ioTable.setMarginTop(15f);
		ioTable.setMarginLeft(10f);
		ioTable.setWidth(60);
		ioTable.setDrawLines(false);
		ioTable.setCellPadding(0);
		
		IOTableRow row1 = ioTable.createRow();
		
		IOTableCell row1Cell1 = row1.createCell();
		row1Cell1.setValue("Cutoff");
		row1Cell1.setWidthPerc(50);
		
		IOTableCell row1Cell2 = row1.createCell();
		row1Cell2.setValue("Monday");
		row1Cell2.setWidthPerc(40);
		
		IOTableRow row2 = ioTable.createRow();
		
		IOTableCell row2Cell1 = row2.createCell();
		row2Cell1.setValue("Delivery");
		row2Cell1.setWidthPerc(50);
		
		IOTableCell row2Cell2 = row2.createCell();
		row2Cell2.setValue("By Thursday");
		row2Cell2.setWidthPerc(40);
		
		IOTableRow row3 = ioTable.createRow();
		
		IOTableCell row3Cell1 = row3.createCell();
		row3Cell1.setValue("Weight min.");
		row3Cell1.setWidthPerc(50);
		
		IOTableCell row3Cell2 = row3.createCell();
		row3Cell2.setValue("500 lbs. or $50.00");
		row3Cell2.setWidthPerc(40);
		
		child1.appendChild(ioTable);
		
		return child1;
	}

	public static PDFDiv getEnforceSteelDiv(PDFPage page, PDFWidget parent){
		
		PDFDiv child1 = new PDFDiv();//In mm
		child1.setWidthPercentage(50, page);
		child1.setRightOf(child1);
		child1.setHeight(30f);
		//child1.setMarginTop(25.4f);
		
		PDFText text2 = new PDFText("ENCORE STEEL BUILDINGS LLC");
		text2.setFontColor(Color.red);
		text2.setBold(true);
		child1.appendChild(text2);
		
		PDFDiv hrshort = new PDFDiv();//In mm
		hrshort.setWidth(20f);
		hrshort.setHeight(0.8f);
		hrshort.setBackground(Color.red);
		hrshort.setMarginLeft(10f);
		hrshort.setBottomOf(text2);
		
		PDFDiv hrLong = new PDFDiv();//In mm
		hrLong.setWidth(35f);
		hrLong.setHeight(0.5f);
		hrLong.setBackground(Color.red);
		hrLong.setMarginLeft(10.1f);
		hrLong.setMarginTop(0.5f);
		hrLong.setBottomOf(text2);
		
		child1.appendChild(hrshort);
		child1.appendChild(hrLong);
		
		IOTable ioTable = new IOTable();
		ioTable.setFontSize(7.5f);
		ioTable.setFontType(PDType1Font.HELVETICA);
		ioTable.setMarginTop(15f);
		ioTable.setMarginLeft(10f);
		ioTable.setWidth(child1.getWidth());
		ioTable.setDrawLines(false);
		ioTable.setCellPadding(0);
		
		IOTableRow row1 = ioTable.createRow();
		
		IOTableCell row1Cell1 = row1.createCell();
		row1Cell1.setValue("Little Rock, AR 72204");
		row1Cell1.setWidthPerc(50);
		
		IOTableCell row1Cell2 = row1.createCell();
		row1Cell2.setValue("");
		row1Cell2.setWidthPerc(40);
		
		IOTableRow row2 = ioTable.createRow();
		
		IOTableCell row2Cell1 = row2.createCell();
		row2Cell1.setValue("Customer Number");
		row2Cell1.setWidthPerc(50);
		
		IOTableCell row2Cell2 = row2.createCell();
		row2Cell2.setValue("50950");
		row2Cell2.setWidthPerc(40);
		
		IOTableRow row3 = ioTable.createRow();
		
		IOTableCell row3Cell1 = row3.createCell();
		row3Cell1.setValue("Customer Book");
		row3Cell1.setWidthPerc(50);
		
		IOTableCell row3Cell2 = row3.createCell();
		row3Cell2.setValue("ENCO006342");
		row3Cell2.setWidthPerc(40);
		
		child1.appendChild(ioTable);
		
		return child1;
	}

	public static IOTable getIOTable(PDFPage page){
		
		IOTable ioTable = new IOTable();
		//ioTable.setFontType(PDType1Font.HELVETICA);
		ioTable.setWidthPercentage(100, page);
		//ioTable.setDrawLines(false);
		ioTable.setBorderColor(Color.red);
		
		IOTableRow header = ioTable.createRow();
		header.setHeaderRow(true);
		
		IOTableCell headerCell1 = header.createCell();
		headerCell1.setValue("SUPER SPAN / LOW RIB");
		headerCell1.setBackgroundColor(Color.red);
		headerCell1.setFontColor(Color.white);
		headerCell1.setAlignment(Alignment.CENTER);
		
		IOTableCell headerCell2 = header.createCell();
		headerCell2.setValue("Grade");
		headerCell2.setBackgroundColor(Color.red);
		headerCell2.setFontColor(Color.white);
		headerCell2.setAlignment(Alignment.CENTER);
		
		IOTableCell headerCell3 = header.createCell();
		headerCell3.setValue("PRICE/SQR");
		headerCell3.setBackgroundColor(Color.red);
		headerCell3.setFontColor(Color.white);
		headerCell3.setAlignment(Alignment.CENTER);
		
		IOTableCell headerCell4 = header.createCell();
		headerCell4.setValue("PRICE/LFT");
		headerCell4.setBackgroundColor(Color.red);
		headerCell4.setFontColor(Color.white);
		headerCell4.setAlignment(Alignment.CENTER);
		
		
		
		IOTableRow row1 = ioTable.createRow();
		
		IOTableCell row1Cell1 = row1.createCell();
		row1Cell1.setValue("29 GA. GALVALUME® Plus");
		row1Cell1.setAlignment(Alignment.CENTER);
		row1Cell1.setFontSize(9);
		
		IOTableCell row1Cell2 = row1.createCell();
		row1Cell2.setValue("(AZ55 Grade 80)");
		row1Cell2.setAlignment(Alignment.CENTER);
		row1Cell2.setFontSize(9);
		
		IOTableCell row1Cell3 = row1.createCell();
		row1Cell3.setValue("$95.25");
		row1Cell3.setAlignment(Alignment.CENTER);
		row1Cell3.setFontSize(9);
		
		IOTableCell row1Cell4 = row1.createCell();
		row1Cell4.setValue("$3.04");
		row1Cell4.setAlignment(Alignment.CENTER);
		row1Cell4.setFontSize(9);
		
		IOTableRow row2 = ioTable.createRow();
		
		IOTableCell row2Cell1 = row2.createCell();
		row2Cell1.setValue("29 GA. GALVALUME® Plus");
		row2Cell1.setAlignment(Alignment.CENTER);
		row2Cell1.setFontSize(9);
		
		IOTableCell row2Cell2 = row2.createCell();
		row2Cell2.setValue("(AZ50 Grade 80)");
		row2Cell2.setAlignment(Alignment.CENTER);
		row2Cell2.setFontSize(9);
		
		IOTableCell row2Cell3 = row2.createCell();
		row2Cell3.setValue("$125.50");
		row2Cell3.setAlignment(Alignment.CENTER);
		row2Cell3.setFontSize(9);
		
		IOTableCell row2Cell4 = row2.createCell();
		row2Cell4.setValue("$4.00");
		row2Cell4.setAlignment(Alignment.CENTER);
		row2Cell4.setFontSize(9);
		
		IOTableRow row3 = ioTable.createRow();
		
		IOTableCell row3Cell1 = row3.createCell();
		row3Cell1.setValue("26 GA. Commodity Colors");
		row3Cell1.setAlignment(Alignment.CENTER);
		row3Cell1.setFontSize(9);
		
		IOTableCell row3Cell2 = row3.createCell();
		row3Cell2.setValue("(AZ55 Grade 80)");
		row3Cell2.setAlignment(Alignment.CENTER);
		row3Cell2.setFontSize(9);
		
		IOTableCell row3Cell3 = row3.createCell();
		row3Cell3.setValue("$125.75");
		row3Cell3.setAlignment(Alignment.CENTER);
		row3Cell3.setFontSize(9);
		
		IOTableCell row3Cell4 = row3.createCell();
		row3Cell4.setValue("$3.92");
		row3Cell4.setAlignment(Alignment.CENTER);
		row3Cell4.setFontSize(9);
		
		return ioTable;
	}
	
	public static IOTable getIOTable2(PDFPage page){
		
		IOTable ioTable = new IOTable();
		//ioTable.setFontType(PDType1Font.HELVETICA);
		ioTable.setWidthPercentage(100, page);
		ioTable.setDrawLines(false);
		ioTable.setCellPadding(0);
		
		IOTableRow header = ioTable.createRow();
		header.setHeaderRow(true);
		
		IOTableCell headerCell1 = header.createCell();
		headerCell1.setValue("SUPER SPAN / LOW RIB");
		headerCell1.setAlignment(Alignment.LEFT);
		headerCell1.setPadding(3);
		headerCell1.setFontSize(9);
		
		IOTableRow row1 = ioTable.createRow();
		
		IOTableCell row1Cell1 = row1.createCell();
		row1Cell1.setValue("29 GA. GALVALUME® Plus");
		row1Cell1.setFontSize(9);
		
		IOTableCell row1Cell2 = row1.createCell();
		row1Cell2.setValue("(AZ55 Grade 80)");
		row1Cell2.setFontSize(9);
		
		IOTableCell row1Cell3 = row1.createCell();
		row1Cell3.setValue("$9999.00");
		row1Cell3.setFontSize(9);
		
		IOTableCell row1Cell4 = row1.createCell();
		row1Cell4.setValue("$318.95");
		row1Cell4.setFontSize(9);
		
		IOTableRow row2 = ioTable.createRow();
		
		IOTableCell row2Cell1 = row2.createCell();
		row2Cell1.setValue("26 GA. SMP Colors");
		row2Cell1.setFontSize(9);
		
		IOTableCell row2Cell2 = row2.createCell();
		row2Cell2.setValue("(AZ50 Grade 80)");
		row2Cell2.setFontSize(9);
		
		IOTableCell row2Cell3 = row2.createCell();
		row2Cell3.setValue("$131.50");
		row2Cell3.setFontSize(9);
		
		IOTableCell row2Cell4 = row2.createCell();
		row2Cell4.setValue("$132.50");
		row2Cell4.setFontSize(9);
		
		IOTableRow row3 = ioTable.createRow();
		
		IOTableCell row3Cell1 = row3.createCell();
		row3Cell1.setValue("22 GA. Poly Polar White");
		row3Cell1.setFontSize(9);
		
		IOTableCell row3Cell2 = row3.createCell();
		row3Cell2.setValue("(AZ55 Grade 80)");
		row3Cell2.setFontSize(9);
		
		IOTableCell row3Cell3 = row3.createCell();
		row3Cell3.setValue("$201.95");
		row3Cell3.setFontSize(9);
		
		IOTableCell row3Cell4 = row3.createCell();
		row3Cell4.setValue("$3.92");
		row3Cell4.setFontSize(9);
		
		IOTableRow row4 = ioTable.createRow();
		
		IOTableCell row4Cell1 = row4.createCell();
		row4Cell1.setValue("22 GA. Poly Polar White");
		row4Cell1.setFontSize(9);
		
		IOTableCell row4Cell2 = row4.createCell();
		row4Cell2.setValue("(AZ55 Grade 80)");
		row4Cell2.setFontSize(9);
		
		IOTableCell row4Cell3 = row4.createCell();
		row4Cell3.setValue("$201.95");
		row4Cell3.setFontSize(9);
		
		IOTableCell row4Cell4 = row4.createCell();
		row4Cell4.setValue("$3.92");
		row4Cell4.setFontSize(9);
		
		IOTableRow row5 = ioTable.createRow();
		
		IOTableCell row5Cell1 = row5.createCell();
		row5Cell1.setValue("22 GA. Poly Polar White");
		row5Cell1.setFontSize(9);
		
		IOTableCell row5Cell2 = row5.createCell();
		row5Cell2.setValue("(AZ55 Grade 80)");
		row5Cell2.setFontSize(9);
		
		IOTableCell row5Cell3 = row5.createCell();
		row5Cell3.setValue("$201.95");
		row5Cell3.setFontSize(9);
		
		IOTableCell row5Cell4 = row5.createCell();
		row5Cell4.setValue("$3.92");
		row5Cell4.setFontSize(9);
		
		IOTableRow row6 = ioTable.createRow();
		
		IOTableCell row6Cell1 = row6.createCell();
		row6Cell1.setValue("29 GA. GALVALUME® Plus");
		row6Cell1.setFontSize(9);
		
		IOTableCell row6Cell2 = row6.createCell();
		row6Cell2.setValue("(AZ55 Grade 80)");
		row6Cell2.setFontSize(9);
		
		IOTableCell row6Cell3 = row6.createCell();
		row6Cell3.setValue("$9999.00");
		row6Cell3.setFontSize(9);
		
		IOTableCell row6Cell4 = row6.createCell();
		row6Cell4.setValue("$318.95");
		row6Cell4.setFontSize(9);
		
		IOTableRow row7 = ioTable.createRow();
		
		IOTableCell row7Cell1 = row7.createCell();
		row7Cell1.setValue("29 GA. GALVALUME® Plus");
		row7Cell1.setFontSize(9);
		
		IOTableCell row7Cell2 = row7.createCell();
		row7Cell2.setValue("(AZ55 Grade 80)");
		row7Cell2.setFontSize(9);
		
		IOTableCell row7Cell3 = row7.createCell();
		row7Cell3.setValue("$9999.00");
		row7Cell3.setFontSize(9);
		
		IOTableCell row7Cell4 = row7.createCell();
		row7Cell4.setValue("$318.95");
		row7Cell4.setFontSize(9);
		
		return ioTable;
	}
	
	public static IOTable getIOTable3(PDFPage page){
		
		IOTable ioTable = new IOTable();
		ioTable.setWidthPercentage(100, page);
		ioTable.setBorderColor(Color.DARK_GRAY);
		
		IOTableRow header = ioTable.createRow();
		header.setHeaderRow(true);
		
		IOTableCell headerCell1 = header.createCell();
		headerCell1.setValue("SUPER SPAN / LOW RIB");
		headerCell1.setBackgroundColor(Color.DARK_GRAY);
		headerCell1.setFontColor(Color.white);
		headerCell1.setAlignment(Alignment.CENTER);
		
		IOTableCell headerCell2 = header.createCell();
		headerCell2.setValue("Grade");
		headerCell2.setBackgroundColor(Color.DARK_GRAY);
		headerCell2.setFontColor(Color.white);
		headerCell2.setAlignment(Alignment.CENTER);
		
		IOTableCell headerCell3 = header.createCell();
		headerCell3.setValue("PRICE/SQR");
		headerCell3.setBackgroundColor(Color.DARK_GRAY);
		headerCell3.setFontColor(Color.white);
		headerCell3.setAlignment(Alignment.CENTER);
		
		IOTableCell headerCell4 = header.createCell();
		headerCell4.setValue("PRICE/LFT");
		headerCell4.setBackgroundColor(Color.DARK_GRAY);
		headerCell4.setFontColor(Color.white);
		headerCell4.setAlignment(Alignment.CENTER);
		
		
		
		IOTableRow row1 = ioTable.createRow();
		
		IOTableCell row1Cell1 = row1.createCell();
		row1Cell1.setValue("29 GA. GALVALUME® Plus");
		row1Cell1.setAlignment(Alignment.CENTER);
		row1Cell1.setFontSize(9);
		
		IOTableCell row1Cell2 = row1.createCell();
		row1Cell2.setValue("(AZ55 Grade 80)");
		row1Cell2.setAlignment(Alignment.CENTER);
		row1Cell2.setFontSize(9);
		
		IOTableCell row1Cell3 = row1.createCell();
		row1Cell3.setValue("$95.25");
		row1Cell3.setAlignment(Alignment.CENTER);
		row1Cell3.setFontSize(9);
		
		IOTableCell row1Cell4 = row1.createCell();
		row1Cell4.setValue("$3.04");
		row1Cell4.setAlignment(Alignment.CENTER);
		row1Cell4.setFontSize(9);
		
		IOTableRow row2 = ioTable.createRow();
		
		IOTableCell row2Cell1 = row2.createCell();
		row2Cell1.setValue("29 GA. GALVALUME® Plus");
		row2Cell1.setAlignment(Alignment.CENTER);
		row2Cell1.setFontSize(9);
		
		IOTableCell row2Cell2 = row2.createCell();
		row2Cell2.setValue("(AZ50 Grade 80)");
		row2Cell2.setAlignment(Alignment.CENTER);
		row2Cell2.setFontSize(9);
		
		IOTableCell row2Cell3 = row2.createCell();
		row2Cell3.setValue("$125.50");
		row2Cell3.setAlignment(Alignment.CENTER);
		row2Cell3.setFontSize(9);
		
		IOTableCell row2Cell4 = row2.createCell();
		row2Cell4.setValue("$4.00");
		row2Cell4.setAlignment(Alignment.CENTER);
		row2Cell4.setFontSize(9);
		
		IOTableRow row3 = ioTable.createRow();
		
		IOTableCell row3Cell1 = row3.createCell();
		row3Cell1.setValue("26 GA. Commodity Colors");
		row3Cell1.setAlignment(Alignment.CENTER);
		row3Cell1.setFontSize(9);
		
		IOTableCell row3Cell2 = row3.createCell();
		row3Cell2.setValue("(AZ55 Grade 80)");
		row3Cell2.setAlignment(Alignment.CENTER);
		row3Cell2.setFontSize(9);
		
		IOTableCell row3Cell3 = row3.createCell();
		row3Cell3.setValue("$125.75");
		row3Cell3.setAlignment(Alignment.CENTER);
		row3Cell3.setFontSize(9);
		
		IOTableCell row3Cell4 = row3.createCell();
		row3Cell4.setValue("$3.92");
		row3Cell4.setAlignment(Alignment.CENTER);
		row3Cell4.setFontSize(9);
		
		return ioTable;
	}
	
	public static void main(String[] args) {
		try {
			generatePDF();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
