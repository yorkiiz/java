package com.matrix.cim.job;

import com.matrix.cim.dao.ams.MailAddressDao;
import com.matrix.cim.entity.ams.AlarmMessage;
import com.matrix.cim.entity.ams.EmailGroup;
import com.matrix.cim.service.*;
import com.matrix.cim.util.MailSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class MainProcessJob {

    @Autowired
    private CodeService codeService;

    @Autowired
    private EqptService eqptService;

    @Autowired
    private PrdctService prdctService;

    @Autowired
    private OperService operService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteiService routeiService;

    @Autowired
    private PrdctmtService prdctmtservice;

    @Autowired
    private MailAddressDao mailAddressDao;
    @Autowired
    private MailSendUtil mailSendUtil;

    public void printJob() {
        log.info("定时任务执行中......");



    }

    public void monitor() {
        StringBuffer sb = new StringBuffer();

        sb.append("任务名称：数据同步检查<br/>");
        sb.append("当前版本：1.0<br/>");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append("检查时间：").append(sdf.format(new Date())).append("<br/>");
        sb.append("详细信息：<br/>");
        AlarmMessage.isAbnormal = false;
        try {
            AlarmMessage.list = new ArrayList<>();
            codeService.compare();
            getMessageFromList("CODE", sb);

            AlarmMessage.list = new ArrayList<>();
            eqptService.compare();
            getMessageFromList("EQPT", sb);

            AlarmMessage.list = new ArrayList<>();
            prdctService.compare();
            getMessageFromList("PRDCT", sb);

            AlarmMessage.list = new ArrayList<>();
            operService.compare();
            getMessageFromList("OPER", sb);

            AlarmMessage.list = new ArrayList<>();
            prdctmtservice.compare();
            getMessageFromList("PRDCTMT", sb);

            AlarmMessage.list = new ArrayList<>();
            routeService.compare();
            getMessageFromList("ROUTE", sb);

            AlarmMessage.list = new ArrayList<>();
            routeiService.compare();
            getMessageFromList("ROUTEI", sb);


            List<EmailGroup> emailGroups = mailAddressDao.queryMailGroup();
            if (AlarmMessage.isAbnormal) {
                mailSendUtil.sendMail(sb.toString(), emailGroups);
            }
        } catch (Exception e) {
            log.error("数据同步检查任务执行异常，异常信息", e);
        }
    }

    /**
     * 如果有异常 isAbnormal 为true
     * @param title
     * @param sb
     */
    private void getMessageFromList(String title, StringBuffer sb) {
        if (AlarmMessage.list.size() != 0) {
            AlarmMessage.isAbnormal = true;
            sb.append("<font color=\"red\">&emsp;[").append(title).append("]比对异常详情：").append("</font><br/>");
            int i = 1;
            for (String s : AlarmMessage.list) {

                sb.append("&emsp;&emsp;").append(i).append(": ").append(s).append("<br/>");
                i++;
            }
        } else {
            sb.append("<font color=\"green\">&emsp;[").append(title).append("]检查正常").append("</font><br/>");
        }
    }
}
