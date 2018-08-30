package com.care.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class DAOFactory {

    private static Logger logger = Logger.getLogger("DAOFactory");

    public static <T extends DAO> T get(Class<T> clazz){
        T dao = null;
        try{
            Method method = clazz.getMethod("getInstance");
            dao = (T)method.invoke(null);
            logger.info(dao.toString());
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
