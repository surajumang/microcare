package com.care.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

        srcGetterMethods = new HashMap<String, Method>();
        destSetterMethods = new HashMap<String, Method>();
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

    /*
        Maps a model class to a form or a DTO. from exact data types to String.
        It will use the static method String.valueOf to do the mapping.
     */
    private static void simpleMapping(Object src, Object dest) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        /*
        If there is a setter in dest and a corrosponding getter in src. Then do the mapping
         */
        for (String destSetterMethodName : destSetterMethods.keySet()){
            if (srcGetterMethods.containsKey(destSetterMethodName)){
                //dest.setXXX(String.valueOf( src.getXXX() ))
                Method setter = destSetterMethods.get(destSetterMethodName);
                Class[] arg = setter.getParameterTypes();
                /*
                Argument check is not necessary As the form classes must have Only Strings as members.
                Check if the argument type is primitive. use Class.isPrimitive()
                 */
                Method getter = srcGetterMethods.get(destSetterMethodName);
                Method valueOf = String.class.getMethod("valueOf", Object.class);
                /*
                If the return type of getter is String then String.valueOf is not required.
                 */
                Class returnType = getter.getReturnType();
                Object getterVal = getter.invoke(src);

                if (getterVal == null){
                    continue;
                }
                if (returnType == String.class){
                    setter.invoke(dest, getterVal);
                }
                else{
                    setter.invoke(dest, valueOf.invoke(null, getterVal));
                }
                logger.info(setter.getName() + " --- > " + getter.getName());

                //setter.invoke(dest, getterVal);
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
                Class returnType = getter.getReturnType();
                /*
                if the method takes a primitive as a parameter then it's corresponding wrapper will be used to
                get the valueOf static method.
                 */
                //logger.info(setter.getName() + "-------" + getter.getName() + "--------" + argTypes[0].getName());

                if(argTypes.length != 1){
                    //throw some exception.
                }
                /*
                [todo] Make changes so as not to allow fields with null values.
                 valueOf is a static method which takes String as a Parameter. Handle the case if no such exist.
                 ValueOf not required if the parameter type is String.
                  */

                /*
                If the argument type is already a String then no valueOf conversion required.
                 */
                // if the types match(i.e argument of setter is same as return type of getter) then no
                //  extra stuff required. Simple invocation will work.

                Object getterValue = getter.invoke(src);

                if (returnType == argTypes[0] && getterValue != null){
                    logger.info("String arg type of Model " + setter);
                    setter.invoke(dest,  getterValue);
                }else {

                    if (argTypes[0].isPrimitive()){
                        argTypes[0] = WRAPPER_TYPE.get(argTypes[0]);
                    }
                    //logger.info("Model populated using Static value of");
                    Method valueOf = argTypes[0].getMethod("valueOf", String.class);
                    //logger.info(valueOf + "--------" + getterValue);

                    if (getterValue != null && valueOf != null && getterValue instanceof String){
                        logger.info("Setter " + setter.getName() );
                        logger.info("Static method " + valueOf.getName());
                        logger.info("Getter " + getter.getName());
                        logger.info("Value of Getter "+getterValue);
                        setter.invoke(dest, valueOf.invoke(null, getterValue));
                    }

                }

            }
        }
    }
}
