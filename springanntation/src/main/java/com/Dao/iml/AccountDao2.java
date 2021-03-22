package com.Dao.iml;

import com.Dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

@Repository
public class AccountDao2 implements IAccountDao {


    public void saveaccount() {
        System.out.println("acountdao2222222222");
    }
}
