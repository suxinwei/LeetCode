package test;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import at.favre.lib.crypto.HKDF;

/**
 * @Description:
 * @Author: suxinwei
 * @CreateDate: 2020/9/8 14:37
 */
public class FileEncryptionUtil {

    private static final String ALGORITHM_NAME = "AES";
    private static final int BYTE_LENGTH = 8;

    public static void main(String[] args) {
//        testDecryptNormal();
        testDecryptPreviewImage();
//        testDecryptVideo();
    }

    private static void testDecryptNormal() {
        String path = "D:\\";
        File file = new File(path, "test.jpg");
        byte[] bytes = FileUtil.readByteArrayFromFile(file);
//        byte[] bytes = "12345678912345678".getBytes();
        String base64Key = "rE19KYTTDMcigwSyh7pnnQ==";
        String base64Nonce = "2NNNbkhpOSGwkrNB+wZ0xQ==";
        byte[] key = FileEncryptionUtil.decodeBase64(base64Key);
//        byte[] key = "1234567891011111".getBytes();
        byte[] nonce = FileEncryptionUtil.decodeBase64(base64Nonce);
//        byte[] nonce = "2121212121212121".getBytes();
        try {
            byte[] decrypt = FileEncryptionUtil.decryptOriginal(bytes, key, nonce, AESType.GCM);
            File outFile = new File(path, "decrypt_" + file.getName());
            FileUtil.writeByteArrayToFile(outFile, decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testDecryptPreviewImage() {
        String path = "D:\\";
        File file = new File(path, "test.jpg");
        byte[] bytes = FileUtil.readByteArrayFromFile(file);
        String base64Key = "r6+SprZcRU/Bd5dXh/W3MoNE9Es/H2tDM/y8qd7BR6Q=";
        byte[] key = FileEncryptionUtil.decodeBase64(base64Key);
//                byte[] key = {7, 2, 31, -26, 45, 82, -4, -58, 53, -93, 43, -51, 18, -110, -22, -80, -12, 96, 111, -30, -91, 77, -55, -92, -54, 0, 60, -28, 94, 77, 96, -29};
        Long time = 1603096842973L;
        try {
            byte[] decrypt = FileEncryptionUtil.decryptPreview(bytes, key, time, AESType.GCM);
            System.out.println("decrypt=" + Arrays.toString(decrypt));
            File outFile = new File(path, "decrypt_" + file.getName());
            FileUtil.writeByteArrayToFile(outFile, decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testDecryptVideo() {
        String path = "D:\\";
        File file = new File(path, "test.jpg");
        byte[] bytes = FileUtil.readByteArrayFromFile(file);
        String base64Key = "LSyJw5zLWWcqgp5TTogWChLCVBKGXy+H+1o2nF5QD0c=";
        byte[] key = FileEncryptionUtil.decodeBase64(base64Key);
//                byte[] key = {7, 2, 31, -26, 45, 82, -4, -58, 53, -93, 43, -51, 18, -110, -22, -80, -12, 96, 111, -30, -91, 77, -55, -92, -54, 0, 60, -28, 94, 77, 96, -29};
        Long time = 1599747534441L;
        try {
            byte[] decrypt = FileEncryptionUtil.decrypt(bytes, key, time, AESType.CTR);
            File outFile = new File(path, "decrypt_" + file.getName());
            FileUtil.writeByteArrayToFile(outFile, decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 原始加密
     *
     * @param src
     * @param key
     * @param nonce
     * @param aesType
     * @return
     */
    public static byte[] encryptOriginal(byte[] src, byte[] key, byte[] nonce, AESType aesType) {
        return aes(src, key, nonce, Cipher.ENCRYPT_MODE, aesType);
    }

    /**
     * 原始解密
     *
     * @param encrypted
     * @param key
     * @param nonce
     * @param aesType
     * @return
     */
    public static byte[] decryptOriginal(byte[] encrypted, byte[] key, byte[] nonce, AESType aesType) {
        return aes(encrypted, key, nonce, Cipher.DECRYPT_MODE, aesType);
    }


    public static byte[] decryptPreview(byte[] encrypted, byte[] videoFileSecret, Long beginTimeStamp, AESType aesType) {
//        byte[] videoFileSecret = getPreviewFileSecret(baseKey, beginTimeStamp, aesType);
        byte[] fileEncryptKey = getFileEncryptKey(videoFileSecret, beginTimeStamp, aesType);
        byte[] fileEncryptNonce = getFileEncryptNonce(videoFileSecret, beginTimeStamp, aesType);
        return aes(encrypted, fileEncryptKey, fileEncryptNonce, Cipher.DECRYPT_MODE, aesType);
    }

    /**
     * 字节数组转16进制
     *
     * @param bytes 需要转换的byte数组
     * @return 转换后的Hex字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] decrypt(byte[] encrypted, byte[] videoFileSecret, Long beginTimeStamp, AESType aesType) {
//        byte[] videoFileSecret = getVideoFileSecret(baseKey, beginTimeStamp, aesType);
        byte[] fileEncryptKey = getFileEncryptKey(videoFileSecret, beginTimeStamp, aesType);
        byte[] fileEncryptNonce = getFileEncryptNonce(videoFileSecret, beginTimeStamp, aesType);
        return aes(encrypted, fileEncryptKey, fileEncryptNonce, Cipher.DECRYPT_MODE, aesType);
    }

    /**
     * 测试试用，保存加密的key，打印iv
     *
     * @param fileName
     * @param aesType
     * @return
     */
    public static byte[] decrypt(String fileName, byte[] encrypted, byte[] videoFileSecret, Long beginTimeStamp, AESType aesType) {
//        byte[] videoFileSecret = getVideoFileSecret(baseKey, beginTimeStamp, aesType);
        byte[] fileEncryptKey = getFileEncryptKey(videoFileSecret, beginTimeStamp, aesType);
        byte[] fileEncryptNonce = getFileEncryptNonce(videoFileSecret, beginTimeStamp, aesType);
        return aes(fileName, encrypted, fileEncryptKey, fileEncryptNonce, Cipher.DECRYPT_MODE, aesType);
    }

    /**
     * 测试试用，保存加密的key，打印iv
     *
     * @param fileName
     * @param message
     * @param key
     * @param nonce
     * @param mode
     * @param aesType
     * @return
     */
    private static byte[] aes(String fileName, byte[] message, byte[] key, byte[] nonce, int mode, AESType aesType) {
        System.out.println("fileName= " + fileName + "  fileKey=" + bytesToHex(key) + "  nonce=" + bytesToHex(nonce));
        Key theKey = new SecretKeySpec(key, ALGORITHM_NAME);
        AlgorithmParameterSpec spec = null;
        if (AESType.CTR.equals(aesType)) {
            spec = new IvParameterSpec(nonce);
        } else if (AESType.GCM.equals(aesType)) {
            spec = new GCMParameterSpec(nonce.length * BYTE_LENGTH, nonce);
        } else {
            return null;
        }
        try {
            String transformationName = getTransformationName(aesType);
            Cipher cipher = Cipher.getInstance(transformationName);
            cipher.init(mode, theKey, spec);
            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] aes(byte[] message, byte[] key, byte[] nonce, int mode, AESType aesType) {
        Key theKey = new SecretKeySpec(key, ALGORITHM_NAME);
        AlgorithmParameterSpec spec = null;
        if (AESType.CTR.equals(aesType)) {
            spec = new IvParameterSpec(nonce);
        } else if (AESType.GCM.equals(aesType)) {
            spec = new GCMParameterSpec(nonce.length * BYTE_LENGTH, nonce);
        } else {
            return null;
        }
        try {
            String transformationName = getTransformationName(aesType);
            Cipher cipher = Cipher.getInstance(transformationName);
            cipher.init(mode, theKey, spec);
            return cipher.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //AES/CBC/PKCS5Padding
    //AES/CTR/NoPadding
    private static String getTransformationName(AESType aesType) {
        if (aesType.equals(AESType.CTR)) {
            return "AES/CTR/NoPadding";
        } else if (aesType.equals(AESType.GCM)) {
            return "AES/GCM/NoPadding";
        } else {
            return null;
        }
    }

    private static final String NONCE_INFO = "ht Nonce";

    public static byte[] getFileEncryptNonce(byte[] fileSecret, Long beginTimeStamp, AESType aesType) {
        int keyLength = getKeyLength(aesType);
        byte[] genFileEncryptNonce = hkdfExpand(fileSecret, NONCE_INFO.getBytes(StandardCharsets.UTF_8), keyLength);
        System.out.println("fileSecret=" + Arrays.toString(fileSecret));
        System.out.println("NONCE_INFO.getBytes(StandardCharsets.UTF_8)=" + Arrays.toString(NONCE_INFO.getBytes(StandardCharsets.UTF_8)));
        System.out.println("keyLength=" + keyLength);
        System.out.println("genFileEncryptNonce=" + Arrays.toString(genFileEncryptNonce));
        byte[] beginTimeStampBytes = ByteUtils.toByteArray(beginTimeStamp);
        System.out.println("beginTimeStampBytes=" + Arrays.toString(beginTimeStampBytes));
        byte[] pre = ByteUtils.addAll(new byte[4], beginTimeStampBytes);
        byte[] beginTimePaddingArray = ByteUtils.addAll(pre, new byte[4]);
        System.out.println("beginTimePaddingArray=" + Arrays.toString(beginTimePaddingArray));
        for (int i = 0; i < genFileEncryptNonce.length; i++) {
            genFileEncryptNonce[i] ^= beginTimePaddingArray[i];
        }
        System.out.println("genFileEncryptNonce=" + Arrays.toString(genFileEncryptNonce));
        return genFileEncryptNonce;
    }

    private static final String FILE_SECRET_INFO = "ht key";

    public static byte[] getFileEncryptKey(byte[] fileSecret, Long beginTimeStamp, AESType aesType) {
        int keyLength = getKeyLength(aesType);
        byte[] genFileEncryptKey = hkdfExpand(fileSecret, FILE_SECRET_INFO.getBytes(StandardCharsets.UTF_8), keyLength);
        System.out.println("fileSecret=" + Arrays.toString(fileSecret));
        System.out.println("FILE_SECRET_INFO.getBytes(StandardCharsets.UTF_8)=" + Arrays.toString(FILE_SECRET_INFO.getBytes(StandardCharsets.UTF_8)));
        System.out.println("keyLength=" + keyLength);
        System.out.println("genFileEncryptKey=" + Arrays.toString(genFileEncryptKey));
        return genFileEncryptKey;
    }

    private static final String VIDEO_INFO_PREFIX = "ht dev cs video ";
    private static final Long HOUR_TIME = 3600000L;

    public static byte[] getVideoFileSecret(byte[] baseKey, Long beginTimeStamp, AESType aesType) {
        Long beginTimeStampVideo = beginTimeStamp / HOUR_TIME;
        byte[] timeArray = ByteUtils.toByteArray(beginTimeStampVideo);
        byte[] videoFileSecretInfo = ByteUtils.addAll(VIDEO_INFO_PREFIX.getBytes(StandardCharsets.UTF_8), timeArray);
        System.out.println("videoFileSecretInfo:" + Arrays.toString(videoFileSecretInfo));
        int hashLength = getHashLength(aesType);
        byte[] genVideoFileSecret = hkdfExpand(baseKey, videoFileSecretInfo, hashLength);
        return genVideoFileSecret;
    }

    private static final String PREVIEW_INFO_PREFIX = "ht dev cs preview ";

    public static byte[] getPreviewFileSecret(byte[] baseKey, Long beginTimeStamp, AESType aesType) {
        byte[] timeArray = ByteUtils.toByteArray(beginTimeStamp);
        byte[] videoFileSecretInfo = ByteUtils.addAll(PREVIEW_INFO_PREFIX.getBytes(StandardCharsets.UTF_8), timeArray);
        int hashLength = getHashLength(aesType);
        byte[] genVideoFileSecret = hkdfExpand(baseKey, videoFileSecretInfo, hashLength);
        return genVideoFileSecret;
    }

    /**
     * @return
     */
    private static int getHashLength(AESType aesType) {
        return 32;
    }

    /**
     * 获取密钥长度
     *
     * @param aesType
     * @return
     */
    private static int getKeyLength(AESType aesType) {
        return 16;
    }

    /**
     * hkdf扩展密钥
     *
     * @param baseKey
     * @param info
     * @param keyLength
     * @return
     */
    private static byte[] hkdfExpand(byte[] baseKey, byte[] info, int keyLength) {
        return HKDF.fromHmacSha256().expand(baseKey, info, keyLength);
    }

    /**
     * hdkf加盐扩展密钥
     *
     * @param baseKey
     * @param salt
     * @param info
     * @param keyLength
     * @return
     */
    private static byte[] hkdf(byte[] baseKey, byte[] salt, byte[] info, int keyLength) {
        byte[] extract = HKDF.fromHmacSha256().extract(salt, baseKey);
        return HKDF.fromHmacSha256().expand(extract, info, keyLength);
    }

    public static String getRandomKey() {
        return UUID.randomUUID().toString().substring(0, 16);
    }

    public static String getRandomNonce() {
        return UUID.randomUUID().toString().substring(0, 16);
    }

    public static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
