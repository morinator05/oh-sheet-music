package com.github.morinator05.ohsheetmusic.service;

import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfExportService {

    public static void exportToPdf(String path, List<PieceOfMusic> piecesToExport) {

        System.out.println("export: started!");

        Font font12 = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // step 1
        Document document = new Document(PageSize.A4);

        try {
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(path));
            float width = document.getPageSize().getWidth();
            float height = document.getPageSize().getHeight();
            // step 3
            document.open();

            // step 4
            float[] columnDefinitionSize = {80F, 20F};

            float pos = height / 2;
            PdfPTable table = null;
            PdfPCell cell = null;

            table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);

            cell = new PdfPCell(new Phrase("Oh Sheet Music export"));
            cell.setColspan(columnDefinitionSize.length);
            table.addCell(cell);

            piecesToExport.sort(
                    (p1, p2) -> p1.getTitle().compareTo(p2.getTitle())
            );

            int count = 0;
            for (PieceOfMusic p : piecesToExport) {
                Color backgroundColor = (count % 2 == 0) ? Color.WHITE : Color.LIGHT_GRAY;

                PdfPCell titleCell = new PdfPCell(new Phrase(p.getTitle(), font12));
                titleCell.setBackgroundColor(backgroundColor);
                table.addCell(titleCell);

                PdfPCell numCell = new PdfPCell(new Phrase(p.getNumber() + p.getLetter(), font12));
                numCell.setBackgroundColor(backgroundColor);
                table.addCell(numCell);

                count++;
            }

            document.add(table);
            System.out.println("export finished: " + path);

        } catch (DocumentException | IOException de) {
            System.err.println("export error: " + de.getMessage());
        }

        // step 5
        document.close();
    }
}
