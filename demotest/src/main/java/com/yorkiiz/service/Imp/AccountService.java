package com.yorkiiz.service.Imp;

import com.yorkiiz.dao.IAccountDao;
import com.yorkiiz.domain.Account;
import com.yorkiiz.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Service("accountService")
public class AccountService implements IAccountService {


    @Resource
    private IAccountDao accountDao;
    public void transferaccount(String tartgetname, String sourcename, double money) throws SQLException {
        //1.拿到两个账户
       Account tartgetaccount = accountDao.findaccount(tartgetname);
       Account sourceaccount = accountDao.findaccount(sourcename);

        //2.设置账户的金额
        tartgetaccount.setMoney(tartgetaccount.getMoney()+money);
        sourceaccount.setMoney(sourceaccount.getMoney()-money);
        //3.更新两个账户金额
        accountDao.updateaccount(tartgetaccount);
        accountDao.updateaccount(sourceaccount);
    }
}
