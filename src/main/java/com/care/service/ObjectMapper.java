package com.care.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectMapper {

    private static Logger logger = Logger.getLogger("ObjectMapper");
    public static void mapObject(Object src, Object dest, boolean strict ){

        //List<Str> srcMethods = new ArrayList<Method>();
        Map<String, Method> srcMethods = new HashMap<String, Method>();

        for (Method method : src.getClass().getMethods()){
            if (method.getName().startsWith("get")){
                String methodName = method.getName().substring(3);
                srcMethods.put(methodName, method);
            }
        }

        for (Method method : dest.getClass().getMethods()){
            if (method.getName().startsWith("set")){
                String methodName = method.getName().substring(3);
                if (srcMethods.containsKey(methodName)){
                    //dest.setXXX(src.getXXX())
                    Class[] arg = method.getParameterTypes();
                    
                    try{
                        Method srcGetterMethod = src.getClass().getMethod("get"+methodName);
                        Class returnType = srcGetterMethod.getReturnType();
                        method.invoke(dest, srcGetterMethod.invoke(src));
                    }catch (IllegalAccessException e){
                        logger.log(Level.SEVERE, "Can't Access", e.getCause());
                    }catch (InvocationTargetException e){
                        logger.log(Level.SEVERE, "Target E", e.getCause());
                    }catch(NoSuchMethodException e){
                        logger.log(Level.SEVERE, "NoMethod", e.getCause());
                    }

                }
            }
        }

    }
}
