package com.care.validation;

import com.care.annotation.*;
import com.care.annotation.Number;
import com.care.form.BaseForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

/*
    It will iterate through the form's methods(getters) and call the corresponding validation
    depending upon the annotation.
 */
public class FormValidator {
    private static Logger logger = Logger.getLogger("FormValidator");

    private static final Map<Class<? extends Annotation>, AnnotationProcessor> ANNOTATION_PROCESSOR_MAP =
            new HashMap<Class<? extends Annotation>, AnnotationProcessor >();

    static {
        ANNOTATION_PROCESSOR_MAP.put(StringDate.class, new StringDateProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Email.class, new EmailProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Name.class, new NameProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Number.class, new NumberProcessor());
        ANNOTATION_PROCESSOR_MAP.put(NotNull.class, new NotNullProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Regex.class, new RegexProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Password.class, new PasswordProcessor());
        ANNOTATION_PROCESSOR_MAP.put(DecimalNumber.class, new DecimalNumberProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Size.class, new SizeProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Time.class, new TimeProcessor());
        ANNOTATION_PROCESSOR_MAP.put(PositiveNumber.class, new PositiveNumberProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Phone.class, new PhoneProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Range.class, new RangeProcessor());
        ANNOTATION_PROCESSOR_MAP.put(Zipcode.class, new ZipcodeProcessor());

    }

    public static void  validate(BaseForm form, ActionErrors errors )
            throws InvocationTargetException, IllegalAccessException {

        logger.info("*****************STaRTING VALIDATION *****************");
        logger.info(form.getClass().getName());
        /* If the class is annotated with a @Flow annotation then we need a Map of
        fields which we need to validate otherwise proceed as usual.
         */
        if (form.getClass().getAnnotation(Flow.class) != null){
            logger.info("Validating using FlowId");
            validate(FormProcessor.getApplicableMethods(form), form, errors);
        } else{
            logger.info("Usual Validation");
            validate(new HashSet<>(Arrays.asList(form.getClass().getMethods())),
              form, errors);
        }

//        for(Method method : form.getClass().getMethods()){
//            if(method.getName().startsWith("get")){
//                //logger.info(method.getName());
//
//                String fieldName = method.getName().substring(3);
//                fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
//
//                for (Annotation annotation : method.getDeclaredAnnotations()) {
//
//                    //logger.info(annotation.toString());
//                    Validator v = ANNOTATION_PROCESSOR_MAP.get(annotation.annotationType()).create(annotation);
//
//                    //logger.info(v.getClass().getSimpleName());
//
//                    String value = (String)method.invoke(form);
//                    logger.info("" + value);
//                    if (! v.isValid(value)){
//                        if (v.addErrorMessage()){
//                            errors.add(fieldName, new ActionMessage(v.getMessage(), fieldName));
//                        }
//                        logger.info("NOTOKAY" + fieldName);
//                        break;
//                    }
//                    //logger.info(method.getName() + "done");
//                }
//            }
//
//        }
        logger.info("***************VALIDATION DONE******************");

    }

    private static void validate (Set<Method> fields, BaseForm form, ActionErrors errors){
        fields
            .forEach(method -> testAndAddErrors(method, form, errors));
    }

    /*
    Since we are working with getters only, the methods will not take any arguents.
     */

    private static void testAndAddErrors(Method method, BaseForm form, ActionErrors errors){
        if (!method.getName().startsWith("get")){
            return;
        }

        String fieldName = method.getName().substring(3);
        String fieldName2 = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
        String tempValue = null;
        logger.info("Method getting validated " + fieldName);
        try {
            tempValue = (String)method.invoke(form);
        } catch (IllegalAccessException e) {
            logger.info("can't invoke " + method);
        } catch (InvocationTargetException e) {
            logger.info("can't invoke " + method + e);
        } catch (Exception e){
            logger.info("can't invoke " + method);
        }
        logger.info("Value to be Validated " + tempValue + " -----");
        String value = tempValue;
        Stream.of(method.getDeclaredAnnotations())
            .map(FormValidator::getValidator)
            .filter(validator -> !validator.isValid(value))
            .forEach(validator -> errors.add(fieldName2,
                        new ActionMessage(validator.getMessage(), fieldName2)));
    }

    // if no validator is available then procced to next annotation.
    // it is being achieved using EmptyValidator which always returns true.
    private static Validator getValidator(Annotation annotation){
        AnnotationProcessor annotationProcessor = ANNOTATION_PROCESSOR_MAP.get(annotation.annotationType());
        if (annotationProcessor == null){
            logger.info("Using an empty validator for " + annotation);
            return new Validator.EmptyValidator();
        }
        return annotationProcessor.create(annotation);
    }
}
