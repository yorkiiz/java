package com.matrix.cim.entity.mes;

import com.matrix.cim.annotation.PKField;
import com.matrix.cim.annotation.TableField;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class BaseEntity {
    /**
     * 获取主键列，有@PKField 标记的属性 就是主键列
     *
     * @return
     */
    public List<Field> getPkFieldList() {
        List<Field> pkFiledList = new ArrayList<>();

        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PKField.class)) {
                pkFiledList.add(field);
            }

        }
        return pkFiledList;
    }


    /**
     * 比较主键值是否都相同
     *
     * @param o
     * @return 是否相同
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public boolean pkEquals(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        List<Field> pkFieldList1 = this.getPkFieldList();


        for (Field field : pkFieldList1) {
            String property1 = BeanUtils.getProperty(this, field.getName());
            String property2 = BeanUtils.getProperty(o, field.getName());

            if (!StringUtils.equals(property1, property2)) {
                return false;
            }
        }

        return true;
    }

    public String getBrmTableName(){
        boolean annotationPresent = this.getClass().isAnnotationPresent(TableField.class);
        if (annotationPresent){
            TableField annotation = this.getClass().getAnnotation(TableField.class);
            return annotation.brmTableName();
        }
        return null;
    }

    public String getPptTableName(){
        boolean annotationPresent = this.getClass().isAnnotationPresent(TableField.class);
        if (annotationPresent){
            TableField annotation = this.getClass().getAnnotation(TableField.class);
            return annotation.pptTableName();
        }
        return null;
    }

}
