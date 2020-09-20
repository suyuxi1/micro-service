package com.soft1851.user.mapper;

import com.soft1851.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */

@Mapper
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User queryById(@Param("id") String id);

}
