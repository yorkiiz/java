package com.matrix.cim.dao;

import com.matrix.cim.entity.Bcode;
import com.matrix.cim.entity.Bsheet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BsheetDao {

    @Autowired
    private JdbcTemplate mesJdbcTemplate;

    public List<Bsheet> queryByShtIds(List<String> shtList) {

        StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM PPT.BSHEET WHERE ");
        sqlBuffer.append("sht_id in (:sht_ids)");

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("sht_ids", shtList);

        return jdbcTemplate.query(sqlBuffer.toString(), paramMap, new BeanPropertyRowMapper(Bsheet.class));
    }

    public List<Bsheet> queryByShtIds(List<String> shtList, String eqptId, String worderId, String grade, String shtStat) {

        StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM PPT.BSHEET WHERE ");
        sqlBuffer.append("SHT_ID in (:sht_ids)");

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("sht_ids", shtList);


        if (StringUtils.isNotBlank(shtStat)) {
            sqlBuffer.append(" AND SHT_STAT = :sht_stat ");
            paramMap.put("sht_stat", shtStat);
        }

        if (StringUtils.isNotBlank(eqptId)) {
            sqlBuffer.append(" AND ( NX_EQPT_ID = :eqpt_id OR NX_EQPTG_ID IN (SELECT EQPTG_ID FROM PPT.BEQPTGR WHERE EQPT_ID = :eqpt_id ))");
            paramMap.put("eqpt_id", eqptId);
        }
        if (StringUtils.isNotBlank(worderId)) {
            sqlBuffer.append(" AND WORDER_ID = :worder_id");
            paramMap.put("worder_id", worderId);
        }
        if (StringUtils.isNotBlank(grade)) {
            sqlBuffer.append(" AND GRADE IN (SELECT SHT_ID FROM BSHTVRY WHERE GRADE = :grade)");
            paramMap.put("grade", grade);
        }
        return jdbcTemplate.query(sqlBuffer.toString(), paramMap, new BeanPropertyRowMapper(Bsheet.class));
    }

    public List<Bsheet> queryByCustomShtIds(List<String> customShtList) {

        StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM PPT.BSHEET WHERE ");
        sqlBuffer.append("SHT_ID IN (SELECT SHT_ID FROM PPT.BSHEET_C WHERE C_SHT_ID IN (:sht_ids))");
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("sht_ids", customShtList);

        return jdbcTemplate.query(sqlBuffer.toString(), paramMap, new BeanPropertyRowMapper(Bsheet.class));
    }

    public List<Bsheet> queryByWorderId(String worderId) {
        String sql = "SELECT * FROM PPT.BSHEET WHERE WORDER_ID = :worder_id";

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("worder_id", worderId);

        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper(Bsheet.class));

    }
}
