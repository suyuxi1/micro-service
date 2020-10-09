package com.soft1851.user.center.mapper;

import com.soft1851.user.center.domain.entity.User;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

;

/**
 * @author Su
 * @className UserMapper
 * @Description TODO
 * @Date 2020/9/29 16:05
 * @Version 1.0
 **/

public interface UserMapper extends Mapper<User> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User queryById(@Param("id") Integer id);
}
