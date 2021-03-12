package com.huan.common.aspect.check;

import cn.hutool.core.exceptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;


/**
 * 校验工具类
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@Slf4j
@Component
public class CheckParamUtils {

    private static final String PARAM_NOT_EXIST = "被校验参数不存在！";

    @Resource
    private
    Validator validator;

    public CheckParamUtils() {
        if (validator == null) {
            ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                    .configure()
                    // 快速失败模式
                    .failFast(false)
                    .buildValidatorFactory();
            validator = validatorFactory.getValidator();
        }
    }

    Validator getValidator() {
        return validator;
    }

    /**
     * 统一处理校验失败项
     *
     * @param constraintViolation 校验失败统一反馈对象
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    private void printConstraintLog(ConstraintViolation<?> constraintViolation) {
        Object invalidValue = constraintViolation.getInvalidValue();
        ConstraintDescriptor<?> constraintDescriptor = constraintViolation.getConstraintDescriptor();
        log.info("InvalidValue\t{},\nMessage\t{},\nparam\t{},\nrequire\t{},\nValidateBean\t{}\n", invalidValue == null ? null : invalidValue.toString(), constraintViolation.getMessage(), constraintViolation.getPropertyPath(), constraintDescriptor == null ? null : constraintDescriptor.getAttributes(), constraintViolation.getRootBeanClass());
        throw new ValidateException("原因：" + constraintViolation.getMessage() + "\t失败参数:" + constraintViolation.getPropertyPath());
    }

    /**
     * 校验整个分组
     *
     * @param checkObject 需要校验的对象
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorAll(Object checkObject) {
        Objects.requireNonNull(checkObject, PARAM_NOT_EXIST);
        validator.validate(checkObject, Default.class).iterator().forEachRemaining(this::printConstraintLog);
    }

    /**
     * 校验整个分组
     *
     * @param checkObject 需要校验的对象
     * @param groups      指定分组
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorAll(Object checkObject,
                             Class<?> groups) {
        Objects.requireNonNull(checkObject, PARAM_NOT_EXIST);
        validator.validate(checkObject, groups).iterator().forEachRemaining(this::printConstraintLog);
    }

    /**
     * 校验默认分组的单个属性
     *
     * @param checkObject       需要校验的对象
     * @param checkPropertyName 需要校验的属性
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorPram(Object checkObject,
                              String checkPropertyName) {
        Objects.requireNonNull(checkObject, PARAM_NOT_EXIST);
        validator.validateProperty(checkObject, checkPropertyName).iterator().forEachRemaining(this::printConstraintLog);
    }

    /**
     * 校验指定分组的单个属性
     *
     * @param checkObject       需要校验的对象
     * @param checkPropertyName 需要校验的属性
     * @param groups            指定分组
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorPram(Object checkObject, Class<?> groups,
                              String checkPropertyName) {
        Objects.requireNonNull(checkObject, PARAM_NOT_EXIST);
        validator.validateProperty(checkObject, checkPropertyName, groups).iterator().forEachRemaining(this::printConstraintLog);
    }

    /**
     * 校验默认分组的多个属性、
     *
     * @param checkObject       需要校验的对象
     * @param checkPropertyName 需要校验的属性
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorPram(Object checkObject,
                              String... checkPropertyName) {
        Objects.requireNonNull(checkObject, PARAM_NOT_EXIST);
        Arrays.stream(checkPropertyName).forEach(strings -> validator.validateProperty(checkObject, strings).iterator().forEachRemaining(this::printConstraintLog));
    }

    /**
     * 校验指定分组的多个属性
     *
     * @param checkObject       需要校验的对象
     * @param checkPropertyName 需要校验的属性
     * @param groups            指定分组
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorPram(Object checkObject, Class<?> groups,
                              String... checkPropertyName) {
        Objects.requireNonNull(checkObject, PARAM_NOT_EXIST);
        Arrays.stream(checkPropertyName).forEach(strings -> validator.validateProperty(checkObject, strings, groups).iterator().forEachRemaining(this::printConstraintLog));
    }

    /**
     * 校验指定属性是否与给定值匹配
     *
     * @param beanType     需要校验的类
     * @param propertyName 需要校验的属性
     * @param value        提供比对值
     * @param groups       指定分组
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorValue(Class<?> beanType,
                               String propertyName,
                               Object value,
                               Class<?> groups) {
        validator.validateValue(beanType, propertyName, value, groups).iterator().forEachRemaining(this::printConstraintLog);
    }

    /**
     * 校验指定属性是否与给定值匹配
     *
     * @param beanType     需要校验的类
     * @param propertyName 需要校验的属性
     * @param value        提供比对值
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorValue(Class<?> beanType,
                               String propertyName,
                               Object value) {
        validator.validateValue(beanType, propertyName, value, Default.class).iterator().forEachRemaining(this::printConstraintLog);
    }

    /**
     * 校验指定的多个属性是否与给定值匹配
     *
     * @param beanType 需要校验的类
     * @param compare  <校验的属性,比对值>的键值对集合
     * @param groups   指定分组
     *
     * @author zhuyongsheng
     * @date 2020/3/16
     * @since YQE-1.0.0
     */
    public void validatorValue(Class<?> beanType,
                               Map<String, Object> compare,
                               Class<?> groups) {
        compare.forEach((propertyName, value) -> validator.validateValue(beanType, propertyName, value, groups).iterator().forEachRemaining(this::printConstraintLog));
    }
}
