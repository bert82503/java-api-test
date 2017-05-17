package demo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo of {@link java.util.Collection}.
 *
 * @author dannong
 * @since 2017年04月07日 19:15
 */
public class CollectionDemo {

    // 遍历集合
    public void forEach() {
        List<String> strList = new ArrayList<>(3);

        // for-each
        for (String str : strList) {
            str.isEmpty();
        }
        strList.forEach(String::isEmpty);
        strList.stream().forEach(String::isEmpty);
    }

    // 批量操作
    public void addAll() {

    }

    // 数组操作
    public void toArray() {
        List<String> list = new ArrayList<>(3);
        String[] array = list.toArray(new String[list.size()]);
    }

}
