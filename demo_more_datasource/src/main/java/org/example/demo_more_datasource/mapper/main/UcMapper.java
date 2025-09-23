package org.example.demo_more_datasource.mapper.main;

import org.apache.ibatis.annotations.Mapper;
import org.example.demo_more_datasource.entiy.UcRecord;

/**
 * *@ClassName efewf
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/22 21:52
 * *Version 1.0
 **/
@Mapper
public interface UcMapper {
    void insert(UcRecord record);
    void update(UcRecord record);
    void delete(Long id);
    UcRecord select(Long id);
}