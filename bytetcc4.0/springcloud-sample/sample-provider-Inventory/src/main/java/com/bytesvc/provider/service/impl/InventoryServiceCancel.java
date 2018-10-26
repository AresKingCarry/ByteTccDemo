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

@Service("inventoryServiceCancel")
public class InventoryServiceCancel implements InventoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceCancel.class);

	private final InventoryMapper inventoryMapper;

	@Autowired(required = false)
	public InventoryServiceCancel(InventoryMapper inventoryMapper) {
		this.inventoryMapper = inventoryMapper;
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean decrease(InventoryDTO inventoryDTO)  {
		LOGGER.info("==========Springcloud调用扣减库存取消方法=cancel方法==========");
//        final InventoryDO entity = inventoryMapper.findByProductId(inventoryDTO.getProductId());
//        entity.setTotalInventory(entity.getTotalInventory() + inventoryDTO.getCount());
//        entity.setLockInventory(entity.getLockInventory() - inventoryDTO.getCount());

		InventoryDO entity = new InventoryDO();
		entity.setExchangeInventory(inventoryDTO.getCount());
		entity.setProductId(inventoryDTO.getProductId());
		synchronized(this) {
			int rows = inventoryMapper.cancel(entity);
			if (rows != 1) {
				LOGGER.error("取消库存操作失败！");
			}
		}
		return true;
	}
}
