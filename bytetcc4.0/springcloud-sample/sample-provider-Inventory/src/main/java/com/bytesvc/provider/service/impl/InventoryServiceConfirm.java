package com.bytesvc.provider.service.impl;

import com.bytesvc.provider.dto.InventoryDTO;
import com.bytesvc.provider.entity.InventoryDO;
import com.bytesvc.provider.mapper.InventoryMapper;
import com.bytesvc.provider.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("inventoryServiceConfirm")
public class InventoryServiceConfirm implements InventoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceConfirm.class);

	private final InventoryMapper inventoryMapper;

	@Autowired(required = false)
	public InventoryServiceConfirm(InventoryMapper inventoryMapper) {
		this.inventoryMapper = inventoryMapper;
	}
	@Transactional(rollbackFor = Exception.class)
	public Boolean decrease(InventoryDTO inventoryDTO) throws Exception {
		LOGGER.info("==========Springcloud调用扣减库存确认方法=confirm方法==========");
//        final InventoryDO entity = inventoryMapper.findByProductId(inventoryDTO.getProductId());
//        entity.setLockInventory(entity.getLockInventory() - inventoryDTO.getCount());

		InventoryDO entity = new InventoryDO();
		entity.setExchangeInventory(inventoryDTO.getCount());
		entity.setProductId(inventoryDTO.getProductId());
		LOGGER.info(String.valueOf(entity));
		synchronized(this) {
			final int rows = inventoryMapper.confirm(entity);


			if (rows != 1) {
				LOGGER.error("确认库存操作失败！");
			}
		}
		return true;
	}
}
