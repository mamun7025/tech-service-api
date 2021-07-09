package comn

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

import java.text.ParseException
import java.text.SimpleDateFormat

@Transactional
class UtilityService {

    def static getDateMonddyyyy(String date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df;
        if (date.contains('-'))
            df = new SimpleDateFormat("d-MMM-yyyy");
        else
            df = new SimpleDateFormat("d/M/yyyy");
        try {

            java.util.Date today = df.parse(date);
            return today;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String getUniqueFileName(final String originalFileName) {
        String ext = originalFileName.substring(originalFileName.lastIndexOf('.'))
        String prevFileTitle = UUID.randomUUID().toString() + ext.toLowerCase()
        return prevFileTitle
    }

    def static replaceFile(MultipartFile file, String filePath) {
        def message = ""
        try {
            def reportFile = new File(filePath)
            if (reportFile.exists()) {
                if (!reportFile.delete()) {
                    message = 'File cannot be deleted!'
                }
            }
            file.transferTo(reportFile)

        } catch (Exception ex) {
            message = ex.getMessage();
            println("1218 Replace File()~ pic~file missing: message: " + message + "  ~~ filePath " + filePath)
        }

        return message
    }

}
