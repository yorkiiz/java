package com.yorkiiz.yysdata.dao.Impl;

import com.yorkiiz.yysdata.dao.UserDao;
import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/


public class UserDaoImpl implements UserDao {



    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    public List<YysData> findAll() {
        //使用JDBC操作数据库.....
        String sql = "select * from yysdata";
        List<YysData> users = template.query(sql, new BeanPropertyRowMapper<YysData>(YysData.class));

        return users;
    }

    public void adddata(YysData yysData){
        String sql = "insert into yysdata (date,number,gold,tili,blue_ticket,gouyu,shepi,goldenshepi,comment)" +
                "values (?,?,?,?,?,?,?,?,?)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        template.update(sql,timestamp,yysData.getNumber(),yysData.getGold(),yysData.getTili(),
        yysData.getBlue_ticket(),yysData.getGouyu(),yysData.getShepi(),yysData.getGoldenshepi(),yysData.getComment());

    }

    @Override
    public void deletedata(YysData yysData) {
        String sql = "delete from yysdata where date = ? and number = ?";
        template.update(sql,yysData.getDate(),yysData.getNumber());
    }


}
