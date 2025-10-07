package com.furniture.furniturefactory.util;

import com.furniture.furniturefactory.annotations.Auditable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ReflectionHelper {

    private ReflectionHelper() {}
    private static final Logger logger = LogManager.getLogger(ReflectionHelper.class);

    public static void printClassInfo(Class<?> clazz) {
        logger.info("class: {}", clazz.getName());
        logger.info("Modifiers: {}", Modifier.toString(clazz.getModifiers()));

        logger.info("fields:");
        for (Field f : clazz.getDeclaredFields()) {
            logger.info("  {} {} {}", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName());
        }

        logger.info("constructors:");
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            logger.info("  {} {}({})", Modifier.toString(c.getModifiers()), c.getName(), Arrays.toString(c.getParameterTypes()));
        }

        logger.info("Methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            logger.info("  {} {} {}({})", Modifier.toString(m.getModifiers()), m.getReturnType().getSimpleName(), m.getName(), Arrays.toString(m.getParameterTypes()));
            if (m.isAnnotationPresent(Auditable.class)) {
                logger.info("    @Auditable: {}", m.getAnnotation(Auditable.class).value());
            }
        }
    }

    public static Object createInstance(Class<?> clazz, Class<?>[] paramTypes, Object[] args) throws Exception {
        Constructor<?> ctor = clazz.getDeclaredConstructor(paramTypes);
        ctor.setAccessible(true);
        return ctor.newInstance(args);
    }

    public static Object callMethod(Object target, String methodName, Class<?>[] paramTypes, Object[] args) throws Exception {
        Method m = target.getClass().getDeclaredMethod(methodName, paramTypes);
        m.setAccessible(true);
        return m.invoke(target, args);
    }
}