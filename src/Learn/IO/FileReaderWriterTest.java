package Learn.IO;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderWriterTest {
    @Test
    public void testFileReaderWriter() throws IOException {
        File file = new File("src/Learn/IO/hello.txt");
        // 这里如果是IOException,那么fr.close()就不会执行，所以最好Try-catch
        FileReader fr = new FileReader(file);
        int data;
        while ((data = fr.read()) != -1) {
            System.out.println((char) data);
        }
        fr.close();
    }

    @Test
    public void testFileReaderWriter2() {
        FileReader fr = null;
        try {
            File file = new File("src/Learn/IO/hello.txt");
            fr = new FileReader(file);
            int data;
            while ((data = fr.read()) != -1) {
                System.out.println((char) data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testFileReaderWriter3() throws IOException {
        FileReader fr = null;
        File file = new File("src/Learn/IO/hello.txt");
        fr = new FileReader(file);
        char[] cbuf = new char[5];
        int data;
        while ((data = fr.read(cbuf)) != -1) {
            System.out.println(cbuf);
        }
        fr.close();
    }
}
