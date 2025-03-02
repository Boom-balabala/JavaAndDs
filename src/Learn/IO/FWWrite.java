package Learn.IO;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FWWrite {
    // 注意：应该使用try-catch-finally处理异常。这里出于方便阅读代码，使用了throws的方式
    @Test
    public void test01() throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter(new File("src/Learn/IO/fw.txt"));
        // 写出数据
        fw.write(97); // 写出第1个字符
        fw.write('b'); // 写出第2个字符
        fw.write('C'); // 写出第3个字符
        fw.write(30000); // 写出第4个字符，中文编码表中30000对应一个汉字。

        // 关闭资源
        fw.close();
    }

    // 注意：应该使用try-catch-finally处理异常。这里出于方便阅读代码，使用了throws的方式
    @Test
    public void test02() throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter(new File("src/Learn/IO/fw.txt"));
        // 字符串转换为字节数组
        char[] chars = "尚硅谷".toCharArray();

        // 写出字符数组
        fw.write(chars); // 尚硅谷

        // 写出从索引1开始，2个字符。
        fw.write(chars, 1, 2); // 硅谷

        // 关闭资源
        fw.close();
    }

    // 注意：应该使用try-catch-finally处理异常。这里出于方便阅读代码，使用了throws的方式
    @Test
    public void test03() throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter("src/Learn/IO/fw.txt");
        // 字符串
        String msg = "尚硅谷";

        // 写出字符数组
        fw.write(msg); // 尚硅谷

        // 写出从索引1开始，2个字符。
        fw.write(msg, 1, 2); // 硅谷

        // 关闭资源
        fw.close();
    }

    @Test
    public void test04() {
        FileWriter fw = null;
        try {
            // 1. 创建File的对象
            File file = new File("src/Learn/IO/presoninfo.txt");
            // 2. 创建FileWriter的对象，将File对象作为参数传递到FileWriter的构造器中
            // 如果输出的文件已存在，则会对现有的文件进行覆盖
            fw = new FileWriter(file);
            //            fw = new FileWriter(file,false);
            // 如果输出的文件已存在，则会在现有的文件末尾写入数据
            //            fw = new FileWriter(file,true);

            // 3. 调用相关的方法，实现数据的写出操作
            // write(String str) / write(char[] cbuf)
            fw.write("I love you,");
            fw.flush();
            fw.write("you love him.");
            fw.write("so sad".toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. 关闭资源，避免内存泄漏
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
