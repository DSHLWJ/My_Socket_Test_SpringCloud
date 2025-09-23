package org.example.demo_more_datasource.mapper.main;

import org.apache.ibatis.annotations.Mapper;
import org.example.demo_more_datasource.entiy.User;

/**
 * *@ClassName sdfo
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:51
 * *Version 1.0
 **/
@Mapper
public interface UserMapper {
    void insert(User user);
    void update(User user);
    void delete(Long id);
    User select(Long id);
}
