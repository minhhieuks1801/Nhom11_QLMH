/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.LopHocPhan;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dat
 */
public class PDFExporter {
  private String path;
  private Document doc;
  private final List<String> dsthu=Arrays.asList("Hai","Ba","Tư","Năm","Sáu","Bảy","Chủ nhật");
  public PDFExporter(String path) throws FileNotFoundException, DocumentException{
    doc=new Document();
    PdfWriter.getInstance(doc, new FileOutputStream(path));
  }
  public void exportStudentTimeTable(Map<String,List<LopHocPhan>> data,int maxRow) throws Exception{
    doc.open();
    PdfPTable table=new PdfPTable(7);
    BaseFont baseFont=BaseFont.createFont("assets/fonts/vuArial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font boldFont=new Font(baseFont, 18, Font.BOLD);
    Font italicFont=new Font(baseFont, 18, Font.ITALIC);
    Arrays.asList("Thứ hai","Thứ ba","Thứ tư","Thứ năm","Thứ sáu","Thứ bảy","Chủ nhật").forEach(title->{
      Phrase header=new Phrase(title,boldFont);
      PdfPCell cell=new PdfPCell(header);
      cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
      cell.setBorderColor(BaseColor.BLACK);
      table.addCell(cell);
    });
    for(int r=0;r<maxRow;++r){
      for(int c=0;c<7;++c){
        List<LopHocPhan> dslhp=data.get(dsthu.get(c));
        PdfPCell cell=new PdfPCell();
        cell.setBorderColor(BaseColor.BLACK);
        if(dslhp!=null && r<dslhp.size()){
          LopHocPhan lhp=dslhp.get(r);
          Paragraph p1=new Paragraph(lhp.getMaLHP(),boldFont);
          Paragraph p2=new Paragraph(lhp.getHocPhan().getTenHP(),italicFont);
          Paragraph p3=new Paragraph(lhp.getTiet(),boldFont);
          Paragraph p4=new Paragraph(lhp.getPhong(),italicFont);
          cell.addElement(p1);
          cell.addElement(p2);
          cell.addElement(p3);
          cell.addElement(p4);
        }
        table.addCell(cell);
      }
    }
    doc.add(table);
    doc.close();
  }
}
