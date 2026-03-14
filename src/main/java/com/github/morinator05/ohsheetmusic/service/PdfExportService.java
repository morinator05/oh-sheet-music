package com.github.morinator05.ohsheetmusic.service;

import com.github.morinator05.ohsheetmusic.model.PieceOfMusic;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfExportService {

    public static void exportToPdf(String path, List<PieceOfMusic> piecesToExport) {

        System.out.println("export: starting export");

        Font font8 = FontFactory.getFont(FontFactory.HELVETICA, 8);

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
            float[] columnDefinitionSize = {25F, 75F};

            float pos = height / 2;
            PdfPTable table = null;
            PdfPCell cell = null;

            table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(0);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);

            cell = new PdfPCell(new Phrase("Pieces of Music + location in the Register"));
            cell.setColspan(columnDefinitionSize.length);
            table.addCell(cell);

            for(PieceOfMusic p : piecesToExport) {
                table.addCell(new Phrase(p.getTitle(), font8));
                table.addCell(new Phrase((p.getNumber() + p.getLetter()), font8));
            }

            document.add(table);

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }

        // step 5
        document.close();
        System.out.println("export: finished -> " + path);
    }

}
