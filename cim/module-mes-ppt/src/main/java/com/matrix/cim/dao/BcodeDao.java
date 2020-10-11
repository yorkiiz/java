package com.matrix.cim.dao;

import com.matrix.cim.entity.Bcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BcodeDao {
    @Autowired
    private JdbcTemplate mesJdbcTemplate;

    public List<Bcode> queryBySql(String sql) {
        List<Bcode> bcodeList = mesJdbcTemplate.query(sql, new BeanPropertyRowMapper(Bcode.class));
        return bcodeList;
    }
}
