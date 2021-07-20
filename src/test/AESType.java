package test;

/**
 * @Description: 文件密钥协商方式
 * @Author: suxinwei
 * @CreateDate: 2020/9/8 14:37
 */
public enum AESType {

    CTR("A1C"),
    GCM("A1G");

    public String code;

    AESType(String code) {
        this.code = code;
    }

    public static AESType getType(String code) {
        if (code == null) {
            return null;
        }
        AESType[] types = AESType.values();
        for (AESType type : types) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
