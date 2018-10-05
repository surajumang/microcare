package com.care.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityManager {
    private static Logger logger = Logger.getLogger("EntityManager");

    /*
    Decide whether to throw the exception or to handle them. If an exception occurs then the program can't continue.
    As any error here is fatal.
     */
    private static final Map<Class<?>, Object> OBJECT_CACHE = new HashMap<Class<?>, Object>();

    /*
    EntityCreationException[todo]
     */
    private static <T> T create(Class<T> classType){
        T returnValue = null;
        try {
            returnValue = (T)classType.newInstance();
            OBJECT_CACHE.put(classType, returnValue);

        } catch (InstantiationException e) {
            logger.info(classType.getName());
            logger.log(Level.SEVERE, "Can't Instansiate", e);
        } catch (IllegalAccessException e) {
            logger.info(classType.getName());
            logger.log(Level.SEVERE, "Illegal Access", e);
        }
        return returnValue;
    }

    public static <T extends Entity> T get(Class<T> classType){
        T returnvalue = null;
        if (OBJECT_CACHE.containsKey(classType)){
            returnvalue = (T) OBJECT_CACHE.get(classType);
        }
        else {
            returnvalue = create(classType);
        }
        return returnvalue;
    }
}
