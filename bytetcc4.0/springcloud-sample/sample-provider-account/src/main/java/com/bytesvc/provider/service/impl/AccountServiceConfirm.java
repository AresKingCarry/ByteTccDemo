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

@Service("accountServiceConfirm")
public class AccountServiceConfirm implements AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceConfirm.class);
	private final AccountMapper accountMapper;

	@Autowired(required = false)
	public AccountServiceConfirm(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	@Transactional(rollbackFor = Exception.class)
	public boolean payment(AccountDTO accountDTO) throws Exception {
		LOGGER.info("============springcloud tcc 执行确认付款接口===============");
		LOGGER.info("入参："+accountDTO.toString());

		AccountDO accountDO = new AccountDO();
		accountDO.setExchangeMoney(accountDTO.getAmount());
		accountDO.setUpdateTime(new Date());
		accountDO.setUserId(accountDTO.getUserId());
		synchronized(this) {
			final int rows = accountMapper.confirm(accountDO);
			if (rows != 1) {
				LOGGER.error("确认扣减账户异常！");
				throw new Exception("【confirm】确认扣减账户异常！");
			}
		}
		return Boolean.TRUE;
	}


}
