package io.pdf;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFImage extends PDFWidget{
	
	private File file;
	
	public PDFImage(String filePath, float width, float height){
		file = new File(filePath);
		this.width = width;
		this.height = height;
	}
	public PDFImage(File file, float width, float height){
		file = this.file;
		this.width = width;
		this.height = height;
	}
	public PDFImage(URL remoteFileUrl, float width, float height) throws IOException{
		FileUtils.copyURLToFile(remoteFileUrl, file);
		this.width = width;
		this.height = height;
	}
	
	public PDFImage(String filePath){
		file = new File(filePath);
	}
	public PDFImage(File file){
		file = this.file;
	}
	public PDFImage(URL remoteFileUrl) throws IOException{
		FileUtils.copyURLToFile(remoteFileUrl, file);
	}

	@Override
	public void draw(PDFPage page) throws Exception {
		if(alignParentBottom){
			//this.startY = (getParent().getHeight() - this.getHeight());
		}
		PDImageXObject pdImage = PDImageXObject.createFromFileByContent(file, page.getDoument());
		float width = this.width > 0 ? this.width : PDFRenderer.ptTomm(pdImage.getWidth());
		float height = this.height > 0 ? this.height : PDFRenderer.ptTomm(pdImage.getHeight());
		float startYPt = this.getAbsoluteY() > -1 ? this.getAbsoluteY() : (getStartY()); 
		float actualY =  PDFRenderer.mmToPt(page.getPageHeight() - (startYPt + height));
		float startXPt = PDFRenderer.mmToPt(this.getAbsoluteX() > -1 ? this.getAbsoluteX() : getStartX());
		page.getContentStream().drawImage(pdImage, startXPt, actualY,  PDFRenderer.mmToPt(width),  
				PDFRenderer.mmToPt(height));
	}

}
