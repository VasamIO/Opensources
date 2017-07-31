package io.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFText extends PDFWidget{

	private float fontSize = 12;
	private String text;
	private Color fontColor = Color.black;
	private boolean isBold = false;
	
	private PDFont fontType = PDType1Font.HELVETICA;
	private PDFont fontTypeBold = PDType1Font.HELVETICA_BOLD;
	private PDFont fontTypeItalic = PDType1Font.HELVETICA_OBLIQUE;
	private PDFont fontTypeBoldItalic = PDType1Font.HELVETICA_BOLD_OBLIQUE;
	
	public PDFText(String text){
		this.text = text;
	}
	
	public void setFontType(PDFont fontType){
		this.fontType = fontType;
	}
	
	public PDFont getFontType(){
		return this.fontType;
	}
	
	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
    public float getFontHeight() {
        return ( fontType.getFontDescriptor().getCapHeight()) / 1000 * fontSize;
    }

    public float getHeight() {
        return ( fontType.getFontDescriptor().getCapHeight()) / 1000 * fontSize;
    }
    
    public float getFontWidth() {
        return fontType.getFontDescriptor().getFontBoundingBox().getWidth() / 1000 * fontSize;
    }
    
    public float getStringWidth() throws IOException{
    	return fontType.getStringWidth( this.text )*fontSize/1000f;
    }
    
    public void setFontColor(Color fontColor){
    	this.fontColor = fontColor;
    }
    
    public boolean isBold() {
		return isBold;
	}

	public void setBold(boolean isBold) {
		this.isBold = isBold;
		if (isBold)  fontType = fontTypeBold;
	}
	
	public List<String> getLines() {
        List<String> result = new ArrayList<>();
        String[] split = text.split("(?<=\\W)");
        int[] possibleWrapPoints = new int[split.length];
        possibleWrapPoints[0] = split[0].length();
        for (int i = 1; i < split.length; i++) {
            possibleWrapPoints[i] = possibleWrapPoints[i - 1] + split[i].length();
        }
        int start = 0;
        int end = 0;
        for (int i : possibleWrapPoints) {
            float width = 0;
            try {
                width = fontType.getStringWidth(text.substring(start, i)) / 1000 * fontSize;
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
            if (start < end && width > (this.width - 10)) {
                result.add(text.substring(start, end));
                start = end;
            }
            end = i;
        }
        result.add(text.substring(start));
        return result;
    }

	@SuppressWarnings("deprecation")
	@Override
	public void draw(PDFPage page) throws Exception {
		/*Converting PDFBox coordinate-system =*/
		float actualY =  page.getPageHeight() - ( startY +  PDFRenderer.ptTomm(getFontHeight()));
		float startXPt = PDFRenderer.mmToPt(getStartX());
		float startYPt = PDFRenderer.mmToPt(actualY );
		PDPageContentStream contentStream = page.getContentStream();
		contentStream.beginText();
		contentStream.setFont( fontType, fontSize );
		contentStream.setNonStrokingColor(fontColor);
		contentStream.moveTextPositionByAmount( startXPt, startYPt );
        contentStream.appendRawCommands(getFontHeight() + " TL\n");
        contentStream.drawString(text.trim());
        contentStream.endText();
        contentStream.closeSubPath();
	}

}
