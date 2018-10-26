package com.bytesvc.provider.service.impl;/**
 * Created by William on 2018/10/24.
 */

import com.bytesvc.provider.dto.AccountDTO;
import com.bytesvc.provider.entity.AccountDO;
import com.bytesvc.provider.mapper.AccountMapper;
import com.bytesvc.provider.service.AccountService;
import org.bytesoft.compensable.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <br>
 * Description: AccountServiceImpl<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : WangLei<br>
 * Date       : 2018/10/24 11:02<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018/10/24             WangLei         新增              1001<br>
 ********************************************************************/
@Service("accountService")
@Compensable(
        interfaceClass = AccountService.class
        , confirmableKey = "accountServiceConfirm"
        , cancellableKey = "accountServiceCancel"
)
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean payment(AccountDTO accountDTO) throws Exception {
        LOGGER.info("============springcloud执行try付款接口===============");

        AccountDO accountDO = new AccountDO();
        accountDO.setExchangeMoney(accountDTO.getAmount());
        accountDO.setUpdateTime(new Date());
        accountDO.setUserId(accountDTO.getUserId());
        synchronized(this) {
            final int update = accountMapper.update(accountDO);
            if (update != 1) {
                LOGGER.error("资金不足！");
                throw new Exception("【try】资金不足！");
            }
        }
        return Boolean.TRUE;
    }


}
