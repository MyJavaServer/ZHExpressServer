package com.zhihui.zhexpress.manager;


import com.zhihui.zhexpress.base.ZHLog;
import com.zhihui.zhexpress.config.ZHConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JerryYin on 10/13/17.
 * <p>
 * JAVA后端文件存储框架 ：
 * HDFS 【Hadoop分布书文件系统】
 * http://hadoop.apache.org/docs/r1.0.4/cn/hdfs_user_guide.html
 */
public class FileManager {


    //    图片类文件格式
    public static List<String> mImageSuffix = new ArrayList<>(Arrays.asList(".jpeg", ".JPEG", ".png", ".PNG", ".gif", ".GIF", ".JPG", ".jpg"));
    public static List<String> mViedoSuffix = new ArrayList<>(Arrays.asList(".avi", ".AVI", ".mp4", ".MP4", ".mkv", ".MKV", ".rmvb", ".RMVB", "ram", "RAM", "mpeg", "MPEG"));
    //    普通类型文档类文件
    public static List<String> mFileSuffix = new ArrayList<>(Arrays.asList(".docx", ".DOCX", ".doc", ".DOC", ".pdf", ".PDF", ".txt", ".TXT", ".xlsx", "XLSX"));


    /**
     * get file type
     *
     * @param file eg: "dsdfgf210515.jpg"...
     * @return type
     */
    public static String getFileType(MultipartFile file) {
        String type = "";
        String originFileName = file.getOriginalFilename();
        String suffix = originFileName.substring(originFileName.lastIndexOf("."));
        if (mImageSuffix.contains(suffix)) {
            type = ZHConfig.AS_FILE_TYPE_IMAGE;
        } else if (mFileSuffix.contains(suffix)) {
            type = ZHConfig.AS_FILE_TYPE_NORMAL_FILES;
        } else if (mViedoSuffix.contains(suffix)) {
            type = ZHConfig.AS_FILE_TYPE_NORMAL_VIEDO;
        }
        return type;
    }


    /**
     * save single file
     *
     * @param file
     * @param desDir
     * @param desName
     * @return 1 -- success   0 -- fail & exception     -1 -- file not support
     */
    public static int saveFile(MultipartFile file, String desDir, String desName) {
        try {
//            String type = getFileType(file);
//            if (!type.isEmpty()) {
//            save file
            File saveFile = new File(desDir + desName);
            if (!saveFile.getParentFile().exists()) {
//                    saveFile.mkdirs();
                if (!saveFile.getParentFile().mkdirs()) {
                    ZHLog.error("创建文件夹失败，请检查！");
                }
            }
            file.transferTo(saveFile);
            ZHLog.info(" saved file to : " + desDir + desName);
            return 1;
//            } else {
////                file type not support
//                return -1;
//            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 删除文件（不删除文件夹，只删除文件）
     *
     * @param fileDir  路径
     * @param fileName 文件名
     * @return 1 -- success  0 -- 文件删除失败  -1 -- 目标文件不存在  -2 -- 文件名或路径为空
     */
    public static int deleteFile(String fileDir, String fileName) {
        if (!fileDir.isEmpty() && !fileName.isEmpty()) {
            File file = new File(fileDir + fileName);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    ZHLog.info("delete file success: " + fileDir + fileName);
                    return 1;
                } else {
                    return 0;
                }
            }
            return -1;
        }
        return -2;
    }

    /**
     * save multi files
     *
     * @param files
     * @param filedir
     * @param fname
     * @return int[]
     * 1 -- success   0 -- fail & exception     -1 -- file not support
     */
    public static int[] saveMultiFiles(MultipartFile[] files, String filedir, String[] fname) {
        int[] results = new int[files.length];
        for (int i = 0; i < files.length; i++) {
            MultipartFile f = files[i];
            results[i] = saveFile(f, filedir, fname[i]);
        }
        return results;
    }


    /**
     * 文件读取
     *
     * @param file 文件路径+文件名
     * @return
     */
    public static String readFile(String file) {
        StringBuffer buffer = null;
        try {
            File file1 = new File(file);
            FileInputStream inputStream = new FileInputStream(file1);
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader reader = new BufferedReader(inputStreamReader);
            byte[] buf = new byte[1024];
            buffer = new StringBuffer();
            while (inputStream.read(buf) != -1) {
//                ZHLog.info(" "+reader.readLine());
//                buffer.append(reader.readLine());
//                if (reader.readLine().toString() == "END")
//                    break;
                String line = new String(buf);
                String s = new String(line.getBytes("utf-8"));
//                ZHLog.info(" "+s);
                buffer.append(s);
                buf = new byte[1024];
            }
//            reader.close();
//            inputStreamReader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ZHLog.error(" " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            ZHLog.error(" " + e.getMessage());
        }
        return buffer.toString();
    }


    public static String readFile(InputStream inputStream) {
//        InputStream inputStream = Enumeration.class.getResourceAsStream("version");
        byte[] buf = new byte[1024];
        StringBuffer buffer = new StringBuffer();
        try {
            while (inputStream.read(buf) != -1) {
                String line = new String(buf);
                String s = new String(line.getBytes("utf-8"));
                buffer.append(s);
                buf = new byte[1024];
            }
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            ZHLog.error(" " + e.getMessage());
        }
        return buffer.toString();
    }

    public static int deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return 1;               //成功删除
            } else {
                return 0;               //删除失败
            }
        } else {
            return -1;                  //无文件
        }
    }
}
