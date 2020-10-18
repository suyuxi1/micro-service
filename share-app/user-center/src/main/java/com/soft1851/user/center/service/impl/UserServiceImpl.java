package com.soft1851.user.center.service.impl;

import com.soft1851.user.center.domain.dto.LoginDTO;
import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.User;
import com.soft1851.user.center.mapper.BonusEventLogMapper;
import com.soft1851.user.center.mapper.UserMapper;
import com.soft1851.user.center.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public int updateBonus(UserAddBonusMsgDTO userAddBonusMsgDto) {
        User user = userMapper.selectByPrimaryKey(userAddBonusMsgDto.getUserId());
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
        return bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userAddBonusMsgDto.getUserId())
                .value(userAddBonusMsgDto.getBonus())
                .event(userAddBonusMsgDto.getEvent())
                .createTime(new Date())
                .description(userAddBonusMsgDto.getDescription())
                .build());
    }

    @Override
    public User login(LoginDTO loginDto, String openId) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxId", openId);
        List<User> users = userMapper.selectByExample(example);
        // 新用户直接注册
        if (users.size() == 0) {
            User saveUser = User.builder()
                    .wxId(openId)
                    .avatarUrl(loginDto.getAvatarUrl())
                    .wxNickname(loginDto.getWxNickname())
                    .roles("user")
                    .bonus(100)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(saveUser);
            return saveUser;
        }
        return users.get(0);
    }

    @Override
    public List<BonusEventLog> getBonusEventLogs(int userId) {
        Example example = new Example(BonusEventLog.class);
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return bonusEventLogMapper.selectByExample(example);
    }

}
