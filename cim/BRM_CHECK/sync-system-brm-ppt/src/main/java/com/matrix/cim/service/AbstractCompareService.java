package com.matrix.cim.service;

import com.matrix.cim.dao.ams.AlarmDao;
import com.matrix.cim.entity.ams.AlarmMessage;
import com.matrix.cim.entity.mes.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Slf4j
public abstract class AbstractCompareService<T extends BaseEntity> {

    @Autowired
    private JdbcTemplate mesJdbcTemplate;

    @Value("${mes.brm.schema}")
    private String brmSchema;

    @Value("${mes.ppt.schema}")
    private String pptSchema;

    @Autowired
    private AlarmDao alarmDao;

    public boolean compateList(T entity) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<T> brmEntityList = queryBrmAll(entity);
        List<T> pptEntityList = queryPptAll(entity);
        log.info("[{}]与[{}] 比对，共 [{}] 笔数据", entity.getBrmTableName(), entity.getPptTableName(), brmEntityList.size());
        boolean b = compareTwoList(brmEntityList, pptEntityList);
        if (b) {
            log.info("[{}]检查成功", entity.getPptTableName());
        }
        return b;
    }

    public List<T> queryBrmAll(T entity) {
        String sql = String.format("select * from %s.%s", brmSchema, entity.getBrmTableName());
        BeanPropertyRowMapper<T> rowMapper = new BeanPropertyRowMapper<T>((Class<T>) entity.getClass());

        return mesJdbcTemplate.query(sql, rowMapper);
    }


    public List<T> queryPptAll(T entity) {
        String sql = String.format("select * from %s.%s", pptSchema, entity.getPptTableName());
        BeanPropertyRowMapper<T> rowMapper = new BeanPropertyRowMapper<>((Class<T>) entity.getClass());
        return mesJdbcTemplate.query(sql, rowMapper);
    }


    public boolean compareTwoList(List<T> brmEntityList, List<T> pptEntityList) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        boolean isSame = true;

        for (T brmEntity : brmEntityList) {
            boolean existFlag = true;

            //for和foreach 写法一边循环，一边删除会出问题,  所以要使用迭代器
            Iterator<T> it = pptEntityList.iterator();
            while (it.hasNext()) {
                T pptEntity = it.next();
                /**
                 * 失败分为三种情况
                 * 1: 在BRM中存在，在PPT中不存在 ==> 说明BRM没有Release 成功
                 *
                 * 2. 在PPT中存在，但在BRM中不存在 ==> 说明BRM的删除动作没有成功
                 *
                 * 3.  BRM和PPT表中有相同主键的记录
                 *  3.1 值相同，比对成功
                 *  3.2 值不相同，比对不成功
                 */


                //可以找到主键相同的同一笔记录
                if (brmEntity.pkEquals(pptEntity)) {

                    //主键相同的两笔记录， 各字段值也相同，此笔记录正常
                    if (brmEntity.equals(pptEntity)) {
                        handleCompareSuccessful(brmEntity, pptEntity);
                    } else {//主键相同的两笔记录，有字段值不相同，此笔记录异常
                        handleBrmUnequals(brmEntity, pptEntity);
                        isSame = false;
                    }

                    existFlag = true;
                    //for和foreach 写法一边循环，一边删除会出问题,  所以要使用迭代器 从pptCodeList中 移除pptCode
                    it.remove();
                    break;
                }

            }

            /**
             *  根据主键找不到 => BRM的此笔记录没有release成功
             */
            if (!existFlag) {
                handleBrmNoReleased(brmEntity);
                isSame = false;
            }

        }


        /**
         * PPT中有，BRM中没有 => BRM的删除动作没有成功
         */
        if (pptEntityList.size() > 0) {
            for (T pptEntity : pptEntityList) {
                handleBrmNoDeletedCase(pptEntity);
            }
            isSame = false;
        }
        return isSame;
    }

    /**
     * PPT中有，BRM中没有 => BRM的删除动作没有成功
     *
     * @param pptEntity
     */
    protected void handleBrmNoDeletedCase(T pptEntity) {
        log.error("[{}] 比对异常,BRM没有删除成功, PPT: [{}] ", pptEntity.getPptTableName(), pptEntity);


        String message = String.format("[%s] 比对异常,BRM没有删除成功, PPT: [%s]", pptEntity.getPptTableName(), pptEntity);
        //针对表[*ROUTE]，BRM 中删除，PPT未删除，则仅记录日志，不作为异常报警部分
        if (!StringUtils.endsWithIgnoreCase(pptEntity.getPptTableName(), "route")) {
            AlarmMessage.list.add(message);
        }


//        alarmDao.sendAlarm(message);
    }


    /**
     * BRM中有，PPT中没有 => BRM的此笔记录没有release成功
     *
     * @param brmEntity
     */
    protected void handleBrmNoReleased(T brmEntity) {
        log.error("[{}] 比对异常,BRM没有Release成功, BRM: [{}] 找PPT表中找不到记录", brmEntity.getPptTableName(), brmEntity);
        String message = String.format("[%s] 比对异常,BRM没有Release成功, BRM: [%s]", brmEntity.getPptTableName(), brmEntity);
        AlarmMessage.list.add(message);
//        alarmDao.sendAlarm(message);
    }


    /**
     * BRM和PPT中主键相同的两笔记录，但是不相同
     *
     * @param brmEntity
     * @param pptEntity
     */
    protected void handleBrmUnequals(T brmEntity, T pptEntity) {
        log.error("[{}] 比对异常, BRM和PPT不一致, BRM:[{}] vs PPT: [{}]", brmEntity.getPptTableName(), brmEntity, pptEntity);

        try {
            /**
             * 不相同部分以红色标记出来
             */
            Map<String, String> brmMap = BeanUtils.describe(brmEntity);

            List<String> diffKeys = new ArrayList<>();
            Set<Map.Entry<String, String>> brmMapEntrySet = brmMap.entrySet();
            for (Map.Entry<String, String> brmEntry : brmMapEntrySet) {
                String key = brmEntry.getKey();
                String brmValue = brmEntry.getValue();
                String pptValue = BeanUtils.getProperty(pptEntity, key);
                if(!StringUtils.equals(brmValue,pptValue)){
                    diffKeys.add(key);
                }
            }
            String brmEntityString = brmEntity.toString();
            String pptEntityString = pptEntity.toString();

            for(String diffKey : diffKeys){
                brmEntityString = brmEntityString.replaceFirst(diffKey,"<font color=\"red\">"+ diffKey + "</font>");
                pptEntityString = pptEntityString.replaceFirst(diffKey,"<font color=\"red\">"+ diffKey + "</font>");
            }

            String message = String.format("[%s] 比对异常, BRM和PPT不一致, BRM:[%s] vs PPT: [%s]", brmEntity.getPptTableName(), brmEntityString, pptEntityString);
            AlarmMessage.list.add(message);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 比对成功
     *
     * @param brmEntity
     * @param pptEntity
     */
    protected void handleCompareSuccessful(T brmEntity, T pptEntity) {

    }
}
