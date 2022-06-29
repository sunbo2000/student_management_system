package org.snbo.ssms.utils;

import java.io.*;

/**
 * @author sunbo
 * @date 2022-06-06-13:51
 */
public class ObjStreamUtils {
    public static Boolean toSerialization(Object obj, String filename) {
        if (filename == null || "".equals(filename)){
            return false;
        }
        File file = new File("D:" + File.separator + File.separator + "SSMS" + File.separator + filename);
        //如果文件不存在就创建一份
        if (!file.exists()) {
            try {
                boolean flag = file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(null, null, fileOutputStream, objectOutputStream);
        }
    }

    public static Object toDeserialize(String filename) {
        if (filename == null || "".equals(filename)) {
            return null;
        }
        File file = new File("D:" + File.separator + File.separator + "SSMS" + File.separator + filename);
        if (!file.exists()) {
            return null;
        }
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(inputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(inputStream, objectInputStream, null, null);
        }
    }

    private static void closeResource(InputStream inputStream1, InputStream inputStream2, OutputStream outputStream1, OutputStream outputStream2) {
        try {
            if (inputStream1 != null) {
                inputStream1.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (inputStream2 != null) {
                inputStream2.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (outputStream1 != null) {
                outputStream1.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (outputStream2 != null) {
                outputStream2.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
