package io.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFRenderer {
	
	ByteArrayOutputStream boas;
	PDDocument document;
	List<PDFPage> pages = new ArrayList<PDFPage>();
	
	
	public PDFRenderer() throws Exception {
		document = new PDDocument();
	}
	
	public PDFPage createPage() throws IOException{
		PDFPage pdfPage = new PDFPage(this);
		pages.add(pdfPage);
		return pdfPage;
	}
	
	public void render() throws Exception{
		for(PDFPage pdfPage: pages){
			pdfPage.draw(null);
		}
	}

	public void done() throws Exception {
		File file = File.createTempFile("Export_" + System.currentTimeMillis(), ".pdf");
		document.save(file);
		document.close();
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream _b = new ByteArrayOutputStream();
		_b.write(IOUtils.toByteArray(fis));
		boas = _b;
		fis.close();
		if (file != null) {
			file.delete();
		}
	}
	public ByteArrayOutputStream getContent() {
		return boas;
	}	
	
	public PDDocument getDocument(){
		return document;
	}
	
	public static float mmToPt(float mm){
		return mm  *2.83465f;
	}
	public static float ptTomm(float pt){
		return pt * 0.352778f;
	}
}