package cn.nci.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class LogtoFile {
    private String logPath = null;

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public LogtoFile() {
    }

    public LogtoFile(String logPath) {
        this.logPath = logPath;
    }

    // 增加标志位，是否加"#"号
    public void dataToFile(String logPath, byte[] content, int contentLength, boolean flag) {
        try {
            File writeName = new File(logPath);
            writeName.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(writeName, true);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                if (!bufferedOutputStream.equals(0)) {
                    bufferedOutputStream.write(content, 0, contentLength);
                    if (flag) {
                        byte[] bytes = {'#', '#', '#', '#', '#', '#', '#', '#'};
//                        bufferedOutputStream.write(content, 0, contentLength);
                        bufferedOutputStream.write(bytes);
                    }
                }
                bufferedOutputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void logtoFile(String logPath, byte[] content, int contentLength) {
//        try {
//            File writeName = new File(logPath);
//            writeName.createNewFile();
//            try (FileOutputStream fileOutputStream = new FileOutputStream(writeName, true);
//                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
//                if (!bufferedOutputStream.equals(0)) {
//                    bufferedOutputStream.write(content, 0, contentLength);
//                    bufferedOutputStream.write('\n');
//                }
//                bufferedOutputStream.flush();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
