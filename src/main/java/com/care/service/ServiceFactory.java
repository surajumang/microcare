package com.care.service;

import sun.rmi.runtime.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceFactory {
    static Logger logger = Logger.getLogger("ServiceFactory");

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
            //e.getCause();
            logger.log(Level.SEVERE, "Exception "+e.getCause());
        }
        return returnValue;
    }

}
