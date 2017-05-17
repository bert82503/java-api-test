package demo.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Pit of {@link Exception}.
 *
 * @author dannong
 * @since 2017年04月07日 11:27
 * @see java.lang.Throwable
 * @see java.lang.StackTraceElement
 * @see java.lang.Exception
 * @see java.lang.Error
 */
public class ExceptionDemo {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionDemo.class);

    public String handleException_6(String fileName) throws IOException {
        BufferedReader br = null;

        try { // 可能抛出异常的代码块
            br = new BufferedReader(new FileReader(fileName));
            return br.readLine();
        } catch (IOException e) { // 异常处理代码
            logger.error("file read failed, fileName:{}", fileName, e); // SLF4J
        } finally { // 清理代码（防止资源泄漏的关键工具）
            if (br != null) {
                br.close(); // java.io.Closeable#close
            }
        }
        return "";
    }

    public String handleException_7(String fileName) {
        // 可能抛出异常的代码块
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (FileNotFoundException e) { // 异常处理代码-1
            // 输出调用栈
            logger.error("file not found, fileName:{}", fileName, e);
        } catch (IOException e) { // 异常处理代码-2
            // 输出调用栈
            logger.error("file read failed, fileName:{}", fileName, e);
        }
        return "";
    }

    /**
     * 坑
     */
    public String pit(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) { // 异常处理代码
            // 输出调用栈（姿势不对）
            // 【现象】CPU占用高
            // System.out、System.err：JVM实例唯一 & System.setOut0()
            // System.err & Throwable.printStackTrace() & synchronized (System.err)
            e.printStackTrace();
            // synchronized (System.out)
            System.out.println(e); // Throwable.toString()

            // 【常见】调用栈丢失
            logger.error("file read failed, fileName:" + fileName + e);
        }
        return "";
    }

}
