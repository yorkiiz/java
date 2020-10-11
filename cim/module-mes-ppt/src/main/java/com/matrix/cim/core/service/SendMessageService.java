package com.matrix.cim.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.matrix.cim.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class SendMessageService {


    public String invokeMethod(String jsonInTrx) {
        JSONObject jsonObject = JSON.parseObject(jsonInTrx);
        String trxId = jsonObject.get("trx_id").toString();
        if (StringUtils.isEmpty(trxId)) {
            throw new RuntimeException("trxId 不能为空");
        }
        MatrixBaseService baseService = SpringContextUtil.getBean(trxId);
        return baseService.subMainProc(jsonInTrx);
    }
}
