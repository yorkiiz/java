package com.Dao.iml;

import com.Dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Repository
public class AccountDao1 implements IAccountDao {


    public void saveaccount() {
        System.out.println("accountdao11111111");
    }
}
