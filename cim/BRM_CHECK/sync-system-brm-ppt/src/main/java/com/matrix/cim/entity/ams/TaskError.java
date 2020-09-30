package com.matrix.cim.entity.ams;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskError {

    private String task_name;
    private Timestamp t_stamp;
    private String task_version;
    private String from_time;
    private String to_time;
    private String error_type;
    private String error_msg;
    private Integer states;
}
