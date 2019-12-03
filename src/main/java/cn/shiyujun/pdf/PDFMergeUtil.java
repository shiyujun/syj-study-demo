package cn.shiyujun.pdf;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
/**
 * d
 *
 * @author syj
 * CreateTime 2019/12/03
 * describe:
 */
public class PDFMergeUtil {
    public static void main(String[] args) {
        String[] files = {"file:///C:/Users/admin/Desktop/1.pdf", "file:///C:/Users/admin/Desktop/2.pdf"};
        String savepath = "C:\\Users\\admin\\Desktop\\test.pdf";
        try {
            combinPdf(files,savepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void combinPdf(String[] pdfFilenames, String targetFilename) throws Exception {
        PdfReader reader = null;
        Document doc = new Document();
        PdfCopy pdfCopy = new PdfCopy(doc, new FileOutputStream(targetFilename));
        int pageCount = 0;
        doc.open();
        for (int i = 0; i < pdfFilenames.length; ++i) {
            System.out.println(pdfFilenames[i]);
            reader = new PdfReader(pdfFilenames[i]);
            pageCount = reader.getNumberOfPages();
            for (int j = 1; j <= pageCount; ++j) {
                pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
            }
        }
        doc.close();
    }
}
