package com.yorkiiz.dao.imp;

import com.yorkiiz.dao.IAccountDao;
import com.yorkiiz.domain.Account;
import com.yorkiiz.util.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Repository("accountDao")
public class AccountDao implements IAccountDao {

    @Autowired
    private QueryRunner runner;
    @Autowired
    private ConnectionUtils connectionUtils;

    public void addaccount(String name) {

    }

    public void deleteaccount(String name) {

    }

    public Account findaccount(String name) throws SQLException {
        List<Account> accounts = runner.query(connectionUtils.getThreadConnection(),"select * from account where name = ?",new BeanListHandler<Account>(Account.class),name);
        if(accounts.size()>1){
            throw new RuntimeException("结果不唯一");
        }else if(accounts.size()==1){
            return accounts.get(0);
        }else if(accounts.size()==0){
            return null;
        }
        return null;
    }

    public void updateaccount(Account account) throws SQLException {
        runner.update(connectionUtils.getThreadConnection(),"update account set money = ? where id=?",account.getMoney(),account.getId());

    }
}
