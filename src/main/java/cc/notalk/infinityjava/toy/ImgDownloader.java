package cc.notalk.infinityjava.toy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * 图片下载器
 * 迭代收集的url文件,逐行下载相应文件.
 *
 */
public class ImgDownloader {

    public static void main(String[] args) {
        int totalImgCount = 0;
        File dir = new File("/Users/notalk/Downloads/datasource");

        Collection<File> files = FileUtils.listFiles(dir, new String[]{"txt"}, true);
        for (File file : files) {
            List<String> strings = null;
            try {
                strings = FileUtils.readLines(file, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            totalImgCount += strings.size();
            System.out.println(totalImgCount);
            //            for (String urlString : strings) {
            //                System.out.println("---------------------------------ready to download file :" + urlString + "---------------------------------");
            //
            //                URL url = null;
            //                try {
            //                    url = new URL(urlString);
            //                } catch (MalformedURLException e) {
            //                    e.printStackTrace();
            //                    continue;
            //                }
            //                URLConnection uri = null;
            //                try {
            //                    uri = url.openConnection();
            //                } catch (IOException e) {
            //                    e.printStackTrace();
            //                    continue;
            //                }
            //                InputStream is = null;
            //                try {
            //                    is = uri.getInputStream();
            //                } catch (IOException e) {
            //                    e.printStackTrace();
            //                    continue;
            //                }
            //                OutputStream os = null;
            //                try {
            //                    os = new FileOutputStream(new File("/Users/notalk/Downloads/happyzone", UUID.randomUUID().toString()));
            //                } catch (FileNotFoundException e) {
            //                    e.printStackTrace();
            //                    continue;
            //                }
            //                try {
            //                    byte[] buf = new byte[1024];
            //                    int len = -1;
            //                    while ((len = is.read(buf)) != -1) {
            //                        os.write(buf, 0, len);
            //                    }
            //                } catch (Exception e) {
            //                    e.printStackTrace();
            //                    continue;
            //                }
            //                System.out.println("--------------------------------- downloaded file finished:" + urlString + "---------------------------------");
            //                try {
            //                    Thread.sleep(500);
            //                } catch (InterruptedException e) {
            //                    e.printStackTrace();
            //                    continue;
            //                }
            //            }
        }
    }
}