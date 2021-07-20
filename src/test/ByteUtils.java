package test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @Description: 一句话描述(更新说明)
 * @Author: lihaiyuan
 * @CreateDate: 2020/7/22 11:27
 */
public class ByteUtils {
    /**
     * short[] 转 byte[]
     */
    public static byte[] toBytes(short[] src) {
        int count = src.length;
        byte[] dest = new byte[count << 1];
        for (int i = 0; i < count; i++) {
            dest[i * 2] = (byte) (src[i]);
            dest[i * 2 + 1] = (byte) (src[i] >> 8);
        }

        return dest;
    }


    /**
     * short[] 转 byte[]
     */
    public static byte[] toBytes(short src) {
        byte[] dest = new byte[2];
        dest[0] = (byte) (src);
        dest[1] = (byte) (src >> 8);

        return dest;
    }

    /**
     * int 转 byte[]
     */
    public static byte[] toBytes(int i) {
        byte[] b = new byte[4];
        b[0] = (byte) (i & 0xff);
        b[1] = (byte) ((i >> 8) & 0xff);
        b[2] = (byte) ((i >> 16) & 0xff);
        b[3] = (byte) ((i >> 24) & 0xff);
        return b;
    }


    /**
     * String 转 byte[]
     */
    public static byte[] toBytes(String str) {
        return str.getBytes();
    }

    /**
     * long类型转成byte数组
     */
    public static byte[] toBytes(long number) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, number);
        return buffer.array();
    }

    public static int toInt(byte[] src, int offset) {
        return ((src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24));
    }

    public static int toInt(byte[] src) {
        return toInt(src, 0);
    }

    /**
     * 字节数组到long的转换.
     */
    public static long toLong(byte[] b) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(b, 0, b.length);
        return buffer.getLong();
    }

    /**
     * byte[] 转 short[]
     * short： 2字节
     */
    public static short[] toShorts(byte[] src) {
        int count = src.length >> 1;
        short[] dest = new short[count];
        for (int i = 0; i < count; i++) {
            dest[i] = (short) ((src[i * 2] & 0xff) | ((src[2 * i + 1] & 0xff) << 8));
        }
        return dest;
    }

    public static byte[] merger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static String toString(byte[] b) {
        return Arrays.toString(b);
    }

    public static byte[] addAll(byte[] arr1, byte[] arr2) {
        byte[] newArr = new byte[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);
        return newArr;
    }

    public static byte[] toByteArray(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            System.out.println("value=" + value);
            long l = value & 0xffL;
            System.out.println("l=" + l);
            byte byteL = (byte) l;
            System.out.println("l1=" + byteL);
            result[i] = byteL;
            value >>= 8;
        }

        return result;
    }

    public static byte[] input2byte(InputStream inStream) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024 * 8];
            int n = 0;
            while ((n = inStream.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
