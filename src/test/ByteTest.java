package test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * created by 80288791 at 2020/4/9
 * description:
 */
class ByteTest {
    public static void main(String[] args) {
        String s1 = "2d2d31633630373133382d363934302d343865632d623865642d3466323231613634356263320d0a436f6e74656e742d446973706f736974696f6e3a20666f726d2d646174613b206e616d653d2266696c65223b2066696c656e616d653d22746573742e6c6f67220d0a436f6e74656e742d547970653a206d756c7469706172742f666f726d2d646174610d0a436f6e74656e742d4c656e6774683a2038390d0a0d0a32302d30332d33312031323a35353a35392d636f6d2e6e6561726d652e61746c61733a3130363130202d20322d442d416374696f6e41637469766974792d6170706c69636174696f6e20696e697461696c2066696e6973680a0d0a2d2d31633630373133382d363934302d343865632d623865642d3466323231613634356263322d2d0d0a";
        String s2 = "2d2d31633630373133382d363934302d343865632d623865642d3466323231613634356263320d0a436f6e74656e742d446973706f736974696f6e3a20666f726d2d646174613b206e616d653d2266696c65223b2066696c656e616d653d22746573742e6c6f67220d0a436f6e74656e742d547970653a206d756c7469706172742f666f726d2d646174610d0a436f6e74656e742d4c656e6774683a2038390d0a0d0a32302d30332d33312031323a35353a35392d636f6d2e6e6561726d652e61746c61733a3130363130202d20322d442d416374696f6e41637469766974792d6170706c69636174696f6e20696e697461696c2066696e6973680a0d0a2d2d31633630373133382d363934302d343865632d623865642d3466323231613634356263322d2d0d0a";

        StringBuilder sb = new StringBuilder();
        sb.append("aaa");
        sb.append("bbbb");
        sb.insert(sb.length() - 1, "c");
        System.out.println(sb);

        String s = "abcde1";
        String tag = "ab";
        int lastIndexOf = s.lastIndexOf(tag);
        System.out.println(s.substring(lastIndexOf + tag.length(), s.length() - 1));


        System.out.println((char) 1);
        String vendor = "" + (char) 0 + (char) 0 + (char) 0 + (char) 1;
        System.out.println(vendor);
        System.out.println(vendor.length());
        System.out.println(Arrays.toString(vendor.getBytes()));

        byte[] x = intToBytes2(49153);
        for (byte b : x) {
            System.out.println(b);
        }

        System.out.println("发送mp4:" + Arrays.toString("mp4".getBytes()));
        System.out.println("发送mp3:" + Arrays.toString("mp3".getBytes()));
        System.out.println("发送reliable:" + Arrays.toString("发送reliable".getBytes()));
        System.out.println("发送cmd:" + Arrays.toString("发送cmd".getBytes()));

    }

    /**
     * 转换4byte到int
     */
    public static int bytesToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    /**
     * 转换2byte到int
     */
    public static int bytesToInt2(byte[] b) {
        return b[0] & 0xFF | (b[1] & 0xFF) << 8;
//        return b[1] & 0xFF | (b[0] & 0xFF) << 8;
    }

    /**
     * 转换int到4byte
     */
    public static byte[] intToBytes(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    /**
     * 转换int到2byte
     */
    public static byte[] intToBytes2(int a) {
        return new byte[]{
                (byte) (a & 0xFF),
                (byte) ((a >> 8) & 0xFF)
        };
    }
}
