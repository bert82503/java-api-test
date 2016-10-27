package jdk8.tutorial;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Annotations. (容器注解->可重复的注解)
 *
 * @author dannong
 * @since 2016年10月27日 11:36
 */
public class AnnotationsTest {
    @Test
    public void functionTest() {
        Hint hint = Person.class.getAnnotation(Hint.class);
        assertThat(hint).isNull();

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        assertThat(hints1).isNotNull();
        assertThat(hints1.value().length).isEqualTo(2);

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        assertThat(hints2.length).isEqualTo(2);
    }
}
