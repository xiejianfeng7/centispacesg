package cn.nci.util;

/**
 * @program: centispacesg
 * @description: 字节数组与字符串相互转换
 * @author: xiejianfeng
 * @create: 2020-07-23 15:12
 */
public class ByteStringUtil {
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStrToByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    public static String decToHex(Integer num, int width) {
        String upperCase = Integer.toHexString(num).toUpperCase();
        while (upperCase.length() < width)
            upperCase = "0" + upperCase;
        return upperCase;
    }
}
