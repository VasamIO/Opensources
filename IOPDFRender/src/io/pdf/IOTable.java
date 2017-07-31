package io.pdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDFont;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.Row;
import be.quodlibet.boxable.line.LineStyle;

public class IOTable extends PDFWidget{
	
	private List<IOTableRow> rows = new ArrayList<>();
	private boolean drawLines = true;
	private float fontSize = 10;
	private PDFont fontType;
	private float cellPadding = -1;
	private Color borderColor;
	private float borderLineSize;
	
	public enum Alignment{
		LEFT, CENTER, RIGHT
	}
	
	public IOTable(){
		
	}
	public IOTable(float width){
		this.width = width;
	}
	
	public class IOTableRow{
		float rowheight = 12;
		List<IOTableCell> cells = new ArrayList<>();
		boolean isHeaderRow = false;
		
		public void setRowHeight(float rowheight){
			this.rowheight = rowheight;
		}
		public float getRowHeight(){
			return this.rowheight;
		}
		public IOTableCell createCell(){
			IOTableCell cell = new IOTableCell();
			cells.add(cell);
			return cell;
		}
		protected List<IOTableCell> getCells(){
			return cells;
		}
		public boolean isHeaderRow() {
			return isHeaderRow;
		}
		public void setHeaderRow(boolean isHeaderRow) {
			this.isHeaderRow = isHeaderRow;
		}
		
	}
	
	public class IOTableCell{
		private float widthPerc;
		private String value;
		private PDFont fontType;
		private float fontSize;
		private Color backgroundColor;
		private Color fontColor;
		private Alignment alignment;
		private float padding = -1;
		
		public float getWidthPerc() {
			return widthPerc;
		}
		public void setWidthPerc(float widthPerc) {
			this.widthPerc = widthPerc;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public PDFont getFontType() {
			return fontType;
		}
		public void setFontType(PDFont fontType) {
			this.fontType = fontType;
		}
		public Color getBackgroundColor() {
			return backgroundColor;
		}
		public void setBackgroundColor(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
		}
		public Color getFontColor() {
			return fontColor;
		}
		public void setFontColor(Color fontColor) {
			this.fontColor = fontColor;
		}
		public Alignment getAlignment() {
			return alignment;
		}
		public void setAlignment(Alignment alignment) {
			this.alignment = alignment;
		}
		public float getFontSize() {
			return fontSize;
		}
		public void setFontSize(float fontSize) {
			this.fontSize = fontSize;
		}
		public float getPadding() {
			return padding;
		}
		public void setPadding(float padding) {
			this.padding = padding;
		}
		
	}
	
	
	public float getCellPadding() {
		return cellPadding;
	}
	public void setCellPadding(float cellPadding) {
		this.cellPadding = cellPadding;
	}
	public IOTableRow createRow(){
		IOTableRow tableRow = new IOTableRow();
		rows.add(tableRow);
		return tableRow;
	}
	

	public boolean isDrawLines() {
		return drawLines;
	}
	public void setDrawLines(boolean drawLines) {
		this.drawLines = drawLines;
	}
	
	public float getFontSize() {
		return fontSize;
	}
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}
	
	public PDFont getFontType() {
		return fontType;
	}
	public void setFontType(PDFont fontType) {
		this.fontType = fontType;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public float getBorderLineSize() {
		return borderLineSize;
	}
	public void setBorderLineSize(float borderLineSize) {
		this.borderLineSize = borderLineSize;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public void draw(PDFPage page) throws Exception {
		float yStartNewPage = page.getPage().getMediaBox().getHeight() - (2 * marginLeft);
		float width = PDFRenderer.mmToPt((this.width > 0 ) ? this.width : page.getPageOffsetWidth());
		float startX = PDFRenderer.mmToPt(this.startX);
		startY = PDFRenderer.mmToPt(page.getPageHeight() - this.startY);
		BaseTable table = new BaseTable(startY,yStartNewPage, page.getPaddingBottom(), width, 
				startX, page.getDoument(), page.getPage(), this.drawLines, true);
		float cellWidthPerc;
		for(IOTableRow row: rows){
			List<IOTableCell> rowCells = row.getCells();
			cellWidthPerc = 100 / rowCells.size();
			Row baseTableRow = table.createRow(row.getRowHeight());
			for(IOTableCell cell : rowCells){
				cellWidthPerc = cell.getWidthPerc() > 0 ? cell.getWidthPerc() : cellWidthPerc;
				Cell baseTableCell = baseTableRow.createCell( cellWidthPerc,cell.getValue());
				if(cell.getAlignment() != null){
					switch (cell.getAlignment()) {
					case LEFT: baseTableCell.setAlign(HorizontalAlignment.LEFT);break;
					case RIGHT:baseTableCell.setAlign(HorizontalAlignment.RIGHT);break;
					case CENTER:baseTableCell.setAlign(HorizontalAlignment.CENTER);break;
					default:break;
					}
				}
				float cellPadding = -1;
				if(cell.getPadding() > -1){
					cellPadding = cell.getPadding();
				}else if(getCellPadding() > -1){
					cellPadding = getCellPadding();
				}
				if(cellPadding > -1){
					baseTableCell.setBottomPadding(cellPadding);
					baseTableCell.setTopPadding(cellPadding);
					baseTableCell.setLeftPadding(cellPadding);
					baseTableCell.setRightPadding(cellPadding);
				}
				
				if(cell.getFontType() != null){
					baseTableCell.setFont(cell.getFontType());
				}else if(getFontType() != null){
					baseTableCell.setFont(getFontType());
				}
				if(cell.getFontSize() > 0){
					baseTableCell.setFontSize(cell.getFontSize());
				}else{
					baseTableCell.setFontSize(getFontSize());
				}
				if(cell.getFontColor() != null)baseTableCell.setTextColor(cell.getFontColor());
				if(cell.getBackgroundColor() != null)baseTableCell.setFillColor(cell.getBackgroundColor());
				
				if(this.borderColor != null){
					borderLineSize = (borderLineSize > 0) ? borderLineSize : 0.2f;
					LineStyle lineStyle = new LineStyle(borderColor, 0.2f);
					baseTableCell.setBorderStyle(lineStyle);
				}
				if(row.isHeaderRow){
					baseTableCell.setHeaderCell(true);
				}
				
			}
		}
		table.draw();
	}

	/*@Override
	public void draw(PDFPage page) throws Exception {
		float yStartNewPage = page.getPage().getMediaBox().getHeight() - (2 * marginLeft);
		
		float width = (this.width > 0 ) ? this.width : page.getPageOffsetWidth();
		float startX = page.getPaddingLeft() + this.getMarginLeft();
		 
		width = PDFRenderer.mmToPt(width);
		startX = PDFRenderer.mmToPt(startX);
		startY = PDFRenderer.mmToPt(page.getPageHeight() - this.startY);
		
		BaseTable table = new BaseTable(startY,yStartNewPage, page.getPaddingBottom(), width, 
				startX, page.getDoument(), page.getPage(), true, true);
		
		Row row1 = table.createRow(12);
		Cell cell = row1.createCell(40, "Cutoff");
		 row1.createCell(50, "Monday");
		 
		 Row row2 = table.createRow(12);
		 row2.createCell(40, "Delivery");
		 row2.createCell(50, "By Thursday of the same week");
			 
		 Row row3 = table.createRow(12);
		 row3.createCell(40, "Weight min.");
		 row3.createCell(50, "500 lbs. or $50.00");
		 
		 table.draw();
		
	}*/
}
