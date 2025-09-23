package org.example.demo_more_datasource.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.example.demo_more_datasource.entiy.UcRecord;
import org.example.demo_more_datasource.entiy.User;
import org.example.demo_more_datasource.mapper.main.UcMapper;
import org.example.demo_more_datasource.mapper.main.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * *@ClassName dfsfd
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:53
 * *Version 1.0
 **/
@Service
public class MixedService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UcMapper ucMapper;

    @Autowired @Qualifier("mainJdbc")
    private JdbcTemplate mainJdbc;

    @Autowired @Qualifier("ucJdbc")
    private JdbcTemplate ucJdbc;

    /**
     * 使用 Seata 全局事务保证跨数据源一致性
     */
    @GlobalTransactional(name = "insert-and-update", rollbackFor = Exception.class)
    public void insertAndUpdate(User user, UcRecord record) {
        // db1 插入用户
        userMapper.insert(user);

        // db2 更新 UC 表
        ucMapper.update(record);

        // JdbcTemplate 同样可以使用
        mainJdbc.update("INSERT INTO user(name) VALUES(?)", user.getName());
        ucJdbc.update("UPDATE uc_table SET value=? WHERE id=?", record.getValue(), record.getId());
    }
}
