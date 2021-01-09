package com.yorkiiz.yysdata.dao.Impl;

import com.mysql.jdbc.StringUtils;
import com.yorkiiz.yysdata.dao.UserDao;
import com.yorkiiz.yysdata.domain.PageBean;
import com.yorkiiz.yysdata.domain.YysData;
import com.yorkiiz.yysdata.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String sql_bk = "insert into yysdata_bk (date,number,gold,tili,blue_ticket,gouyu,shepi,goldenshepi,comment)" +
                "values (?,?,?,?,?,?,?,?,?)";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        template.update(sql,timestamp,yysData.getNumber(),yysData.getGold(),yysData.getTili(),
        yysData.getBlue_ticket(),yysData.getGouyu(),yysData.getShepi(),yysData.getGoldenshepi(),yysData.getComment());

        template.update(sql_bk,timestamp,yysData.getNumber(),yysData.getGold(),yysData.getTili(),
                yysData.getBlue_ticket(),yysData.getGouyu(),yysData.getShepi(),yysData.getGoldenshepi(),yysData.getComment());


    }

    @Override
    public void deletedata(YysData yysData) {
        String sql = "delete from yysdata where date = ? and number = ?";
        template.update(sql,yysData.getDate(),yysData.getNumber());
    }

    @Override
    public YysData find(YysData yysData) {
        String sql = "select * from yysdata where date = ? and number = ?";
        YysData yysData1 = template.queryForObject(sql,new BeanPropertyRowMapper<YysData>(YysData.class),yysData.getDate(),yysData.getNumber());
        return yysData1;

    }

    @Override
    public void update(YysData yysData) {
        String sql = "update yysdata set gold=?, " +
                "tili=?, " +
                "blue_ticket=?, " +
                "gouyu=?, " +
                "shepi=?, " +
                "goldenshepi=?, " +
                "shepiao=?, " +
                "comment=? " +
                "where date=? and number=?";
        int resoult =  template.update(sql,yysData.getGold(),yysData.getTili(),yysData.getBlue_ticket(),yysData.getGouyu(),
                yysData.getShepi(),yysData.getGoldenshepi(),yysData.getShepiao(),yysData.getComment(),yysData.getDate(),yysData.getNumber());

        System.out.println(resoult);

    }

    @Override
    public void deletesel(String[] tids) throws ParseException {

        for (String tid : tids) {

            String sql = "delete  from yysdata where date = ?";
            template.update(sql,tid);


        }
    }

    @Override
    public List<YysData> select(String t1, String t2, String number) {
        String sql = "select * from yysdata where 1 = 1 ";
        if(!StringUtils.isNullOrEmpty(t1)){
            sql = sql+"and date > '"+t1+"'";
        }

        if(!StringUtils.isNullOrEmpty(t2)){
            sql = sql+" and date < '"+t2+"'";
        }

        if(!StringUtils.isNullOrEmpty(number)){
            sql = sql+" and number = '"+number+"'";
        }


        List<YysData> yysData1 = template.query(sql, new BeanPropertyRowMapper<YysData>(YysData.class));
        return yysData1;
    }

    @Override
    public List<YysData> findbyPage(int startrows, int endrows) {
        String sql = "select * from yysdata limit ?,?";
        List<YysData> users = template.query(sql, new BeanPropertyRowMapper<YysData>(YysData.class),startrows,endrows);

        return users;

    }

    @Override
    public Integer findAllcount() {
        String sql = "select count(*) from yysdata ";
        return template.queryForObject(sql,Integer.class);

    }


}
