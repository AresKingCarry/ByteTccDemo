package com.bytesvc.provider.service.impl;

import com.bytesvc.provider.dto.AccountDTO;
import com.bytesvc.provider.entity.AccountDO;
import com.bytesvc.provider.mapper.AccountMapper;
import com.bytesvc.provider.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("accountServiceCancel")
public class AccountServiceCancel implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceCancel.class);

	private final AccountMapper accountMapper;

	@Autowired(required = false)
	public AccountServiceCancel(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean payment(AccountDTO accountDTO) throws Exception {
		LOGGER.info("============springcloud tcc 执行取消付款接口===============");

//        final AccountDO accountDO = accountMapper.findByUserId(accountDTO.getUserId());
		//用户总金额+消费金额
//        accountDO.setBalance(accountDO.getBalance().add(accountDTO.getAmount()));
		//冻结金额-消费金额
//        accountDO.setFreezeAmount(accountDO.getFreezeAmount().subtract(accountDTO.getAmount()));

		AccountDO accountDO = new AccountDO();
		accountDO.setExchangeMoney(accountDTO.getAmount());
		accountDO.setUpdateTime(new Date());
		accountDO.setUserId(accountDTO.getUserId());
		synchronized(this) {
			final int rows = accountMapper.cancel(accountDO);
			if (rows != 1) {
				LOGGER.error("取消扣减账户异常！");
				throw new Exception("【cancel】取消扣减账户异常！");
			}
		}
		return Boolean.TRUE;
	}


}
