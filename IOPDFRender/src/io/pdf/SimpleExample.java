package io.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;

public class SimpleExample {
	public static void main(String args[]) throws Exception {

		PDPage myPage = new PDPage(PDRectangle.A4);
		PDDocument mainDocument = new PDDocument();
		PDPageContentStream contentStream = new PDPageContentStream(mainDocument, myPage);
		contentStream.beginText();
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
		contentStream.moveTextPositionByAmount(50, 700);
		contentStream.drawString("This is a title");
		contentStream.endText();
		PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\venkatesh_vasam\\Downloads\\image020.png", mainDocument);
		int w = pdImage.getWidth();
		int h = pdImage.getHeight();
		if (h > 100) {
			int newh = 100;
			double ration = (double) h / (double) newh;
			int neww = (int) (w / ration);
			contentStream.drawImage(pdImage, 50, 575, neww, newh);
		} else {
			contentStream.drawImage(pdImage, 50, 575, w, h);
		}
		float margin = 50;
		float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
		float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);
		boolean drawContent = true;
		float yStart = yStartNewPage;
		float bottomMargin = 70;
		BaseTable table = new BaseTable(550, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage,
				true, drawContent);
		Row<PDPage> headerRow = table.createRow(15f);
		Cell<PDPage> cell = headerRow.createCell(100, "Header");
		table.addHeaderRow(headerRow);
		Row<PDPage> row = table.createRow(12);
		cell = row.createCell(30, "Data 1");
		cell = row.createCell(70, "Some value");
		table.draw();
		contentStream.close();
		mainDocument.addPage(myPage);
		mainDocument.save("C:\\Users\\venkatesh_vasam\\Downloads\\testfile.pdf");
		mainDocument.close();
	}
}