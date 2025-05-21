package Model.BO;

import java.util.ArrayList;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;

import Utils.Utils;

public class CombineDocx {
    public static void combineFiles(ArrayList<String> docFilePaths, String output) throws Exception {
        Document firstDocument = new Document();
        try {
            firstDocument.loadFromFile(docFilePaths.get(0), FileFormat.Docx);

            for (int i = 1; i < docFilePaths.size(); i++) {
                Document documentMerge = new Document();
                documentMerge.loadFromFile(docFilePaths.get(i), FileFormat.Docx);

                for (Object sectionObj : documentMerge.getSections()) {
                    Section section = (Section) sectionObj;
                    firstDocument.getSections().add(section.deepClone());
                }
            }

            firstDocument.saveToFile(output, FileFormat.Docx);
            System.out.println("Combine done");

        } catch (Exception e) {
            throw new Exception("Error combined file: " + e.getMessage());
        } finally {
            deleteTemporalFiles(docFilePaths);
        }
    }

    private static void deleteTemporalFiles(ArrayList<String> temporalFiles) {
        for (String filePath : temporalFiles) {
            Utils.deleteFile(filePath);
            Utils.deleteFile(filePath.replace(".docx", ".pdf"));
        }
    }
}
