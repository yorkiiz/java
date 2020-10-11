package com.matrix.cim.service;

import com.matrix.cim.entity.mes.Prdctmt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
@Slf4j
public class PrdctmtService extends AbstractCompareService<Prdctmt>  {

    public boolean compare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return compateList(new Prdctmt());
    }

}
