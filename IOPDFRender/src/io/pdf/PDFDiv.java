package io.pdf;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFDiv extends PDFWidget{
	
	List<PDFWidget> childs =  new ArrayList<>();
	
	public PDFDiv(){
		super();
	}
	
	public List<PDFWidget> getChilds(){
		return childs;
	} 
	
	public void appendChild(PDFWidget child){
		childs.add(child);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void draw(PDFPage page) throws Exception{
		if(alignParentBottom){
			this.startY = (getParent().getHeight() - this.getHeight());
		}
		for(PDFWidget child : childs){
			child.setParent(this);
			child.setStartX(startX);
			child.setStartY(startY);
			child.draw(page);
		}
		PDPageContentStream contentStream = page.getContentStream();
		/*Converting PDFBox coordinate-system =*/
		float actualY =  page.getPageHeight() - (getHeight() + getStartY());
		float startXPt = PDFRenderer.mmToPt(getStartX());
		float startYPt = PDFRenderer.mmToPt(actualY );
		float widthPt = PDFRenderer.mmToPt(this.width );
		float heightPt = PDFRenderer.mmToPt(this.height);
		
		drawBorders(contentStream, startXPt, startYPt, widthPt, heightPt );
		if(isAllowBorderTop()) startYPt++;
		if(isAllowBorderRight()) widthPt = widthPt-2;
		if(isAllowBorderBottom()) heightPt--;
		if(isAllowBorderLeft()) startXPt++;
		if(background != null){
			 contentStream.setNonStrokingColor(background);
			 contentStream.fillRect(startXPt, startYPt,widthPt, heightPt);
		}
	}
}
