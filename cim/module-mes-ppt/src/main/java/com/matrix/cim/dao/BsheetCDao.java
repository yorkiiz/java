package com.matrix.cim.dao;

import com.matrix.cim.entity.Bsheet;
import com.matrix.cim.entity.BsheetC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BsheetCDao {
    @Autowired
    private JdbcTemplate mesJdbcTemplate;

    public List<BsheetC> queryByShtIds(List<String> shtIdList){

        String sql = "SELECT * FROM PPT.BSHEET_C WHERE C_SHT_ID IN (:sht_ids)";

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("sht_ids", shtIdList);

        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper(BsheetC.class));
    }
}
