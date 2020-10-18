package com.soft1851.user.center.rocketmq;

import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.User;
import com.soft1851.user.center.mapper.BonusEventLogMapper;
import com.soft1851.user.center.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author su
 * @className AddBonusListener
 * @Description TODO
 * @Date 2020/10/8
 * @Version 1.0
 **/

@Service
@RocketMQMessageListener(consumerGroup = "consumer", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDto) {
        log.info("参数为{}", userAddBonusMsgDto);
        //1.为用户加积分
        Integer userId = userAddBonusMsgDto.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        log.info("用户信息{}", user);
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);

        //2.写积分日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(userAddBonusMsgDto.getBonus())
                .event("CONTRIBUTE")
                .createTime(new Date())
                .description("投稿积分")
                .build());
//        log.info("日志信息{}",11);

    }
}
