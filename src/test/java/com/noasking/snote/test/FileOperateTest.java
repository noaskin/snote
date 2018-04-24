package framework.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOperateTest {

    private static final String BASE_PATH = "D:\\var\\snote";

    /**
     * 新增目录
     */
    @Test
    public void testAddDirectory() throws IOException {
        File file = new File(BASE_PATH + File.separator + "java");
        if (file.exists()) {
            FileUtils.deleteDirectory(file);
//            file.delete();
        }
        file.mkdir(); // 这里只能创建一级目录，如果想创建多级需要用mkdirs
        File readmeFile = new File(file.getPath() + File.separator + "README.md");
        readmeFile.createNewFile();
    }

    @Test
    public void testAddFile() throws IOException {

    }

    /**
     * 删除指定文件
     */
    @Test
    public void testDeleteFile() {
        File readmeFile = new File(BASE_PATH + File.separator + "java" + File.separator + "README.md");
        readmeFile.delete();
    }

    /**
     * 删除指定目录及下面所有文件
     *
     * @throws IOException
     */
    @Test
    public void testDeleteDirectory() throws IOException {
        File file = new File(BASE_PATH + File.separator + "java");
        if (file.exists()) {
            FileUtils.deleteDirectory(file);
//            file.delete();
        }
    }

    /**
     * 目录扫描
     */
    @Test
    public void testScanDirectory() {
        File file = new File(BASE_PATH);
        list(file);
    }

    private List<File> list(File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                File readmeFile = new File(f.getPath() + File.separator + "README.md");
                if (readmeFile.exists()) {
                    System.out.println(f.getPath());
                }
                list(f);
            }
        }
        return null;
    }

    @Test
    public void testRename() {
        File file = new File("D:\\var\\snote\\java - 副本 (3)\\aaaa");
        file.renameTo(new File(file.getPath().substring(0, file.getPath().lastIndexOf(File.separator)) + File.separator + "aaa22a"));
    }

    /**
     * 扫描文本内容，删除无引用的文件（非目录）
     * 兼容下面这两种格式
     * ![Alt text](/path/to/img.jpg)
     * <p>
     * ![Alt text](/path/to/img.jpg "Optional title") 兼容格式待定
     */
    @Test
    public void testScanNote() {
        File file = new File("D:\\var\\snote\\java - 副本 (3)\\aaaa");
        File readmeFile = new File(file.getPath() + File.separator + "README.md");
        Set<String> filenames = new HashSet<String>();
        for (File f : file.listFiles()) {
            if (!f.isDirectory()) {
                filenames.add(f.getPath().substring(0, f.getPath().lastIndexOf(File.separator)));
            }
        }
        Pattern pattern = Pattern.compile(("^!\\!\\[.*\\]\\(.*\\)$"));
    }

    @Test
    public void testPattern() throws IOException {
        File readmeFile = new File("D:\\var\\snote\\java - 副本 (3)\\aaa22a\\README.md");
        List<String> contents = FileUtils.readLines(readmeFile);
        Pattern pattern = Pattern.compile("^\\!\\[.*\\]\\(.*\\)$");
        Matcher matcher = null;
        for (String line : contents) {
            matcher = pattern.matcher(line);
            if (matcher.matches()) {
                Pattern pattern1 = Pattern.compile("(?<=\\()[^\\)]+");
                Matcher m = pattern1.matcher(line);
                while (m.find()) {
                    System.out.println("Found value: " + m.group());
                }
//                System.out.println(line);
//                System.out.println(Pattern.compile("\\(.*\\)").matcher(line).find());

            }
        }
        //
    }

    @Test
    public void testcate(){
        Pattern pattern1 = Pattern.compile("(?<=\\()[^\\)]+");
        String content = "你好,你的班车从 $(dep.name) 到 ${arr.name} 发生XX，${yyy}您是否需要退票";
        Matcher m = pattern1.matcher(content);
        System.out.println("Found value: " + m.group() );
        while (m.find()) {
            System.out.println("Found value: " + m.group() );
        }
    }

}
