package tr.gov.sgk.demo.studentlesson.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import tr.gov.sgk.demo.studentlesson.dto.StudentNotesDTO;

import java.io.IOException;
import java.util.List;

@Setter
public class PDFGeneratorNote {
    private List<StudentNotesDTO> noteList;
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);
        Paragraph paragraph = new Paragraph("Note List", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 2, 2, 2, 2, 2 });
        table.setSpacingBefore(5);
        // Create Table Header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);
        // Add Font
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(BaseColor.BLACK);
        cell.setPhrase(new Phrase("Student Note", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Student First Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Student Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lesson Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lesson Code", font));
        table.addCell(cell);
        for (StudentNotesDTO note : noteList) {
            table.addCell(note.getNote().toString());
            table.addCell(note.getStudent().getFirstName());
            table.addCell(note.getStudent().getLastName());
            table.addCell(note.getLesson().getLessonName());
            table.addCell(note.getLesson().getLessonCode());
        }
        // Add table to document
        document.add(table);
        document.close();
    }

}
