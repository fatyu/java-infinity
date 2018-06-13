package cc.notalk.infinityjava.toy;

import com.google.common.collect.Maps;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//**c查找多个项目中出现Bean name 冲突的java类文件
public class RepeatJavaServiceFinder {


    public static void main(String args[]) {
        File file = new File("/Users/notalk/workspace/utry");


        Map<String, Set<String>> namePathMap = Maps.newHashMap();
        Collection<File> listFilesAndDirs = FileUtils.listFiles(file, new String[]{"java"}, true);
        for (File file2 : listFilesAndDirs) {
            try {
                String s = FileUtils.readFileToString(file2, Charsets.UTF_8);
                if (!(StringUtils.contains(s, "@Repository"))) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //if (StringUtils.contains(file2.getAbsolutePath(), "service")) {
            if (namePathMap.containsKey(file2.getName())) {
                Set<String> projectName = namePathMap.get(file2.getName());
                String project = StringUtils.substringBefore(StringUtils.substringAfter(file2.getAbsolutePath(), "/Users/notalk/workspace/utry/"), "/");
                projectName.add(project);
            } else {
                HashSet<String> projectNameSet = new HashSet<String>();
                String project = StringUtils.substringBefore(StringUtils.substringAfter(file2.getAbsolutePath(), "/Users/notalk/workspace/utry/"), "/");

                projectNameSet.add(project);
                namePathMap.put(file2.getName(), projectNameSet);
            }
            //}
        }
        Set<Map.Entry<String, Set<String>>> entries = namePathMap.entrySet();
        for (Map.Entry<String, Set<String>> item : entries) {
            String key = item.getKey();
            Set<String> value = item.getValue();
            if (value.size() > 1) {
                System.out.println("key:" + key + " " + "value:" + value);

            }
        }
    }

}