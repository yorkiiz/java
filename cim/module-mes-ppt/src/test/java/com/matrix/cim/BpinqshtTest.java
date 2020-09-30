package com.matrix.cim;

import com.alibaba.fastjson.JSON;
import com.matrix.cim.controller.SendMessageController;
import com.matrix.cim.tx.bpinqsht.BPINQSHTi;
import com.matrix.cim.tx.bpinqsht.BPINQSHTi_a;
import com.matrix.cim.tx.bpinqsht.BPINQSHTo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Slf4j
public class BpinqshtTest {

    @Autowired
    private SendMessageController sendMessageController;
    @Test
    public void testQueryByshtIds() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        BPINQSHTi inTrx = new BPINQSHTi();
        inTrx.setTrx_id("BPINQSHT");
        inTrx.setType_id("I");
        List<BPINQSHTi_a> iarys = new ArrayList<>();

        BPINQSHTi_a iary1 = new BPINQSHTi_a();
        iary1.setSht_id("A183000205192");
        iarys.add(iary1);


        BPINQSHTi_a iary2 = new BPINQSHTi_a();
        iary2.setSht_id("A1490167142A5");
        iarys.add(iary2);

        inTrx.setIary(iarys);



        String strInTrx = JSON.toJSONString(inTrx);
        log.info("inTrx Json: {}", strInTrx);
        String strOutTrx = sendMessageController.sendMessage(strInTrx, null);
        log.info("outTrx Json: {}",strOutTrx);
        BPINQSHTo outTrx = JSON.parseObject(strOutTrx, BPINQSHTo.class);
        log.info("outTrx Object: {}", outTrx);

    }
}
