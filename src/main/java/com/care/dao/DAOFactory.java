package com.care.dao;

import com.care.service.EntityManager;

import java.util.logging.Logger;

public class DAOFactory {

    private static Logger logger = Logger.getLogger("DAOFactory");

    public static <T extends DAO> T get(Class<T> clazz){
        return EntityManager.createObject(clazz);
    }
}
