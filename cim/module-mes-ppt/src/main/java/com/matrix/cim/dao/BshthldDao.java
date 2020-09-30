package com.matrix.cim.dao;

import com.matrix.cim.entity.Bsheet;
import com.matrix.cim.entity.Bshthld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BshthldDao {

    @Autowired
    private JdbcTemplate mesJdbcTemplate;

    public List<Bshthld> queryByShtIds(List<String> shtList) {

        String sql = "SELECT * FROM PPT.BSHTHLD WHERE SHT_ID IN (:sht_ids)";

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(mesJdbcTemplate);

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("sht_ids", shtList);

        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper(Bshthld.class));
    }
}
