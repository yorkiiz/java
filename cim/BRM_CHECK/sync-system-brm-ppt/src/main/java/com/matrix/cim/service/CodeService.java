package com.matrix.cim.service;

import com.matrix.cim.entity.mes.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
@Slf4j
public class CodeService extends AbstractCompareService<Code> {

    public boolean compare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return compateList(new Code());
    }

}
