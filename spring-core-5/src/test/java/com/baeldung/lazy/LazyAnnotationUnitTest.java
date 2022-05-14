package com.baeldung.lazy;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LazyAnnotationUnitTest {

    @Test
    public void givenLazyAnnotation_whenConfigClass_thenLazyAll() {
        // Add @Lazy to AppConfig.class while testing
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        final Region region = ctx.getBean(Region.class);
        final Country country = ctx.getBean(Country.class);
        assertEquals(Country.class, country.getClass());
    }

    @Test
    public void givenLazyAnnotation_whenAutowire_thenLazyBean() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        Region region = ctx.getBean(Region.class);
        final City city = region.getCityInstance();
        assertNotEquals(City.class, city.getClass());
    }

    @Test
    public void givenLazyAnnotation_whenBeanConfig_thenLazyBean() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        final Region region = ctx.getBean(Region.class);
        assertEquals(Region.class, region.getClass());
    }
}
