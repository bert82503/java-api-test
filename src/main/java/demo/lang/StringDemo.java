package demo.lang;

/**
 * Demo of {@link String}.
 *
 * @author dannong
 * @since 2017年04月06日 17:22
 */
public class StringDemo {

    private String name;

    private int age;


    @Override
    public String toString() {
        return "StringDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String performanceIssue() {
        String result = "";
        for (int i = 0; i < 100; i++) {
            result += i; // String concatenation - StringBuilder.toString()
        }
        return result;
    }

    public String performanceNormal() {
        StringBuilder sb = new StringBuilder(200); // 200表示什么
        for (int i = 0; i < 100; i++) {
            sb.append(i);
        }
        return sb.toString();
    }

    /**
     * 坑
     */
    public void pit() {
        StringBuilder sb = new StringBuilder('a'); // new StringBuilder(97)

        (new String("pit")).intern();
    }

}
