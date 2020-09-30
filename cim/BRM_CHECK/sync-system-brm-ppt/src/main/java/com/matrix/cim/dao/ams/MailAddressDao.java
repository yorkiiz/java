package com.matrix.cim.dao.ams;

import com.matrix.cim.entity.ams.EmailGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailAddressDao {

    @Autowired
    private JdbcTemplate amsJdbcTemplate;

    @Value("${mes.mail.group}")
    private String groupName;

    public List<EmailGroup> queryMailGroup(){
        String sql = String.format("select * from etl_config.email_group_info where group_name = '%s'",groupName);
        BeanPropertyRowMapper<EmailGroup> rowMapper = new BeanPropertyRowMapper<>(EmailGroup.class);
        return amsJdbcTemplate.query(sql,rowMapper);
    }
}
