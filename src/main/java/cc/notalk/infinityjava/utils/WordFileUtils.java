package cc.notalk.infinityjava.utils;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.lowagie.text.*;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.rtf.RtfWriter2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTAltChunk;
import org.docx4j.wml.P;
import org.docx4j.wml.STBrType;

import java.io.*;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * word文档处理工具类
 * 提供功能:
 *  - html代码片段转成docx文件
 *  - 多个docx文件合并为一个docx文件
 */
public class WordFileUtils {


    /**
     * 根据html代码片段和指定文件,生成docx(推荐docx文件格式)
     *
     * @param pageSize doc文档尺寸大小
     * @param htmlSnippet html代码片段
     * @param targetFileDir 建议根据一次批量合并任务创建一个临时文件夹(以系统文件分割符结束 File.pathSeparator))
     * @param fileName 文件名称,带文件类型后缀 如 km-2019-09-07.docx
     * @param a4
     * @param  imgUrlPrefix 图片是相对路径地址,用于拼接完整url地址的前缀 例如: http://10.0.10.139:8080/utry_ckms/
     * @throws IOException
     * @throws DocumentException
     */
    public static void convertHtml2WordDoc(String htmlSnippet, String targetFileDir, String fileName, Rectangle pageSize, String imgUrlPrefix)
            throws IOException, DocumentException {
        OutputStream out = new FileOutputStream(targetFileDir + fileName);//创建生成文件输出流
        Document document = new Document(pageSize);//设置文档页面尺寸为A4尺寸
        RtfWriter2.getInstance(document, out);
        document.open();
        Paragraph context = new Paragraph();
        /**
         * 处理html代码片段中的img标签,如果非http为前缀,拼接指定ImgUrlPrefix
         */
        if (StringUtils.isNotBlank(imgUrlPrefix)) {
            Set<String> srcSet = getImgStr(htmlSnippet);
            for (String src : srcSet) {
                if (StringUtils.startsWith(src, "http")) {
                } else {
                    String newSrc = imgUrlPrefix + src;
                    htmlSnippet = htmlSnippet.replace(src, newSrc);
                }
            }
        }
        List htmlList = HTMLWorker.parseToList(new StringReader(htmlSnippet), new StyleSheet());
        for (int i = 0; i < htmlList.size(); i++) {
            Element e = (Element) htmlList.get(i);
            context.add(e);
        }
        document.add(context);
        document.close();
    }

    /**
     * 使用正则匹配文本中img标签,并抽取src文本信息
     * @param htmlStr
     * @return
     */
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<String>();
        String img = "";
        Pattern pImage;
        Matcher mImage;
        String regExImg = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        pImage = Pattern.compile(regExImg, Pattern.CASE_INSENSITIVE);
        mImage = pImage.matcher(htmlStr);
        while (mImage.find()) {
            // 得到<img />数据
            img = mImage.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    /**
     * 获取文件文本
     * @param filePath
     * @return
     */
    private static String fileToSnippet(String filePath) {
        try {
            String data = FileUtils.readFileToString(new File(filePath), Charsets.UTF_8);
            return data;
        } catch (IOException e) {
            //TODO 添加错误日志,代替堆栈信息打印
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 合并指定word文件列表
     */
    public static void merge(List<String> list, String fileName) throws Exception {
        List<InputStream> inList = new ArrayList<InputStream>();
        for (int i = 0; i < list.size(); i++) {
            inList.add(new FileInputStream(list.get(i)));
        }
        InputStream inputStream = mergeDocx(inList);
        saveDoc(inputStream, fileName);
    }

    public static InputStream mergeDocx(final List<InputStream> streams) throws Docx4JException, IOException {
        WordprocessingMLPackage target = null;
        // 临时文件
        final File generated = File.createTempFile("generated", ".docx");
        int chunkId = 0;
        Iterator<InputStream> it = streams.iterator();
        while (it.hasNext()) {
            InputStream is = it.next();
            if (is != null) {
                if (target == null) {//第一个文档
                    OutputStream os = new FileOutputStream(generated);
                    os.write(IOUtils.toByteArray(is));
                    os.close();
                    target = WordprocessingMLPackage.load(generated);
                } else {
                    addPageBreak(target);
                    insertDocx(target.getMainDocumentPart(), IOUtils.toByteArray(is), chunkId++);
                }
            }
        }

        if (target != null) {
            target.save(generated);
            return new FileInputStream(generated);
        } else {
            return null;
        }
    }

    /**
     * 插入文档
     * @param main
     * @param bytes
     * @param chunkId
     */
    public static void insertDocx(MainDocumentPart main, byte[] bytes, int chunkId) {
        try {
            AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/part" + chunkId + ".docx"));
            afiPart.setBinaryData(bytes);
            Relationship altChunkRel = main.addTargetPart(afiPart);
            CTAltChunk chunk = Context.getWmlObjectFactory().createCTAltChunk();
            chunk.setId(altChunkRel.getId());
            main.addObject(chunk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 向文档添加一个换行符
     */
    private static void addPageBreak(WordprocessingMLPackage wordMLPackage) {
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        Br breakObj = new Br();
        breakObj.setType(STBrType.PAGE);
        P paragraph = Context.getWmlObjectFactory().createP();
        paragraph.getContent().add(breakObj);
        documentPart.getJaxbElement().getBody().getContent().add(paragraph);
    }

    /**
     * 输出文件
     * @param fis
     * @param toDocPath
     */
    public static void saveDoc(InputStream fis, String toDocPath) {
        FileOutputStream fos;
        int byteread = 0;
        try {
            fos = new FileOutputStream(toDocPath);
            byte[] buffer = new byte[1444];
            while ((byteread = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试转换
    //    public static void main(String[] args) throws IOException, DocumentException {
    //        convertHtml2WordDoc(fileToSnippet("/tmp/a.html"), "/tmp/", "aa.doc", PageSize.A4, "http://www.baidu.com");
    //    }
    //测试合并
    public static void main(String[] args) {
        List<String> datas = Lists.newArrayList();
        datas.add("/tmp/1.docx");
        datas.add("/tmp/2.docx");
        datas.add("/tmp/3.docx");
        try {
            merge(datas, "/tmp/merge.docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
