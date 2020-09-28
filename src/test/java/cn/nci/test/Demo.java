package cn.nci.test;

import cn.nci.domain.QueryCondition;
import cn.nci.util.ByteStringUtil;

import java.io.File;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-28 18:20
 */
public class Demo {
    public static void main(String[] args) {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setDataType(0x00530002);
        String filePath = "D:\\FTP" + File.separator + ByteStringUtil.decToHex(queryCondition.getDataType(), 8) + File.separator;
        File file = new File(filePath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.exists() && file.isDirectory()){
                    File[] files1 = file1.listFiles();
                    for (File file2 : files1) {
                        System.out.println(file2);
                    }
                }
            }
            System.out.println();
        }
    }
}
