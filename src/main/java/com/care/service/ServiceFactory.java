package com.care.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceFactory {

    public static <T extends Service> T get( Class<T> clazz){
        T returnValue = null;
        try{
            Method method = clazz.getMethod("getInstance");
            returnValue = (T) method.invoke(null);
        }catch(NoSuchMethodException e){
            e.getCause();
        }catch (IllegalAccessException e){
            e.getCause();
        }catch (InvocationTargetException e){
            e.getCause();
        }
        return returnValue;
    }

}
