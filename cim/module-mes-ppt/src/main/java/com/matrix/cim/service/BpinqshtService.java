package com.matrix.cim.service;

import com.alibaba.fastjson.JSON;
import com.matrix.cim.core.service.MatrixBaseService;
import com.matrix.cim.dao.BsheetDao;
import com.matrix.cim.entity.Bsheet;
import com.matrix.cim.tx.bpinqsht.BPINQSHTi;
import com.matrix.cim.tx.bpinqsht.BPINQSHTi_a;
import com.matrix.cim.tx.bpinqsht.BPINQSHTo;
import com.matrix.cim.tx.bpinqsht.BPINQSHTo_a;
import com.matrix.cim.util.MatrixBeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("BPINQSHT")
public class BpinqshtService extends MatrixBaseService {

    public static final String ID_CANNOT_EMPTY = "玻璃ID不能为空";
    @Autowired
    private BsheetDao bsheetDao;

    @Override
    public String subMainProc(String strInTrx) {
        BPINQSHTi inTrx = JSON.parseObject(strInTrx, BPINQSHTi.class);
        BPINQSHTo outTrx = new BPINQSHTo();
        List<BPINQSHTo_a> oaryList = new ArrayList<>();

        List<BPINQSHTi_a> iarys = inTrx.getIary();
        List<String> shtIds = new ArrayList<>();




        if (inTrx.getIary().isEmpty()) {
            outTrx.setRtn_code("0000001");
            outTrx.setRtn_mesg(ID_CANNOT_EMPTY);
            outTrx.setOary(oaryList);
            return JSON.toJSONString(outTrx);
        }

        List<Bsheet> bsheets = new ArrayList<>();

        if (StringUtils.equalsIgnoreCase(inTrx.getC_pnl_flg(), "C")) {
            for (BPINQSHTi_a iary : iarys) {
                shtIds.add(iary.getC_sht_id());
                bsheets = bsheetDao.queryByCustomShtIds(shtIds);
            }
        } else if (StringUtils.equalsIgnoreCase(inTrx.getC_pnl_flg(), "R")) {
            for (BPINQSHTi_a iary : iarys) {
                shtIds.add(iary.getSht_id());
            }
            bsheets = bsheetDao.queryByCustomShtIds(shtIds);
        } else {
            for (BPINQSHTi_a iary : iarys) {
                shtIds.add(iary.getSht_id());
            }
            bsheets = bsheetDao.queryByShtIds(shtIds);
        }



        for (Bsheet bsheet : bsheets) {
            BPINQSHTo_a oary = new BPINQSHTo_a();
            MatrixBeanUtil.copyProperty(oary, bsheet);

            if (StringUtils.equals("INPR", bsheet.getShtStat()) ||
                    StringUtils.equals("COMP", bsheet.getShtStat())
            ) {
                oary.setRoute_id(bsheet.getCrRouteId());
                oary.setRoute_ver(bsheet.getCrRouteVer());
                oary.setOpe_no(bsheet.getCrOpeNo());
                oary.setOpe_id(bsheet.getCrOpeId());
                oary.setOpe_ver(bsheet.getCrOpeVer());
                oary.setProc_id(bsheet.getCrProcId());
                oary.setPep_lvl(bsheet.getCrPepLvl());
                oary.setRecipe_id(bsheet.getCrRecipeId());
                oary.setMproc_id(bsheet.getCrMprocId());
                oary.setMproc_flg(bsheet.getCrMprocFlg());
                oary.setEqpt_id(bsheet.getEqptId());
                oary.setEqpt_port_id(bsheet.getEqptPortId());
            } else {
                oary.setRoute_id(bsheet.getNxRouteId());
                oary.setRoute_ver(bsheet.getNxRouteVer());
                oary.setOpe_no(bsheet.getNxOpeNo());
                oary.setOpe_id(bsheet.getNxOpeId());
                oary.setOpe_ver(bsheet.getNxOpeVer());
                oary.setProc_id(bsheet.getNxProcId());
                oary.setPep_lvl(bsheet.getNxPepLvl());
                oary.setRecipe_id(bsheet.getNxRecipeId());
                oary.setMproc_id(bsheet.getNxMprocId());
                oary.setMproc_flg(bsheet.getNxMprocFlg());
                oary.setEqpt_id(StringUtils.isAllBlank(bsheet.getNxEqptId()) ? bsheet.getNxEqptgId() : bsheet.getNxEqptId());
                oary.setEqpt_port_id(" ");
            }

            oaryList.add(oary);
        }
        outTrx.setOary(oaryList);
        outTrx.setRtn_code("0000000");
        outTrx.setRtn_mesg("success");
//        return JSON.toJSONString(outTrx, SerializerFeature.WriteNullStringAsEmpty);
        return JSON.toJSONString(outTrx);
    }

}
