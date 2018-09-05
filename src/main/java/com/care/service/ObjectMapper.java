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

    private static final Map<Class<?>, Class<?>> WRAPPER_TYPE = new HashMap<>();

    static {
        WRAPPER_TYPE.put(int.class, Integer.class);
        WRAPPER_TYPE.put(double.class, Double.class);
        WRAPPER_TYPE.put(long.class, Long.class);
    }

    public static void mapObject(Object src, Object dest, boolean strict ){

        srcGetterMethods = new HashMap<>();
        destSetterMethods = new HashMap<>();
        /*
        Fetch getters from the src
         */
        for (Method method : src.getClass().getMethods()){
            if (method.getName().startsWith("get")){
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
            logger.log(Level.SEVERE, "INvok", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Illegal Access", e);
        } catch (NoSuchMethodException e) {
            logger.log(Level.SEVERE, "NoSuchMethod", e);
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
                Method setter = destSetterMethods.get(destSetterMethodName);
                Class[] arg = setter.getParameterTypes();
                /*
                Check if the argument type is primitive. use Class.isPrimitive()
                 */
                Method getter = srcGetterMethods.get(destSetterMethodName);

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
                Method setter = destSetterMethods.get(srcGetterMethodName);
                Method getter = srcGetterMethods.get(srcGetterMethodName);
                // The method should have exactly one parameter.
                Class[] argTypes = setter.getParameterTypes();
                /*
                if the method takes a primitive as a parameter then it's corresponding wrapper will be used to
                get the valueOf static method.
                 */
                logger.info(setter.getName() + "-------" + getter.getName() + "--------" + argTypes[0].getName());
                if (argTypes[0].isPrimitive()){
                    argTypes[0] = WRAPPER_TYPE.get(argTypes[0]);
                }
                if(argTypes.length != 1){
                    //throw some exception.
                }
                /*
                 valueOf is a static method which takes String as a Parameter. Handle the case if no such exist.
                 ValueOf not required if the parameter type is String.
                  */

                /*
                If the argument type is already a String then no valueOf conversion required.
                 */
                if (argTypes[0] == String.class){
                    setter.invoke(dest,  getter.invoke(src));
                }else {
                    Method valueOf = argTypes[0].getMethod("valueOf", String.class);
                    setter.invoke(dest, valueOf.invoke(null, getter.invoke(src)));
                }

            }
        }
    }
}
