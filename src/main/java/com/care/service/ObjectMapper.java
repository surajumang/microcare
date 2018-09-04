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
    private static Map<String, Method> srcGetterMethods;
    private static Map<String, Method> destSetterMethods;

    public static void mapObject(Object src, Object dest, boolean strict ){

        srcGetterMethods = new HashMap<>();
        destSetterMethods = new HashMap<>();
        /*
        Fetch getters from the src
         */
        for (Method method : src.getClass().getMethods()){
            if (method.getName().startsWith("create")){
                String methodName = method.getName().substring(3);
                srcGetterMethods.put(methodName, method);
            }
        }
        for (Method method : dest.getClass().getMethods()){
            if (method.getName().startsWith("set")){
                String methodName = method.getName().substring(3);
                destSetterMethods.put(methodName, method);
            }
        }
        logger.info("mapping started");

        try {
            if (strict){
                strictMapping(src, dest);
            }else {
                simpleMapping(src, dest);
            }
        } catch (InvocationTargetException e) {
            //logger.log(Level.SEVERE, );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private static void simpleMapping(Object src, Object dest) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        /*
        If there is a setter in dest and a corrosponding getter in src. Then do the mapping
         */
        for (String destSetterMethodName : destSetterMethods.keySet()){
            if (srcGetterMethods.containsKey(destSetterMethodName)){
                //dest.setXXX(src.getXXX())
                Method setter = destSetterMethods.get("set" + destSetterMethodName);
                Class[] arg = setter.getParameterTypes();
                Method getter = srcGetterMethods.get("get" + destSetterMethodName);

                Class returnType = getter.getReturnType();
                logger.info(setter.getName() + " --- > " + getter.getName());
                setter.invoke(dest, getter.invoke(src));
            }
        }
    }

    private static void strictMapping(Object src, Object dest) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException{
        /*
        Go ahead only if you have a getter in the src and a corresponding setter in the dest.
        Invoking involves a static method.
         */
        for (String srcGetterMethodName : srcGetterMethods.keySet()) {
            if (destSetterMethods.containsKey(srcGetterMethodName)){
                Method setter = destSetterMethods.get("set" + srcGetterMethodName);
                Method getter = srcGetterMethods.get("get" + srcGetterMethodName);
                // The method should have exactly one parameter.
                Class[] argTypes = setter.getParameterTypes();
                if(argTypes.length != 1){
                    //throw some exception.
                }
                // valueOf is a method which takes String as a Parameter. Handle the case if no such exist.
                Method valueOf = argTypes[0].getMethod("valueOf", String.class);
                logger.info(" --------- " + getter + " --- " + setter + " ----- " + valueOf + "--------");
                setter.invoke(dest, valueOf.invoke(null, getter.invoke(src)));
            }
        }
    }
}
