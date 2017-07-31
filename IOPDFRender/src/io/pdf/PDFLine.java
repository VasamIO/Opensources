package io.pdf;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFLine extends PDFWidget{

	@Override
	public void draw(PDFPage page) throws Exception {
		
		PDPageContentStream contentStream = page.getContentStream();
		float actualY =  page.getPageHeight() - (getHeight() + getStartY());
		float startXPt = PDFRenderer.mmToPt(getStartX());
		float startYPt = PDFRenderer.mmToPt(actualY );
		float widthPt = PDFRenderer.mmToPt(this.width );
		contentStream.drawLine(startXPt, startYPt, startXPt+widthPt, startYPt);
		contentStream.closeSubPath();
	}
}
