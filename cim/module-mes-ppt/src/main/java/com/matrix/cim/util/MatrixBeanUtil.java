package com.matrix.cim.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;

import java.util.Map;

@Slf4j
public class MatrixBeanUtil {

    public static void copyProperty(Object dest, Object orig) {
        try {
            Map<String, String> oaryMap = BeanUtils.describe(dest);
            Map<String, String> bsheetMap = BeanUtils.describe(orig);

            for (Map.Entry<String, String> oaryEs : oaryMap.entrySet()) {
                String oaryKey = oaryEs.getKey();

                //直接根据名称查找
                String value = bsheetMap.get(oaryKey);
                if (StringUtils.isNoneBlank(value)) {
                    BeanUtils.setProperty(dest, oaryKey, value);
                    //如果根据名称找不到，根据驼峰式的名称查找
                } else {
                    String converKey = JdbcUtils.convertUnderscoreNameToPropertyName(oaryKey);
                    String converValue = bsheetMap.get(converKey);

                    if (StringUtils.isNoneBlank(converValue)) {
                        BeanUtils.setProperty(dest, oaryKey, converValue);
                    }
                }
            }
        }catch (Exception ex){
            log.error("转换错误:{}",ex.toString());
        }
    }
}
