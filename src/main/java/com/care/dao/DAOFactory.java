package com.care.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DAOFactory {
    public static <T extends DAO> T get(Class<T> clazz){
        T dao = null;
        try{
            Method method = clazz.getMethod("getInstance");
            dao = (T)method.invoke(null);
        }catch (NoSuchMethodException e){
            e.getCause();
        }catch (InvocationTargetException e){
            e.getCause();
        }catch (IllegalAccessException e){
            e.getCause();
        }
        return dao;
    }
}
