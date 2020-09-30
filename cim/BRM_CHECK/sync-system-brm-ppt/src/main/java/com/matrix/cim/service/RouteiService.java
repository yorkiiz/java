package com.matrix.cim.service;

import com.matrix.cim.entity.mes.Routei;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Slf4j
public class RouteiService extends AbstractCompareService<Routei> {


    public boolean compare() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return compateList(new Routei());
    }

}
