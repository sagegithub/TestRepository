package heiss.sage.pdf;
/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
 
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
 
/**
 * First iText example: Hello World.
 */
public class HelloWorldPDF {
 
    /** Path to the resulting PDF file. */
    public static final String RESULT
        = "C:/HMI/Git/SageTestProject01/hello.pdf";
    public Document document;
 
    /**
     * Creates a PDF file: hello.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args)
    	throws DocumentException, IOException {
    	new HelloWorldPDF().createPdf(RESULT);
    }
 
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename)
	throws DocumentException, IOException {
        // step 1
        document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Background event = new Background();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("Hello World! Test..."));
        
        addBlackOnRed();
        
        addLists();
        
        addSimpleTable101();
        
        addTableWithSpecificWidths();
        
        // step 5
        document.close();
    }
    
    private void addBlackOnRed() throws DocumentException{
    	Font f = new Font();
        Chunk c = new Chunk("Black text on red background", f);
        c.setBackground(Color.RED);
        Paragraph p = new Paragraph(c);
        document.add(p);
    }
    
    private void addLists() throws DocumentException{
    	String text = "test 1 2 3 ";
        for (int i = 0; i < 5; i++) {
            text = text + text;
        }
        List list = new List(List.UNORDERED);
        ListItem item = new ListItem(text);
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);
        text = "a b c align ";
        for (int i = 0; i < 5; i++) {
            text = text + text;
        }
        item = new ListItem(text);
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);
        text = "supercalifragilisticexpialidocious ";
        for (int i = 0; i < 3; i++) {
            text = text + text;
        }
        item = new ListItem(text);
        item.setAlignment(Element.ALIGN_RIGHT);
        list.add(item);
        document.add(list);
    }
    
    private void addSimpleTable101() throws DocumentException{
    	PdfPTable table = new PdfPTable(8);
        for(int aw = 0; aw < 16; aw++){
            table.addCell("hi");
        }
        document.add(table);
    }
    
    private void addTableWithSpecificWidths() throws DocumentException{
    	float[] columnWidths = {1, 5, 5};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        Font f = new Font();
        PdfPCell cell = new PdfPCell(new Paragraph("This is a header", f));
        cell.setBackgroundColor(Color.GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(3);
        table.addCell(cell);
        table.getDefaultCell().setBackgroundColor(Color.BLUE);
        for (int i = 0; i < 2; i++) {
            table.addCell("#");
            table.addCell("Key");
            table.addCell("Value");
        }
        table.setHeaderRows(3);
        table.setFooterRows(1);
        table.getDefaultCell().setBackgroundColor(Color.GREEN);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (int counter = 1; counter < 15; counter++) {
            table.addCell(String.valueOf(counter));
            table.addCell("key " + counter);
            table.addCell("value " + counter);
        }
        document.add(table);
    }
    
    public class Background extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            int pagenumber = writer.getPageNumber();
            if (pagenumber % 2 == 1 && pagenumber != 1)
                return;
            PdfContentByte canvas = writer.getDirectContentUnder();
            Rectangle rect = document.getPageSize();
            canvas.setColorFill(pagenumber < 2 ? Color.LIGHT_GRAY : Color.WHITE);
            canvas.rectangle(rect.left(), rect.bottom(), rect.width(), rect.height());
            canvas.fill();
            
            canvas = writer.getDirectContent();
            rect = document.getPageSize();
            rect.setBorder(Rectangle.BOX); // left, right, top, bottom border
            rect.setBorderWidth(5); // a width of 5 user units
            rect.setBorderColor(Color.RED); // a red border
            rect.setUseVariableBorders(true); // the full width will be visible
            canvas.rectangle(rect);
        }
    }
}
