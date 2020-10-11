package com.matrix.cim.dao;

import com.matrix.cim.entity.Bshtpnl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BshtpnlDao {

    @Autowired
    private JdbcTemplate mesJdbcTemplate;

    public List<Bshtpnl> queryByShtIds(List<String> shtList) {

        StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM PPT.BSHTPNL WHERE ");
        sqlBuffer.append("PNL_ID in (:sht_ids)");

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("sht_ids", shtList);

        return jdbcTemplate.query(sqlBuffer.toString(), paramMap, new BeanPropertyRowMapper(Bshtpnl.class));
    }

}
