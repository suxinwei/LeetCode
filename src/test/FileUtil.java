package test;

import java.io.*;

/**
 * created by suxinwei at 2020/5/29
 * description: 文件工具类
 */
public class FileUtil {
    /**
     * 文件读取字节数组
     */
    public static byte[] readByteArrayFromFile(String fileName) {
        return readByteArrayFromFile(new File(fileName));
    }

    /**
     * 文件读取字节数组
     */
    public static byte[] readByteArrayFromFile(File file) {
        FileInputStream fip = null;

        try {
            if (file.exists() && file.isFile()) {
                fip = new FileInputStream(file);
                int length = fip.available();
                byte[] buffer = new byte[length];
                //将文件中的数据读到byte数组中
                fip.read(buffer);
                return buffer;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fip != null) {
                    fip.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void writeByteArrayToFile(File file, byte[] decrypted) {
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void renameFile(String path, String oldname, String newname) {
        delFile(path, newname);
        if (!oldname.equals(newname)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            File oldFile = new File(path, oldname);
            File newFile = new File(path, newname);
            oldFile.renameTo(newFile);
        }
    }

    public static void delFile(String path, String filename) {
        File file = new File(path, filename);
        if (file.exists() && file.isFile())
            file.delete();
    }

    public static boolean isHasFile(String path, String filename) {
        File file = new File(path, filename);
        return isHasFile(file);
    }

    public static boolean isHasFile(File file) {
        return file.exists() && file.isFile();
    }

    public static void delDir(String path) {
        File dir = new File(path);
        if (dir.exists()) {
            File[] tmp = dir.listFiles();
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i].isDirectory()) {
                    delDir(path + File.separator + tmp[i].getName());
                } else {
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }

    /**
     * 获取文件拓展名
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static boolean delFile(File f) {
        if (f != null && f.exists() && !f.isDirectory()) {
            return f.delete();
        }
        return false;
    }

    public static void delDir(File f) {
        if (f != null && f.exists() && f.isDirectory()) {
            for (File file : f.listFiles()) {
                if (file.isDirectory())
                    delDir(file);
                file.delete();
            }
            f.delete();
        }
    }

    public static void delFileOrDir(File f) {
        delFile(f);
        delDir(f);
    }

    public static void createFileIfNoExist(String path) {
        File file = new File(path);
        if (!isHasFile(file)) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createFileIfNoExist(String dir, String filename) {
        File file = new File(dir, filename);
        if (!isHasFile(file)) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
