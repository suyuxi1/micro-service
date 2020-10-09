package com.soft1851.user.center.service.impl;

import com.soft1851.user.center.domain.dto.UserAddBonusMsgDto;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.User;
import com.soft1851.user.center.mapper.BonusEventLogMapper;
import com.soft1851.user.center.mapper.UserMapper;
import com.soft1851.user.center.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author su
 * @className UserServiceImpl
 * @Description TODO
 * @Date 2020/9/29
 * @Version 1.0
 **/

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer id) {
        return userMapper.queryById(id);
    }


    @Resource
    private BonusEventLogMapper bonusEventLogMapper;

    @Override
    public int updateBonus(UserAddBonusMsgDto userAddBonusMsgDto) {
        User user = userMapper.selectByPrimaryKey(userAddBonusMsgDto.getUserId());
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
        return bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userAddBonusMsgDto.getUserId())
                .value(userAddBonusMsgDto.getBonus())
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("投稿积分")
                .build());
    }


}
