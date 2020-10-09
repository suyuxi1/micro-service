package com.soft1851.user.center.service;

import com.soft1851.user.center.domain.dto.UserAddBonusMsgDto;
import com.soft1851.user.center.domain.entity.User;

/**
 * @author Su
 * @className UserService
 * @Description TODO
 * @Date 2020/9/29 16:04
 * @Version 1.0
 **/
public interface UserService {

    User findUserById(Integer id);

    int updateBonus(UserAddBonusMsgDto userAddBonusMsgDto);


}
