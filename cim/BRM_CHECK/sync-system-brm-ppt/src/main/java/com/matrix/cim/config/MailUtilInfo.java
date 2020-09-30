package com.matrix.cim.config;

import com.matrix.cim.util.DesEncrypt;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MailUtilInfo {
    private String mailHost;
    private String mailPort;

    private String username;

    private String password;

    public void init(){
        try{
            this.password = DesEncrypt.doDecrypt(this.password);
        }catch (Exception e){

        }

    }
}
