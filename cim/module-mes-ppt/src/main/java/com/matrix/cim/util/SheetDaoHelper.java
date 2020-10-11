package com.matrix.cim.util;

import com.matrix.cim.dao.BsheetCDao;
import com.matrix.cim.dao.BsheetDao;
import com.matrix.cim.entity.Bsheet;
import com.matrix.cim.entity.BsheetC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SheetDaoHelper {

    @Autowired
    private BsheetDao bsheetDao;
    @Autowired
    private BsheetCDao bsheetCDao;

    /**
     * 先从BSHEET_C表中根据C_SHT_ID获取sht_id
     * 如果BSHEET_C表中存在，那么使用此表中的sht_id回传BSHEET表
     * 否则直接使用InTrx的sht_id 去读 BSHEET表
     *
     * @param shtIdList 玻璃ID接
     * @return BSHEET集合
     */
    public List<Bsheet> queryBothCustomAndBSheetByShtIds(List<String> shtIdList) {
        List<BsheetC> bsheetCList = bsheetCDao.queryByShtIds(shtIdList);

        for (BsheetC bsheetC : bsheetCList) {
            shtIdList.remove(bsheetC.getCShtId());
            shtIdList.add(bsheetC.getShtId());
        }

        return bsheetDao.queryByShtIds(shtIdList);
    }


    /**
     * 先从BSHEET_C表中根据C_SHT_ID获取sht_id
     * 如果BSHEET_C表中存在，那么使用此表中的sht_id回传BSHEET表
     * 否则直接使用InTrx的sht_id 去读 BSHEET表
     *
     * @param shtIdList 玻璃ID集合
     * @return 玻璃表集合
     */
    public List<Bsheet> queryBothCustomAndBSheetByShtIds(List<String> shtIdList, String eqptId, String worderId, String grade, String shtStat) {
        List<BsheetC> bsheetCList = bsheetCDao.queryByShtIds(shtIdList);

        for (BsheetC bsheetC : bsheetCList) {
            shtIdList.remove(bsheetC.getCShtId());
            shtIdList.add(bsheetC.getShtId());
        }

        return bsheetDao.queryByShtIds(shtIdList, eqptId, worderId, grade, shtStat);
    }
}
