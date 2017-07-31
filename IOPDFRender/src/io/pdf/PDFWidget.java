package io.pdf;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

public abstract class PDFWidget {
	
	float width = 0;
	float height = 0;
	float startX = 0;
	float startY = 0;
	Color background;
	float absoluteX = -1, absoluteY = -1;
	float widthPerc, heightPerc;
	boolean alignParentBottom;
	
	private float borderTop, borderRight, borderBottom, borderLeft;
	protected float marginTop;
	protected float marginLeft;
	protected float borderWidth = 1;//PX
	private PDFWidget rightOfWidget, bottomOfWidget, parent;
	protected boolean allowBorderTop = false, allowBorderRight = false, 
			allowBorderBottom = false, allowBorderLeft = false, allowBorders = true;
	
	public PDFWidget(){}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public void setWidthPercentage(int widthPerc, PDFPage page){
		this.width = page.getPageOffsetWidth()*widthPerc / 100;
	}
	
	public void setHeightPercentage(int heightPerc, PDFPage page){
		this.height = page.getPageOffsetWidth()*heightPerc / 100;
	}
	
	public float getOffsetWidth(){
		return width + marginLeft;
	}
	public void setHeight(float height){
		this.height = height;
	}
	public float getOffsetHeight(){
		return height + marginTop;
	}
	public float getStartX() {
		return startX;
	}
	public void setStartX(float startX) {
		this.startX = startX + this.marginLeft;
		if(rightOfWidget != null){
			this.startX += rightOfWidget.getWidth();
		}
	}
	public float getStartY() {
		return startY;
	}
	public void setStartY(float startY) {
		this.startY = startY + this.marginTop;
		if(bottomOfWidget != null){
			this.startY = bottomOfWidget.getHeight()  + bottomOfWidget.getStartY() + this.marginTop;
		}
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	public void setBorders(float borderTop, float borderRight, float borderBottom, float borderLeft){
		this.borderTop = borderTop;
		this.borderRight = borderRight;
		this.borderBottom = borderBottom;
		this.borderLeft = borderLeft;
	}

	public float getBorderTop() {
		return borderTop;
	}

	public void setBorderTop(float borderTop) {
		this.borderTop = borderTop;
		this.allowBorderTop = true;
	}

	public float getBorderRight() {
		return borderRight;
	}

	public void setBorderRight(float borderRight) {
		this.borderRight = borderRight;
		this.allowBorderRight = true;
	}

	public float getBorderBottom() {
		return borderBottom;
	}

	public void setBorderBottom(float borderBottom) {
		this.borderBottom = borderBottom;
		this.allowBorderBottom = true;
	}

	public float getBorderLeft() {
		return borderLeft;
	}

	public void setBorderLeft(float borderLeft) {
		this.borderLeft = borderLeft;
		this.allowBorderLeft = true;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}
	
	public void setMargins(float marginTop, float marginLeft){
		this.marginTop = marginTop;
		this.marginLeft = marginLeft;
	}

	public float getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(float marginTop) {
		this.marginTop = marginTop;
	}

	public float getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(float marginLeft) {
		this.marginLeft = marginLeft;
	}
	public Color getBackground() {
		return background;
	}
	public void setBackground(Color background) {
		this.background = background;
	}

	public void borderTop(boolean borderTop){
		allowBorderTop = borderTop;
	}
	public void borderBottom(boolean borderBottom){
		allowBorderBottom = borderBottom;
	}
	public void borderLeft(boolean borderLeft){
		allowBorderLeft = borderLeft;
	}
	public void borderRight(boolean borderRight){
		allowBorderRight = borderRight;
	}
	public void borders(boolean borders){
		allowBorders = borders;
		allowBorderLeft = borders;
		allowBorderTop = borders;
		allowBorderRight = borders;
		allowBorderBottom = borders;
	}
    
	public boolean isAllowBorderTop() {
		return allowBorderTop;
	}
	public boolean isAllowBorderRight() {
		return allowBorderRight;
	}
	public boolean isAllowBorderBottom() {
		return allowBorderBottom;
	}
	public boolean isAllowBorderLeft() {
		return allowBorderLeft;
	}
	public boolean isAllowBorders() {
		return allowBorders;
	}
	public abstract void draw(PDFPage page) throws Exception;
	
	public void setRightOf(PDFWidget widget){
		rightOfWidget = widget;
	}
	
	public void setBottomOf(PDFWidget widget){
		bottomOfWidget = widget;
	}
	
	public void setX(float x){
		this.absoluteX = x;
	}
	public void setY(float y){
		this.absoluteY = y;
	}
	
	public float getAbsoluteX(){
		return this.absoluteX;
	}
	public float getAbsoluteY(){
		return this.absoluteY ;
	}
	
	
	public boolean isAlignParentBottom() {
		return alignParentBottom;
	}

	public void setAlignParentBottom(boolean alignParentBottom) {
		this.alignParentBottom = alignParentBottom;
	}
	

	public PDFWidget getParent() {
		return parent;
	}

	public void setParent(PDFWidget parent) {
		this.parent = parent;
	}

	@SuppressWarnings("deprecation")
	public void drawBorders(PDPageContentStream contentStream, float startXPt, float startYPt,
			float widthPt, float heightPt ) throws IOException{
		if(this.isAllowBorders()){
			contentStream.setNonStrokingColor(Color.black);
			if(isAllowBorderTop()){
				float borderTop = this.getBorderTop() > 0 ? this.getBorderTop() : (widthPt+startXPt);
				contentStream.drawLine(startXPt, startYPt+heightPt,borderTop, startYPt+heightPt);
			}
			if(isAllowBorderRight()){
				float borderRight = this.getBorderRight() > 0 ? this.getBorderTop() : (startYPt+heightPt);
				contentStream.drawLine(startXPt+widthPt, startYPt, startXPt+widthPt,borderRight );
			}
			if(isAllowBorderBottom()){
				float borderBottom = this.getBorderBottom() > 0 ? this.getBorderBottom() : (widthPt+startXPt);
				contentStream.drawLine(startXPt, startYPt, borderBottom , startYPt);
			}
			if(isAllowBorderLeft()){
				float borderLeft = this.getBorderLeft() > 0 ? this.getBorderLeft() : (startYPt+heightPt);
				contentStream.drawLine(startXPt, startYPt, startXPt, borderLeft);
				
			}
			contentStream.closeSubPath();
		}
	}
	
}
