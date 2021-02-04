package cc.notalk.infinityjava.tools;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Recursive {


    static List<String> ignoreFilesAndDirs = Lists.newArrayList(new String[]{"image", "images", "img", "imgs", "lib", "README.md", "_coverpage.md", "_navbar.md", "_sidebar.md"});

    public static void main(String[] args) {
        //1.初始文件路径
        //2.递归调用recursive方法,打印当前路径信息
        File file = new File("/Users/zhangyu/workspace/labs/MDLOG/");
        String prefix = "";
        boolean ignoreCurrentFile = true;
        recursive(file, prefix, ignoreCurrentFile);
    }

    private static void recursive(File file, String prefix, boolean ignoreCurrentFile) {
        if (file.isHidden()) {
            return;
        }
        if (ignoreFilesAndDirs.contains(file.getName())) {
            return;
        }
        if (file.isDirectory()) {

            if (!ignoreCurrentFile) {
                System.out.println(prefix + " * " + file.getName());
            }
            File[] files = file.listFiles();
            Arrays.sort(files);
            if (null != files)
                for (File f : files) {
                    recursive(f, prefix + "    ", false);
                }
        } else {

            if (file.getName().startsWith(".") || !(file.getName().endsWith("md"))) {

            } else {
                if (file.getName().contains(" ")) {
                    System.err.println("mv '" + file.getAbsoluteFile() + "' " + StringUtils.replace(file.getAbsoluteFile().getAbsolutePath(), " ", "-"));
                }
                System.out.println(StringUtils.replace(prefix + " * " + "[" + file.getName() + "](" + file.getAbsolutePath() + ")", "/Users/zhangyu/workspace/labs/MDLOG/", ""));
            }
        }
    }
}