package com.care.service;

import sun.rmi.runtime.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceFactory {
    private static Logger logger = Logger.getLogger("ServiceFactory");

    public static <T extends Service> T get( Class<T> clazz){

        T returnValue = null;
        try{
            logger.info("Method called successfully");
            Method method = clazz.getMethod("getInstance");
            returnValue = (T) method.invoke(null);
            System.err.println(returnValue);

        }catch(NoSuchMethodException e){
            logger.log(Level.SEVERE, "MethodNotFound", e.getCause());
        }catch (IllegalAccessException e){
            logger.log(Level.SEVERE, "IllegalAccess", e.getCause());
        }catch (InvocationTargetException e){
            logger.log(Level.SEVERE, "Exception "+e.getCause());
        }
        return returnValue;
    }

}
