package com.matrix.cim.service;

import com.alibaba.fastjson.JSON;
import com.matrix.cim.core.service.MatrixBaseService;
import com.matrix.cim.dao.BsheetCDao;
import com.matrix.cim.dao.BsheetDao;
import com.matrix.cim.dao.BshthldDao;
import com.matrix.cim.entity.Bsheet;
import com.matrix.cim.entity.BsheetC;
import com.matrix.cim.entity.Bshthld;
import com.matrix.cim.tx.bpihldsh.BPIHLDSHi;
import com.matrix.cim.tx.bpihldsh.BPIHLDSHo;
import com.matrix.cim.tx.bpihldsh.BPIHLDSHo_a;
import com.matrix.cim.tx.bpihldsh.BPIHLDSHo_a1;
import com.matrix.cim.tx.bpinqsht.BPINQSHTi_a;
import com.matrix.cim.util.MatrixBeanUtil;
import com.matrix.cim.util.SheetDaoHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BPIHLDSH")
public class BpihldshService extends MatrixBaseService {

    @Autowired
    private BsheetDao bsheetDao;
    @Autowired
    private BsheetCDao bsheetCDao;

    @Autowired
    private BshthldDao bshthldDao;

    @Autowired
    private SheetDaoHelper sheetDaoHelper;


    @Override
    public String subMainProc(String strInTrx) {

        BPIHLDSHi inTrx = JSON.parseObject(strInTrx, BPIHLDSHi.class);
        BPIHLDSHo outTrx = new BPIHLDSHo();




        List<Bsheet> bsheetList = null;
        if (StringUtils.isNoneBlank(inTrx.getWorder_id())) {
            bsheetList = getSheetInfoByWorderId(inTrx.getWorder_id());
        } else {
            List<BPINQSHTi_a> iaryList = inTrx.getIary();
            if (iaryList.isEmpty()) {
                outTrx.setRtn_code("0000001");
                outTrx.setRtn_mesg("传入的玻璃ID不能为空");
                return JSON.toJSONString(outTrx);
            }

            bsheetList = getSheetInfoByShtIds(iaryList);
        }

        if(CollectionUtils.isEmpty(bsheetList)){
            outTrx.setSht_cnt("0");
            outTrx.setRtn_code("0000000");
            outTrx.setRtn_mesg("success");
            return JSON.toJSONString(outTrx);
        }

        List<String> dbShtIds = new ArrayList<>();

        for (Bsheet bsheet : bsheetList) {
            dbShtIds.add(bsheet.getShtId());
        }

        List<Bshthld> bshthldList = bshthldDao.queryByShtIds(dbShtIds);

        Map<String, List<Bshthld>> bshthldMap = new HashMap<>();
        for (Bshthld bshthld : bshthldList) {
            String shtId = bshthld.getShtId();
            List<Bshthld> bshthldMapList = bshthldMap.get(shtId);
            if (bshthldMapList == null) {
                bshthldMapList = new ArrayList<>();
            }
            bshthldMapList.add(bshthld);
            bshthldMap.put(shtId, bshthldMapList);
        }

        List<BPIHLDSHo_a> oaryList = new ArrayList<>();
        for (Bsheet bsheet : bsheetList) {

            List<Bshthld> bshthldMapList = bshthldMap.get(bsheet.getShtId());
            if (bshthldMapList == null) {
                outTrx.setRtn_code("0000002");
                outTrx.setRtn_mesg("导入的玻璃不在Hold保留记录中");
                return JSON.toJSONString(outTrx);
            }

            BPIHLDSHo_a oary = new BPIHLDSHo_a();
            MatrixBeanUtil.copyProperty(oary, bsheet);

            List<BPIHLDSHo_a1> oary1List = new ArrayList<>();
            for (Bshthld bshthld : bshthldMapList) {
                BPIHLDSHo_a1 oary1 = new BPIHLDSHo_a1();
                MatrixBeanUtil.copyProperty(oary1, bshthld);
                oary1List.add(oary1);
            }
            oary.setOary1(oary1List);

            oaryList.add(oary);
        }

        outTrx.setOary(oaryList);
        outTrx.setSht_cnt(String.valueOf(oaryList.size()));
        outTrx.setRtn_code("0000000");
        outTrx.setRtn_mesg("success");
        return JSON.toJSONString(outTrx);
    }

    private List<Bsheet> getSheetInfoByWorderId(String worderId) {
        return bsheetDao.queryByWorderId(worderId);
    }

    private List<Bsheet> getSheetInfoByShtIds(List<BPINQSHTi_a> iaryList) {
        List<String> shtIdList = new ArrayList<>();

        for (BPINQSHTi_a iary : iaryList) {
            shtIdList.add(iary.getSht_id());
        }

        return sheetDaoHelper.queryBothCustomAndBSheetByShtIds(shtIdList);
    }

}
