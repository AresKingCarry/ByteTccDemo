package com.bytesvc.consumer.service.impl;/**
 * Created by William on 2018/10/24.
 */

import com.bytesvc.consumer.entity.Order;
import com.bytesvc.consumer.enums.OrderStatusEnum;
import com.bytesvc.consumer.mapper.OrderMapper;
import com.bytesvc.consumer.service.OrderService;
import com.bytesvc.consumer.service.PaymentService;
import com.bytesvc.util.IdWorkerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <br>
 * Description: OrderServiceImpl<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : WangLei<br>
 * Date       : 2018/10/24 10:31<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018/10/24             WangLei         新增              1001<br>
 ********************************************************************/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderMapper orderMapper;


    private final PaymentService paymentService;

    @Autowired(required = false)
    public OrderServiceImpl(OrderMapper orderMapper, PaymentService paymentService) {
        this.orderMapper = orderMapper;
        this.paymentService = paymentService;
    }

    public String orderPay(Integer count, BigDecimal amount) {
        final Order order = buildOrder(count, amount);
        final int rows = orderMapper.save(order);
        Boolean flag =false ;
        if (rows > 0) {
            try {
                flag  = paymentService.makePayment(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag?"success":"failed";
    }


    private Order buildOrder(Integer count, BigDecimal amount) {
        LOGGER.debug("构建订单对象");
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setNumber(IdWorkerUtils.getInstance().buildPartNumber());
        //demo中的表里只有商品id为 1的数据
        order.setProductId("1");
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中 表里面存的用户id为10000
        order.setUserId("10000");
        return order;
    }
}
