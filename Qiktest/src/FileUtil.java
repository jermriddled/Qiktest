import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File[] getAllFiles(String path) {

        File folder = new File(path);
        return folder.listFiles();

    }

    // Grab all text from first page of test
    public static String getPDFText(File[] listFiles, int i) throws IOException {

        PDDocument document = PDDocument.load(listFiles[i]);
        PDFTextStripper PDFstripper = new PDFTextStripper();
        PDFstripper.setEndPage(1);
        String page = PDFstripper.getText(document);
        document.close();
        return page;

    }
}
