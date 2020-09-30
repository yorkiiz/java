package com.matrix.cim.service;

import com.alibaba.fastjson.JSON;
import com.matrix.cim.core.service.MatrixBaseService;
import com.matrix.cim.dao.BsheetDao;
import com.matrix.cim.dao.BshtpnlDao;
import com.matrix.cim.entity.Bsheet;
import com.matrix.cim.entity.Bshtpnl;
import com.matrix.cim.tx.bpishtem.BPISHTEMi;
import com.matrix.cim.tx.bpishtem.BPISHTEMi_a;
import com.matrix.cim.tx.bpishtem.BPISHTEMo;
import com.matrix.cim.tx.bpishtem.BPISHTEMo_a1;
import com.matrix.cim.util.MatrixBeanUtil;
import com.matrix.cim.util.SheetDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BPISHTEM")
public class BpishtemService extends MatrixBaseService {
    @Autowired
    private BshtpnlDao bshtpnlDao;

    @Autowired
    private SheetDaoHelper sheetDaoHelper;

    @Override
    public String subMainProc(String strInTrx) {
        BPISHTEMi inTrx = JSON.parseObject(strInTrx, BPISHTEMi.class);
        BPISHTEMo outTrx = new BPISHTEMo();

        List<String> shtIds = new ArrayList<>();
        List<BPISHTEMi_a> iaryList = inTrx.getIary();
        if (iaryList.isEmpty()) {
            outTrx.setRtn_code("0000001");
            outTrx.setRtn_mesg("玻璃ID不能为空");
            return JSON.toJSONString(outTrx);
        }

        for (BPISHTEMi_a iary : iaryList) {
            shtIds.add(iary.getSht_id());
        }

        String worderId = inTrx.getWorder_id();
        String grade = inTrx.getGrade();
        String eqptId = inTrx.getEqpt_id();
        List<Bsheet> bsheetList = sheetDaoHelper.queryBothCustomAndBSheetByShtIds(shtIds, eqptId, worderId, grade, "WAIT");

        List<Bshtpnl> bshtpnlList = bshtpnlDao.queryByShtIds(shtIds);
        Map<String, Bshtpnl> bshtpnlMap = new HashMap<>();
        for (Bshtpnl bshtpnl : bshtpnlList) {
            bshtpnlMap.put(bshtpnl.getPnlId().trim(), bshtpnl);
        }


        List<BPISHTEMo_a1> oaryList = new ArrayList<>();
        for (Bsheet bsheet : bsheetList) {
            BPISHTEMo_a1 oary = new BPISHTEMo_a1();
            MatrixBeanUtil.copyProperty(oary, bsheet);
            Bshtpnl bshtpnl = bshtpnlMap.get(bsheet.getShtId().trim());
            if (bshtpnl != null) {
                oary.setOld_grade(bshtpnl.getOlePnlGrade());
                oary.setOle_pnl_grade(bshtpnl.getOlePnlGrade());
            }
            oaryList.add(oary);
        }
        outTrx.setOary1(oaryList);
        outTrx.setSht_cnt(String.valueOf(oaryList.size()));
        outTrx.setRtn_code("0000000");
        outTrx.setRtn_mesg("success");
        return JSON.toJSONString(outTrx);
    }
}
