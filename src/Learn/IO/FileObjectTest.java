package Learn.IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileObjectTest {
    public static void main(String[] args) {
        // 文件路径名
        String pathname = "E:\\LearningGOGOGO\\JavaStart\\Java\\src\\Learn\\IO\\aaa.txt";
        File file1 = new File(pathname);

        // 文件路径名
        String pathname2 = "E:\\LearningGOGOGO\\JavaStart\\Java\\src\\Learn\\IO\\aaa\\bbb.txt";
        File file2 = new File(pathname2);

        // 通过父路径和子路径字符串
        String parent = "E:\\LearningGOGOGO\\JavaStart\\Java\\src\\Learn\\IO\\aaa";
        String child = "bbb.txt";
        File file3 = new File(parent, child);

        // 通过父级File对象和子路径字符串
        File parentDir = new File("E:\\LearningGOGOGO\\JavaStart\\Java\\src\\Learn\\IO\\aaa");
        String childFile = "bbb.txt";
        File file4 = new File(parentDir, childFile);
    }

    @Test
    public void test01() throws IOException {
        File f1 = new File("d:\\atguigu\\javase\\HelloIO.java"); // 绝对路径
        System.out.println("文件/目录的名称：" + f1.getName());
        System.out.println("文件/目录的构造路径名：" + f1.getPath());
        System.out.println("文件/目录的绝对路径名：" + f1.getAbsolutePath());
        System.out.println("文件/目录的父目录名：" + f1.getParent());
    }

    @Test
    public void test02() throws IOException {
        File f2 = new File("/HelloIO.java"); // 绝对路径，从根路径开始
        System.out.println("文件/目录的名称：" + f2.getName());
        System.out.println("文件/目录的构造路径名：" + f2.getPath());
        System.out.println("文件/目录的绝对路径名：" + f2.getAbsolutePath());
        System.out.println("文件/目录的父目录名：" + f2.getParent());
    }

    @Test
    public void test03() throws IOException {
        File f3 = new File("HelloIO.java"); // 相对路径
        System.out.println("user.dir =" + System.getProperty("user.dir"));
        System.out.println("文件/目录的名称：" + f3.getName());
        System.out.println("文件/目录的构造路径名：" + f3.getPath());
        System.out.println("文件/目录的绝对路径名：" + f3.getAbsolutePath());
        System.out.println("文件/目录的父目录名：" + f3.getParent());
    }

    @Test
    public void test04() throws IOException {
        File f5 = new File("HelloIO.java"); // 相对路径
        System.out.println("user.dir =" + System.getProperty("user.dir"));
        System.out.println("文件/目录的名称：" + f5.getName());
        System.out.println("文件/目录的构造路径名：" + f5.getPath());
        System.out.println("文件/目录的绝对路径名：" + f5.getAbsolutePath());
        System.out.println("文件/目录的父目录名：" + f5.getParent());
        f5.delete();
    }
}
