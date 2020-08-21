package cn.nci.util;

import cn.nci.socket.FTPInit;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @program: centispacesg
 * @description: FTP客户端工具类，支持上传和下载
 * @author: xiejianfeng
 * @create: 2020-08-06 10:16
 */
public class FtpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);

    // 初始化参数
    private static String ftpHost = "";                // FTP服务的IP地址
    private static String ftpPort = "";                // FTP端口
    private static String username = "";               // 用户名
    private static String password = "";               // 密码
    public static String localWorkPath = null;

    private FTPClient ftpClient = null;         // FTP实例
    private String localCharset = "UTF-8";      // 本地编码格式
    private String serverCharset = "UTF-8";     // FTP服务器编码格式


    /**
     * 创建FTP实例
     *
     * @param ftpHost
     * @param ftpPort
     * @param username
     * @param password
     * @throws Exception
     */
    private FtpClientUtil(String ftpHost, String ftpPort, String username, String password) throws Exception {
        this.username = username;
        this.password = password;
        this.ftpHost = ftpHost;
        this.ftpPort = ftpPort;
        this.ftpClient = new FTPClient();
        ftpClient.setAutodetectUTF8(true);
        try {
            this.ftpClient.connect(ftpHost, Integer.parseInt(ftpPort));    // 连接FTP服务器
            this.ftpClient.setDataTimeout(18000);    // 设置超时时间
            this.ftpClient.login(username, password);    // 登陆FTP服务器
            int reply = this.ftpClient.getReplyCode();    // 返回代码
            // 根据返回代码判断是否连接成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftpClient.disconnect();
                throw new Exception("连接ftp服务器失败");
            }
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setBufferSize(4096);
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
            if (FTPReply.isPositiveCompletion(this.ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                this.localCharset = "UTF-8";
            }
            this.ftpClient.setControlEncoding(this.localCharset);    // 设置编码格式
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

        } catch (Exception e) {
            throw new Exception("连接ftp客户端失败");
        }
    }

    /**
     * 功能描述: 获取FTP实例
     *
     * @Param: [jsonPath, moduleName]
     * @Return: cn.nci.util.FtpClientUtil
     * @Author: john
     * @Date: 2020/8/6 15:55
     */
    public static FtpClientUtil getInstance(String jsonPath, String moduleName) throws Exception {
        Map<String, String> message = FTPInit.init(jsonPath, moduleName);
        Set<String> keySet = message.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            if ("ftpHost" == key) {
                ftpHost = message.get(key);
            } else if ("ftpPort" == key) {
                ftpPort = message.get(key);
            } else if ("username" == key) {
                username = message.get(key);
            } else if ("password" == key) {
                password = message.get(key);
            } else if ("localWorkPath" == key) {
                localWorkPath = message.get(key);
            }
        }
        return new FtpClientUtil(ftpHost, ftpPort, username, password);
    }

    /**
     * 功能描述: 关闭FTP连接
     *
     * @Param: []
     * @Return: void
     * @Author: john
     * @Date: 2020/8/6 14:09
     */
    public void close() throws IOException {
        if (this.ftpClient.isConnected()) {
            this.ftpClient.disconnect();
        }
    }

    /**
     * 功能描述: 删除文件
     *
     * @Param: [sourcePath:要删除的文件所在FTP服务器中路径, filename:要删除的文件名]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/6 14:02
     */
    public void deleteFile(String sourcePath, String filename) throws Exception {
        sourcePath = sourcePath.replace("\\", "/");    // 源文件路径
        try {
            boolean cd = this.ftpClient.changeWorkingDirectory(new String(sourcePath.getBytes("UTF-8"), this.serverCharset));
            if (!cd) {
                throw new Exception("切换到上传文件目录失败或者目录不存在！");
            }
            String[] files = this.ftpClient.listNames();
            this.ftpClient.deleteFile(new String(filename.getBytes(this.localCharset), this.serverCharset));
        } catch (Exception e) {
            throw new Exception("删除ftp文件失败，请检查参数配置和ftp服务器状态!");
        }
    }

    /**
     * 功能描述: 下载文件，返回一个File自行处理
     *
     * @Param: [sourcePath:要下载的文件所在FTP服务器中路径, filename:要下载的文件名]
     * @Return: java.io.File
     * @Author: john
     * @Date: 2020/8/6 10:36
     */
    public File downLoadFile(String sourcePath, String filename, String localPath) throws Exception {
        sourcePath = sourcePath.replace("\\", "/");    // 源文件路径
        OutputStream os = null;
        File localFile = null;
        try {
            // 跳转到对应的文件路径中
            boolean cd = this.ftpClient.changeWorkingDirectory(new String(sourcePath.getBytes(this.localCharset),
                    this.serverCharset));
            if (!cd) {
                throw new Exception("切换到下载文件目录失败或者目录不存在！");
            }
            File path = new File(localWorkPath + "/" + localPath + "/");
            if (!path.exists()) {
                path.mkdirs();
            }
            localFile = new File(localWorkPath + "/" + localPath + "/" + filename);
            os = new FileOutputStream(localFile);
            this.ftpClient.retrieveFile(new String(filename.getBytes(this.localCharset), this.serverCharset), os);
            return localFile;
        } catch (Exception e) {
            throw new Exception("下载ftp文件失败，请检查参数配置和ftp服务器状态!");
        } finally {
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
                throw new Exception("关闭输出流出错！");
            }
        }
    }

    /**
     * 功能描述: 下载文件
     *
     * @Param: [sourcePath:要下载的文件所在FTP服务器中路径, filename:要下载的文件名, localWorkPath:本地存放路径]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/6 14:18
     */
//    public void downLoadFile(String sourcePath, String filename, String localWorkPath) throws Exception {
//        File src = downLoadFile(sourcePath, filename);
//        File dest = new File(localWorkPath + filename);
//        FileInputStream fio = new FileInputStream(src);
//        BufferedInputStream bio = new BufferedInputStream(fio);
//        FileOutputStream fos = new FileOutputStream(dest);
//        BufferedOutputStream bos = new BufferedOutputStream(fos);
//        byte buffer[] = new byte[65535];
//        int length;
//        while ((length = bio.read(buffer)) != -1) {
//            bos.write(buffer, 0, length);
//        }
//        bos.flush();
//        bos.close();
//        fos.close();
//        bio.close();
//        fio.close();
//    }

    /**
     * 功能描述: 上传文件
     *
     * @Param: [uploadFilePath:要上传的文件路径, fileName:要上传的文件名, ftpWorkPath:要上传的FTP路径]
     * @Return: void
     * @Author: john
     * @Date: 2020/8/6 14:12
     */
    public void uploadFtpFile(String uploadFilePath, String fileName, String ftpWorkPath) throws RuntimeException {
        FileInputStream fis = null;
        try {
            File srcFile = new File(uploadFilePath + fileName);
            fis = new FileInputStream(srcFile);
            //设置上传目录
            ftpClient.changeWorkingDirectory("/" + ftpWorkPath);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");

            //设置为被动模式
            ftpClient.enterLocalPassiveMode();

            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1"), fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
            logger.info("已上传至FTP服务器路径！");
        }
    }
}
