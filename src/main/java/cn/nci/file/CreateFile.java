package cn.nci.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFile {
    private String filePath = null;

    public CreateFile() {
    }

    public CreateFile(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String generateFileName(String filePath, String suffix) {
        String year = getTime("yyyy");
        String month = getTime("MM");
        String dates = getTime("dd");
        String hour = getTime("HH");
        String minute = getTime("mm");
        String fileName = filePath + year + "年/" + month + "月/" + dates + "日/" + hour + "时/"
                + year + "-" + month + "-" + dates + " " + hour + "-" + minute + "." + suffix;
        return fileName;
    }

    /**
     * 格式化系统时间
     */
    private String getTime(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public String createFile(String filePath,String suffix) {
        String fileName = generateFileName(filePath,suffix);
        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file.getParentFile() + "/" + file.getName();
    }
}
