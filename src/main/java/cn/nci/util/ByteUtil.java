package cn.nci.util;

/**
 * Byte字节转换工具，用于基本类型数据和byte数组之间的转换
 * 创建者：yy
 * 创建时间：2014/04/09
 */
public class ByteUtil {

    /**
     * short转换为byte[]，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param s
     * @param index 在数组b中存放short变量的起始位置
     * @return void
     */
    public static void writeShortM(byte b[], short s, int index) {
        b[index + 0] = (byte) (s >> 8);
        b[index + 1] = (byte) (s >> 0);
    }

    /**
     * short转换为byte[]，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param s
     * @param index 在数组b中存放short变量的起始位置
     * @return void
     */
    public static void writeShortL(byte b[], short s, int index) {
        b[index + 0] = (byte) (s >> 0);
        b[index + 1] = (byte) (s >> 8);
    }

    /**
     * byte[]转换为short，低地址存放最高有效字节（MSB）
     *
     * @param b     byte数组
     * @param index 从数组b中获取short变量的起始位置
     * @return short
     */
    public static short readShortM(byte[] b, int index) {
        return (short) ((((b[index + 0] & 0xff) << 8) | b[index + 1] & 0xff));
    }


    /**
     * byte[]转换为short，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取short变量的起始位置
     * @return short
     */
    public static short readShortL(byte[] b, int index) {
        return (short) (((b[index + 1] & 0xff) << 8) | b[index + 0] & 0xff);
    }

    /**
     * 从byte[]中读取unsignedshort的值并以int类型返回该值，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param index 从数组b中获取unsigned short值的起始位置
     * @return int
     */
    public static int readUnsignedShortM(byte[] b, int index) {
        return (int) ((((b[index + 0] & 0xff) << 8) | b[index + 1] & 0xff) & 0xfffff);
    }

    /**
     * 从byte[]中读取unsignedshort的值并以int类型返回该值，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取unsigned short值的起始位置
     * @return int
     */
    public static int readUnsignedShortL(byte[] b, int index) {
        return (int) ((((b[index + 1] & 0xff) << 8) | b[index + 0] & 0xff) & 0xfffff);
    }


    /**
     * int转换为byte[]，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param i
     * @param index 在数组b中存放int变量的起始位置
     * @return void
     */
    public static void writeIntM(byte[] b, int i, int index) {
        b[index + 0] = (byte) (i >> 24);
        b[index + 1] = (byte) (i >> 16);
        b[index + 2] = (byte) (i >> 8);
        b[index + 3] = (byte) (i >> 0);
    }

    /**
     * int转换为byte[]，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param i
     * @param index 在数组b中存放int变量的起始位置
     * @return void
     */
    public static void writeIntL(byte[] b, int i, int index) {
        b[index + 0] = (byte) (i >> 0);
        b[index + 1] = (byte) (i >> 8);
        b[index + 2] = (byte) (i >> 16);
        b[index + 3] = (byte) (i >> 24);
    }

    /**
     * byte[]转换为int，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param index 从数组b中获取int变量的起始位置
     * @return int
     */
    public static int readIntM(byte[] b, int index) {
        return (int) ((((b[index + 0] & 0xff) << 24)
                | ((b[index + 1] & 0xff) << 16)
                | ((b[index + 2] & 0xff) << 8)
                | ((b[index + 3] & 0xff) << 0)));
    }


    /**
     * byte[]转换为int，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取int变量的起始位置
     * @return int
     */
    public static int readIntL(byte[] b, int index) {
        return (int) ((((b[index + 0] & 0xff) << 0)
                | ((b[index + 1] & 0xff) << 8)
                | ((b[index + 2] & 0xff) << 16)
                | ((b[index + 3] & 0xff) << 24)));
    }

    /**
     * 2020年9月18日17:18:40 新增函数
     * 无符号整型
     * byte[]转换为int，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取int变量的起始位置
     * @return long
     */
    public static long readUnsignedIntL(byte[] b, int index) {
        return (long) ((((long)(b[index + 0] & 0xff) << 0)
                | (((long)b[index + 1] & 0xff) << 8)
                | (((long)b[index + 2] & 0xff) << 16)
                | (((long)b[index + 3] & 0xff) << 24)) & 0xffffffff);
    }

    /**
     * long转换为byte[]，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param l
     * @param index 在数组b中存放long变量的起始位置
     * @return void
     */
    public static void writeLongM(byte[] b, long l, int index) {
        b[index + 0] = (byte) (l >> 56);
        b[index + 1] = (byte) (l >> 48);
        b[index + 2] = (byte) (l >> 40);
        b[index + 3] = (byte) (l >> 32);
        b[index + 4] = (byte) (l >> 24);
        b[index + 5] = (byte) (l >> 16);
        b[index + 6] = (byte) (l >> 8);
        b[index + 7] = (byte) (l >> 0);
    }

    /**
     * long转换为byte[]，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param l
     * @param index 在数组b中存放long变量的起始位置
     * @return void
     */
    public static void writeLongL(byte[] b, long l, int index) {
        b[index + 0] = (byte) (l >> 0);
        b[index + 1] = (byte) (l >> 8);
        b[index + 2] = (byte) (l >> 16);
        b[index + 3] = (byte) (l >> 24);
        b[index + 4] = (byte) (l >> 32);
        b[index + 5] = (byte) (l >> 40);
        b[index + 6] = (byte) (l >> 48);
        b[index + 7] = (byte) (l >> 56);
    }

    /**
     * byte[]转换为long，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param index 从数组b中获取long变量的起始位置
     * @return long
     */
    public static long readLongM(byte[] b, int index) {
        return ((((long) b[index + 0] & 0xff) << 56)
                | (((long) b[index + 1] & 0xff) << 48)
                | (((long) b[index + 2] & 0xff) << 40)
                | (((long) b[index + 3] & 0xff) << 32)
                | (((long) b[index + 4] & 0xff) << 24)
                | (((long) b[index + 5] & 0xff) << 16)
                | (((long) b[index + 6] & 0xff) << 8)
                | (((long) b[index + 7] & 0xff) << 0));
    }

    /**
     * byte[]转换为long，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取long变量的起始位置
     * @return long
     */
    public static long readLongL(byte[] b, int index) {
        return ((((long) b[index + 0] & 0xff) << 0)
                | (((long) b[index + 1] & 0xff) << 8)
                | (((long) b[index + 2] & 0xff) << 16)
                | (((long) b[index + 3] & 0xff) << 24)
                | (((long) b[index + 4] & 0xff) << 32)
                | (((long) b[index + 5] & 0xff) << 40)
                | (((long) b[index + 6] & 0xff) << 48)
                | (((long) b[index + 7] & 0xff) << 56));
    }

    /**
     * float转换为byte[]，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param f
     * @param index 在数组b中存放float变量的起始位置
     * @return void
     */
    public static void writeFloatM(byte[] b, float f, int index) {
        int l = Float.floatToIntBits(f);
	/*	for (int i = 3; i >= 0; i--) {
			b[index + i] = new Integer(l).byteValue();
			l = l >> 8;
		}
	*/
        writeIntM(b, l, index);
    }

    /**
     * float转换为byte[]，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param f
     * @param index 在数组b中存放float变量的起始位置
     * @return void
     */
    public static void writeFloatL(byte[] b, float f, int index) {
        int l = Float.floatToIntBits(f);
	/*	for (int i = 0; i < 4; i++) {
			b[index + i] = new Integer(l).byteValue();
			l = l >> 8;
		}
	*/
        writeIntL(b, l, index);
    }


    /**
     * byte[]转换为float，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param index 从数组b中获取float变量的起始位置
     * @return float
     */
    public static float readFloatM(byte[] b, int index) {
        int i;
	/*	i = b[index + 3];
		i &= 0xff;
		i |= (b[index + 2] << 8);
		i &= 0xffff;
		i |= (b[index + 1] << 16);
		i &= 0xffffff;
		i |= (b[index + 0] << 24);
	*/
        i = readIntM(b, index);
        return Float.intBitsToFloat(i);
    }

    /**
     * byte[]转换为float，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取float变量的起始位置
     * @return float
     */
    public static float readFloatL(byte[] b, int index) {
        int i;
	/*	i = b[index + 0];
		i &= 0xff;
		i |= (b[index + 1] << 8);
		i &= 0xffff;
		i |= (b[index + 2] << 16);
		i &= 0xffffff;
		i |= (b[index + 3] << 24);
	*/
        i = readIntL(b, index);
        return Float.intBitsToFloat(i);
    }


    /**
     * double转换为byte[]，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param d
     * @param index 在数组b中存放double变量的起始位置
     * @return void
     */
    public static void writeDoubleM(byte[] b, double d, int index) {
        long l = Double.doubleToLongBits(d);
	/*	for (int i = 7; i >= 0; i--) {
			b[index + i] = new Long(l).byteValue();
			l = l >> 8;
		}
	*/
        writeLongM(b, l, index);
    }

    /**
     * double转换为byte[]，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param d
     * @param index 在数组b中存放double变量的起始位置
     * @return void
     */
    public static void writeDoubleL(byte[] b, double d, int index) {
        long l = Double.doubleToLongBits(d);
	/*	for (int i = 0; i < 8; i++) {
			b[index + i] = new Long(l).byteValue();
			l = l >> 8;
		}
	*/
        writeLongL(b, l, index);
    }

    /**
     * byte[]转换为double，低地址存放最高有效字节（MSB）
     *
     * @param b
     * @param index 从数组b中获取double变量的起始位置
     * @return double
     */
    public static double readDoubleM(byte[] b, int index) {
        long l;
	/*	l = b[index + 7];
		l &= 0xff;
		l |= ((long) b[index + 6] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 5] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 4] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[index + 3] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[index + 2] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[index + 1] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[index + 0] << 56);
	*/
        l = readLongM(b, index);
        return Double.longBitsToDouble(l);
    }

    /**
     * byte[]转换为double，低地址存放最低有效字节（LSB）
     *
     * @param b
     * @param index 从数组b中获取double变量的起始位置
     * @return double
     */
    public static double readDoubleL(byte[] b, int index) {
        long l;
	/*	l = b[index + 0];
		l &= 0xff;
		l |= ((long) b[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 3] << 24);
		l &= 0xffffffffl;
		l |= ((long) b[index + 4] << 32);
		l &= 0xffffffffffl;
		l |= ((long) b[index + 5] << 40);
		l &= 0xffffffffffffl;
		l |= ((long) b[index + 6] << 48);
		l &= 0xffffffffffffffl;
		l |= ((long) b[index + 7] << 56);
	*/
        l = readLongL(b, index);
        return Double.longBitsToDouble(l);
    }


    /**
     * byte[]转换为String（1个byte转换为1个字符）
     *
     * @param b
     * @param index  从数组b中获取String的起始位置
     * @param length 要获取的String的长度
     * @return String
     */
    public static String readString(byte[] b, int index, int length) {
	/*	char[] chs = new char[length];
		for (int i = 0; i < length; i++) {								
			chs[i] = (char)b[index + i];
		}
		return new String(chs).trim();	
	*/
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (b[index + i] & 0xff));
        }
        return sb.toString().trim();
    }

    /**
     * byte[]转换为String（1个byte转换为1个字符）
     *
     * @param b
     * @param index 从数组b中获取String的起始位置
     * @return String
     */
    public static void writeString(byte[] b, int index, String str) {
        byte[] buf = str.getBytes();
        System.arraycopy(buf, 0, b, index, buf.length);
    }


    /**
     * byte转换为16进制字符串
     *
     * @param buffer 要转换的byte数组(从下标为0的位置开始转换)
     * @param length buffer中要进行转换的长度
     * @return String
     */
    public static String byteToHexStr(byte[] buffer, int length) {
        if (buffer.length == 0 || buffer == null || length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            String temp = Integer.toHexString(buffer[i] & 0xff);
            if (temp.length() == 1) {
                temp = '0' + temp;
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * byte转换为16进制字符串
     *
     * @param buffer
     * @param start  buffer中要进行转换的起始位置为start
     * @param end    buffer中要进行转换的结束位置为end-1
     * @return String
     */
    public static String byteToHexStr(byte[] buffer, int start, int end) {
        if (buffer.length == 0 || buffer == null || start >= end) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = start; i < end; i++) {
            String temp = Integer.toHexString(buffer[i] & 0xff);
            if (temp.length() == 1) {
                temp = '0' + temp;
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 去掉字符串首部的0，直到字符串中第一个字符为非0字符或字符串中只有一个字符
     *
     * @param str
     * @return String
     */
    public static String removeZero(String str) {
        if (str.startsWith("0") && str.length() != 1)
            return removeZero(str.substring(1));
        else
            return str;
    }


}