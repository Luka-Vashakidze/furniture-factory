package util;

import annotations.Auditable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public final class ReflectionHelper {

    private ReflectionHelper() {}

    public static void printClassInfo(Class<?> clazz) {
        System.out.println("class: " + clazz.getName());
        System.out.println("Modifiers: " + Modifier.toString(clazz.getModifiers()));

        System.out.println("fields:");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName());
        }

        System.out.println("constructors:");
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            System.out.println("  " + Modifier.toString(c.getModifiers()) + " " + c.getName() + "(" +
                    Arrays.toString(c.getParameterTypes()) + ")");
        }

        System.out.println("Methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println("  " + Modifier.toString(m.getModifiers()) + " " +
                    m.getReturnType().getSimpleName() + " " + m.getName() + "(" +
                    Arrays.toString(m.getParameterTypes()) + ")");
            if (m.isAnnotationPresent(Auditable.class)) {
                System.out.println("    @Auditable: " + m.getAnnotation(Auditable.class).value());
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