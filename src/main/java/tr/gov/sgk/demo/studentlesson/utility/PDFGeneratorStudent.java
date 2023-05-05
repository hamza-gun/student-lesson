//package tr.gov.sgk.demo.studentlesson.utility;
//
//import com.itextpdf.text.DocumentException;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.Setter;
//import tr.gov.sgk.demo.studentlesson.dto.StudentDTO;
//
//import com.lowagie.text.Font;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//import java.io.IOException;
//import java.util.List;
//import java.awt.Color;
//import com.lowagie.text.Document;
//import com.lowagie.text.FontFactory;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//
//@Setter
//public class PDFGeneratorStudent {
//    private List<StudentDTO> studentList;
//    public void generate(HttpServletResponse response) throws DocumentException, IOException {
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//        document.open();
//        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        fontTiltle.setSize(20);
//        Paragraph paragraph = new Paragraph("Student List", fontTiltle);
//        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//        document.add(paragraph);
//        PdfPTable table = new PdfPTable(3);
//        table.setWidthPercentage(100f);
//        table.setWidths(new int[] { 2, 2, 2 });
//        table.setSpacingBefore(3);
//        // Create Table Header
//        PdfPCell cell = new PdfPCell();
//        cell.setBackgroundColor(Color.lightGray);
//        cell.setPadding(3);
//        // Add Font
//        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//        font.setColor(Color.black);
//        cell.setPhrase(new Phrase("Student Number", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Student First Name", font));
//        table.addCell(cell);
//        cell.setPhrase(new Phrase("Student Last Name", font));
//        table.addCell(cell);
//        for (StudentDTO student : studentList) {
//            table.addCell(student.getNumber().toString());
//            table.addCell(student.getFirstName());
//            table.addCell(student.getLastName());
//        }
//        // Add table to document
//        document.add(table);
//        document.close();
//    }
//
//}
