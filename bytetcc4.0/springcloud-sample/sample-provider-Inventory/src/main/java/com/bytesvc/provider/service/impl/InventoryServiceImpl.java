package com.bytesvc.provider.service.impl;/**
 * Created by William on 2018/10/24.
 */

import com.bytesvc.provider.dto.InventoryDTO;
import com.bytesvc.provider.entity.InventoryDO;
import com.bytesvc.provider.mapper.InventoryMapper;
import com.bytesvc.provider.service.InventoryService;
import org.bytesoft.compensable.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br>
 * Description: InventoryServiceImpl<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : WangLei<br>
 * Date       : 2018/10/24 11:10<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018/10/24             WangLei         新增              1001<br>
 ********************************************************************/
@Service("inventoryService")
@Compensable(
        interfaceClass = InventoryService.class
        , confirmableKey = "inventoryServiceConfirm"
        , cancellableKey = "inventoryServiceCancel"
)
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);
    private final InventoryMapper inventoryMapper;

    @Autowired(required = false)
    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean decrease(InventoryDTO inventoryDTO) throws Exception {
        LOGGER.info("==========springcloud调用扣减库存decrease    try方法===========");
        InventoryDO entity = new InventoryDO();
        entity.setExchangeInventory(inventoryDTO.getCount());
        entity.setProductId(inventoryDTO.getProductId());
        synchronized(this) {
            final int decrease = inventoryMapper.decrease(entity);
            if (decrease != 1) {
                LOGGER.error("库存不足");
                throw new Exception("库存不足");
            }
        }
        return true;
    }
}
