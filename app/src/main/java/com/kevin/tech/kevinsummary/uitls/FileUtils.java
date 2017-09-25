package com.kevin.tech.kevinsummary.uitls;

import android.os.Environment;
import android.util.Log;

import com.kevin.tech.kevinsummary.constants.Constants;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/12.
 * <p>
 * <p>
 * <br/>
 * </p >
 */


public class FileUtils {
    private static String TAG = "FileUtils.class";
    /**
     * directory
     */
    public static final String directory = Environment.getExternalStorageDirectory() +
            File.separator + "KS" + File.separator;
    private OutputStream out;

    /**
     * get info about whether sd is mounted.
     *
     * @return
     */
    public static boolean isSDMounted() {
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * create file directory.
     *
     * @param name
     * @return
     */
    public static File createFileDirectory(String name) {
        File destDir = new File(directory + name);
        boolean mkdirs = destDir.mkdirs();
        LogK.i(TAG, "===" + mkdirs);
        return destDir;
    }

    /**
     * get info about whether file is exit.
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(directory + fileName);
        file.isFile();
        return file.exists();
    }

    public static String readFileFromSdcard(String fileName) throws IOException {
        String res = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(directory + fileName);

            int length = fis.available();

            byte[] buffer = new byte[length];
            fis.read(buffer);

            res = EncodingUtils.getString(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {

                fis.close();
            }
        }
        return res;


//        FileInputStream in = null;
//        try {
//            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
//            // 一次读多个字节
//            byte[] tempbytes = new byte[100];
//            int byteread = 0;
//            in = new FileInputStream(fileName);
////            ReadFromFile.showAvailableBytes(in);
//            // 读入多个字节到字节数组中，byteread为一次读入的字节数
//            while ((byteread = in.read(tempbytes)) != -1) {
//                System.out.write(tempbytes, 0, byteread);
//            }
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        } finally {
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e1) {
//                }
//            }
    }

    /**
     * save file & data to SDCard.
     *
     * @param fileName
     * @param data
     */
    public static void saveFile2Sdcard(String fileName, String data) {
        FileOutputStream fos = null;
        try {
            File f = new File(directory + fileName, "hello.txt");
//            if (f.exists()) {
//                f.delete();
//            }
            f.createNewFile();
            fos = new FileOutputStream(f);
            byte[] bytes = data.getBytes();

            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * delete all the files.
     */
    public static void deleteDir() throws Exception {
        File dir = new File(directory);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete();
            if (!file.delete()) {
                throw new Exception("The file delete failed!");
            } else {
                if (file.isDirectory())
                    deleteDir();
            }
        }
        dir.delete();
    }


    public static boolean createFile(String fileName, String filecontent) {
        Boolean bool = false;
//       String filenameTemp = directory+fileName+".txt";//文件路径+名称+文件类型
//        createFileDirectory(fileName);
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filenameTemp = directory + fileName + ".png";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is " + filenameTemp);
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, Charset.forName(Constants.ENCODING));
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static boolean delFile(String fileName) {
        Boolean bool = false;
        String filenameTemp = directory + fileName + ".xml";
        File file = new File(filenameTemp);
        try {
            if (file.exists()) {
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
        }
        return bool;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        OutputStream out = null;
        InputStream is = null;

        try {
            File dir = new File(filePath);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            out = new FileOutputStream(filePath + File.separator + fileName);
            is = new ByteArrayInputStream(bfile);
            byte[] buff = new byte[1024];
            int len;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void waitForWirtenCompleted(File file) {
        if (!file.exists())
            return;
        long old_length;
        do {
            old_length = file.length();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("waitForWriteCompleted", old_length + " " + file.length());

        } while (old_length != file.length());
    }

    public static boolean isCompletelyWritten(File file) {
        RandomAccessFile stream = null;
        try {
            stream = new RandomAccessFile(file, "rw");
            return true;
        } catch (Exception e) {
            Log.i("h", "Skipping file " + file.getName() + " for this iteration due it's not completely written");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e("e", "Exception during closing file " + file.getName());
                }
            }
        }
        return false;
    }

    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
}
