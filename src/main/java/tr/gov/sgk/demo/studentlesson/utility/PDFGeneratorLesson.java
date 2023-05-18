package tr.gov.sgk.demo.studentlesson.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import tr.gov.sgk.demo.studentlesson.dto.LessonDTO;
import java.io.IOException;
import java.util.List;

@Setter
public class PDFGeneratorLesson {

    private List<LessonDTO> lessonList;
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);
        Paragraph paragraph = new Paragraph("Lesson List", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 2, 2});
        table.setSpacingBefore(2);
        // Create Table Header
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(2);
        // Add Font
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(BaseColor.BLACK);
        cell.setPhrase(new Phrase("Lesson Code", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lesson Name", font));
        table.addCell(cell);
        for (LessonDTO lesson : lessonList) {
            table.addCell(lesson.getLessonCode());
            table.addCell(lesson.getLessonName());
        }
        // Add table to document
        document.add(table);
        document.close();
    }
}
