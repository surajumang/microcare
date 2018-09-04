package com.care.dao;

import com.care.service.ObjectCreator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class DAOFactory {

    private static Logger logger = Logger.getLogger("DAOFactory");

    public static <T extends DAO> T get(Class<T> clazz){
        return ObjectCreator.create(clazz);
    }
}
