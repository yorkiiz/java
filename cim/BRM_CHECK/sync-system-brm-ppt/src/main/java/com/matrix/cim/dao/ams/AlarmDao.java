package com.matrix.cim.dao.ams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Repository
@Slf4j
public class AlarmDao {
    @Autowired
    private JdbcTemplate amsJdbcTemplate;



    public void sendAlarm(String msg) {
        try{
            String sql = "insert into etl_config.task_error(task_name,t_stamp,task_version,from_time,to_time,error_type,error_msg,states) values(?,?,?,?,?,?,?,?)";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long time = System.currentTimeMillis();
            String currentTimeStr = sdf.format(new Date(time));
            // TODO: 2019/9/26 将比对结果写入数据库中
//            amsJdbcTemplate.update(sql, "MES_SYNC_BRM_PPT",  new Timestamp(time),"v1.0", currentTimeStr,currentTimeStr,"error",msg,1);
        }catch (Exception e){
            log.error("ERROR信息存储异常，异常信息", e);
        }

//        log.info("alarm 发送成功");
    }

}
