package com.soft1851.user.center.service;

import com.soft1851.user.center.domain.dto.LoginDTO;
import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.User;

import java.util.List;

/**
 * @author Su
 * @className UserService
 * @Description TODO
 * @Date 2020/9/29 16:04
 * @Version 1.0
 **/
public interface UserService {

    User findUserById(Integer id);

    int updateBonus(UserAddBonusMsgDTO userAddBonusMsgDto);


    /**
     * 登录
     * @param loginDto
     * @return
     */
    User login(LoginDTO loginDto, String openId);

    List<BonusEventLog> getBonusEventLogs(int userId);
}
