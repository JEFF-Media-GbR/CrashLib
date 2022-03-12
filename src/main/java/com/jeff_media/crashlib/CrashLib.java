package com.jeff_media.crashlib;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class CrashLib {

    @SneakyThrows
    public static void sneakyCrash() {
        throwyCrash();
    }

    public static void tryToCrash() {
        try {
            throwyCrash();
        } catch (NoSuchFieldException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new CouldNotCrashException();
        }
    }

    public static void throwyCrash() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Field f = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Method method = Class.forName("sun.misc.Unsafe").getMethod("putAddress",Long.TYPE, Long.TYPE);
        method.invoke(f.get(null),0,0);
    }

}
