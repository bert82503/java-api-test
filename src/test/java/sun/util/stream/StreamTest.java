package sun.util.stream;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import jdk8.tutorial.UserDO;

/**
 * Streams. (流：表示元素序列)
 *
 * @author dannong
 * @since 2016年10月25日 19:17
 */
public class StreamTest {

    // 1. create a sample source in form of a list of strings
    private static final Collection<String> collection =
            Collections.unmodifiableCollection(
                    Arrays.asList("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1"));

    @Test(description = "过滤器：接受一个谓词函数来过滤流中的所有元素")
    public void filter() {
        // Filter accepts a predicate to filter all elements of the stream
//        Collection<String> result = Lists.newArrayList();
//        collection
//                .stream()
//                .filter(s -> s.startsWith("a"))
//                .forEach(result::add);
        Collection<String> result = collection
                .stream()
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList("aaa2", "aaa1"));
    }

    @Test(description = "排序：一个中间运算，它返回流的一个有序视图")
    public void sorted() {
        // Sorted is an intermediate operation which returns a sorted view of the stream
        Collection<String> result = collection
                .stream()
                .sorted()
                .filter(s -> s.startsWith("a"))
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList("aaa1", "aaa2"));
    }

    @Test(description = "映射：通过给定函数将每个元素转换为另一个对象")
    public void map() {
        // Map converts each element into another object via the given function
        Collection<String> result = collection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
        assertThat(result).isEqualTo(Arrays.asList(
                "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "BBB1", "AAA2", "AAA1"
        ));
    }

    @Test(description = "映射：通过给定函数将每个元素转换为另一个对象")
    public void mapToObject() {
        Collection<UserDO> userCollection = Lists.newArrayList(
                new UserDO("Edward Lee", 30, "你好，%s！"),
                new UserDO("Bert Lee", 31, "%s，欢迎回家！")
        );
        List<String> introList = userCollection.stream()
                .map((user) -> user.setIntroduction(String.format(user.getIntroduction(), user.getName())))
                .map(UserDO::getIntroduction)
                .collect(Collectors.toList());
        assertThat(introList).isEqualTo(Arrays.asList("你好，Edward Lee！", "Bert Lee，欢迎回家！"));
    }

    @Test(description = "匹配：用来检查是否某些谓词匹配流")
    public void match() {
        // Matching operations can be used to check whether a certain predicate matches the stream
        boolean result = collection
                .stream()
                .anyMatch(s -> s.startsWith("a"));
        assertThat(result).isTrue();

        result = collection
                .stream()
                .allMatch(s -> s.startsWith("a"));
        assertThat(result).isFalse();

        result = collection
                .stream()
                .noneMatch(s -> s.startsWith("z"));
        assertThat(result).isTrue();
    }

    @Test(description = "计数：返回流中元素的数量")
    public void count() {
        long count = collection
                .stream()
                .filter(s -> s.startsWith("b"))
                .count();
        assertThat(count).isEqualTo(3);
    }

    @Test(description = "归约：使用给定函数在流中的元素上执行一次归约")
    public void reduce() {
        Optional<String> reduce = collection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);
        assertThat(reduce.get()).isEqualTo("aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2");
    }

}
