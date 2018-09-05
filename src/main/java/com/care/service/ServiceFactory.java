package com.care.service;

import java.util.logging.Logger;

public class ServiceFactory {
    private static Logger logger = Logger.getLogger("ServiceFactory");

    public static <T extends Service> T get( Class<T> clazz){
        return EntityManager.createObject(clazz);
    }

}
