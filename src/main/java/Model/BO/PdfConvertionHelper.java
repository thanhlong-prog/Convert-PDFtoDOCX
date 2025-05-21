package Model.BO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.spire.pdf.PdfDocument;

public class PdfConvertionHelper {
    private static final int MAX_PAGES_PER_FILE = 10;

    public static void convertPdfToDoc(String fileInput) throws Exception {
        File f = new File(fileInput);
        if (f.exists()) {
            String fileOutput = fileInput.replace(".pdf", ".docx");
            convertPdfToDoc(fileInput, fileOutput);
        }
    }

    private static void convertPdfToDoc(String fileInput, String fileOutput) throws Exception {
        ArrayList<String> pathOfChunkFiles = splitPdf(fileInput);
        ArrayList<String> fileDocxPaths = convertChunkPdfToDocx(pathOfChunkFiles);
        Collections.sort(fileDocxPaths);
        CombineDocx.combineFiles(fileDocxPaths, fileOutput);
    }

    /**
     * Chia file pdf thành các file nhỏ hơn, mỗi file tối đa 10 trang
     * 
     * @param filePath đường dẫn file pdf
     * @return danh sách file pdf nhỏ
     */
    private static ArrayList<String> splitPdf(String filePath) throws Exception {
        ArrayList<String> pathOfChunkFiles = new ArrayList<>();
        String fileNameWithoutExtension = filePath.replace(".pdf", "").replaceAll(" ", "");

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            int totalPages = document.getNumberOfPages();
            int fileIndex = 1;

            for (int start = 0; start < totalPages; start += MAX_PAGES_PER_FILE) {
                int end = Math.min(start + MAX_PAGES_PER_FILE, totalPages);

                try (PDDocument chunkDocument = new PDDocument()) {
                    for (int page = start; page < end; page++) {
                        chunkDocument.addPage(document.getPage(page));
                    }

                    String outputPdf = fileNameWithoutExtension + "_part_" + fileIndex + ".pdf";
                    chunkDocument.save(outputPdf);
                    pathOfChunkFiles.add(outputPdf);
                    fileIndex++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error convert PDF by PDFBox: " + e.getMessage());
            throw e;
        }

        return pathOfChunkFiles;
    }

    private static ArrayList<String> convertChunkPdfToDocx(ArrayList<String> chunkFiles) {
        ArrayList<String> docFilePaths = new ArrayList<>();

        int total = chunkFiles.size();
        for (int i = 0; i < total; i++) {
            String filePath = chunkFiles.get(i);
            String outputPath = filePath.replace(".pdf", ".docx");

            PdfDocument pdf = new PdfDocument();
            pdf.loadFromFile(filePath);
            pdf.saveToFile(outputPath, com.spire.pdf.FileFormat.DOCX);

            docFilePaths.add(outputPath);

            int percent = (int) (((i + 1) * 100.0f) / total);
            System.out.print("\rConverting: " + percent + "% (" + (i + 1) + "/" + total + ")");
        }

        System.out.println("\nConvert to sub docx files done, combining them ...");
        return docFilePaths;
    }

}
