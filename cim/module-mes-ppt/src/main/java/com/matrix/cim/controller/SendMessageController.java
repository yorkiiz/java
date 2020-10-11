package com.matrix.cim.controller;

import com.matrix.cim.core.service.SendMessageService;
import com.matrix.cim.service.BcodeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@RestController
@Slf4j
public class SendMessageController {

    @Autowired
    private SendMessageService sendMessageService;

    @RequestMapping("/sendMessage.do")
    public String sendMessage(String inTrx, HttpServletRequest req) {
        String traceId = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.put("traceId", traceId);

        if (req != null) {
            String userIp = req.getRemoteHost();
            MDC.put("userIp", userIp);
            MDC.put("userIp", userIp);
        }

        log.info("inTrx:[{}]", inTrx);
        String outTrx = sendMessageService.invokeMethod(inTrx);
        log.info("outTrx:[{}]", outTrx);
        return outTrx;
    }
}
