package com.matrix.cim.service;

import com.alibaba.fastjson.JSON;
import com.matrix.cim.core.service.MatrixBaseService;
import com.matrix.cim.dao.BcodeDao;
import com.matrix.cim.entity.Bcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BCODE")
public class BcodeService extends MatrixBaseService {

    @Autowired
    private BcodeDao bcodeDao;

    @Override
    public String subMainProc(String strInTrx) {
        String sql = "select * from ppt.bcode where code_cate ='PRST' ";
        List<Bcode> bcodeList = bcodeDao.queryBySql(sql);
        return JSON.toJSONString(bcodeList);
    }
}
