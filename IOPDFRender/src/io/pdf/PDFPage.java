package io.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFPage extends PDFWidget{
	
	private float paddingTop = 10, paddingRight = 10, paddingBottom = 0, paddingLeft = 10;
	private float currentX, currentY, pageWidth, pageHeight;
	private PDDocument document;
	private PDPage pdPage;
	private PDPageContentStream contentStream;
	
	private List<PDFWidget> widgets = new ArrayList<>();

	public PDFPage(PDFRenderer renderer) throws IOException{
		document = renderer.getDocument();
		pdPage = new PDPage();
		document.addPage(pdPage);
		contentStream = new PDPageContentStream(document, pdPage);
		currentX = paddingLeft;
		currentY = paddingTop;
		pageWidth = PDFRenderer.ptTomm(pdPage.getMediaBox().getWidth());
		pageHeight = PDFRenderer.ptTomm(pdPage.getMediaBox().getHeight());
	}
	
	public List<PDFWidget> getWidgets() {
		return widgets;
	}
	public void addWidget(PDFWidget widget) {
		widgets.add(widget);
	}
	public PDPage getPage(){
		return pdPage;
	}

	public float getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(float paddingTop) {
		this.paddingTop = paddingTop;
	}

	public float getPaddingRight() {
		return paddingRight;
	}

	public void setPaddingRight(float paddingRight) {
		this.paddingRight = paddingRight;
	}

	public float getPaddingBottom() {
		return paddingBottom;
	}

	public void setPaddingBottom(float paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	public float getPaddingLeft() {
		return paddingLeft;
	}

	public void setPaddingLeft(float paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	public float getCurrentX() {
		return currentX;
	}

	public void setCurrentX(float currentX) {
		this.currentX = currentX;
	}

	public float getCurrentY() {
		return currentY;
	}

	public void setCurrentY(float currentY) {
		this.currentY = currentY;
	}

	public float getPageWidth() {
		return pageWidth;
	}
	
	public float getPageOffsetWidth() {
		return pageWidth - (paddingLeft + paddingRight);
	}

	public void setPageWidth(float pageWidth) {
		this.pageWidth = pageWidth;
	}
	
	public float getPageOffsetHeight() {
		return pageHeight - (paddingTop + paddingBottom);
	}

	public float getPageHeight() {
		return pageHeight;
	}

	public void setPageHeight(float pageHeight) {
		this.pageHeight = pageHeight;
	}
	
	public PDPageContentStream getContentStream(){
		return contentStream;
	}
	
	public PDDocument getDoument(){
		return document;
	}
	
	public float getHeight(){
		return pageHeight;
	}
	
	public float getWidth(){
		return pageWidth;
	}

	@Override
	public void draw(PDFPage page) throws Exception {
		for(PDFWidget widget : widgets){
			currentX = paddingLeft;
			currentY = paddingTop;
			widget.setParent(this);
			widget.setStartX(currentX);
			widget.setStartY(currentY);
			widget.draw(this);
		}
		contentStream.close();
	}
}
