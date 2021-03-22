package com.service.iml;

import com.Dao.IAccountDao;
import com.service.IsaveAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Service
public class SaveAccountService implements IsaveAccountService {


    @Autowired
    private IAccountDao accountDao1;

    public void save() {
        accountDao1.saveaccount();
    }

    @Override
    public void test(){
        save();
    }
}
