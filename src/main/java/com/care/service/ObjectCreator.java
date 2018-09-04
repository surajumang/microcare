package com.care.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectCreator {
    private static Logger logger = Logger.getLogger("ObjectCreator");

    /*
    Decide whether to throw the exception or to handle them. If an exception occurs then the program can't continue.
    As any error here is fatal.
     */
    public static <T> T create(Class<T> type){
        T returnValue = null;
        try {
            returnValue = (T)type.newInstance();
        } catch (InstantiationException e) {
            logger.info(type.getName());
            logger.log(Level.SEVERE, "Can't Instansiate", e);
        } catch (IllegalAccessException e) {
            logger.info(type.getName());
            logger.log(Level.SEVERE, "Illegal Access", e);
        }
        return returnValue;
    }
}
