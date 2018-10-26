/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytesvc.consumer.service.impl;


import com.bytesvc.consumer.entity.Order;
import com.bytesvc.consumer.enums.OrderStatusEnum;
import com.bytesvc.consumer.mapper.OrderMapper;
import com.bytesvc.consumer.service.PaymentService;
import com.bytesvc.feign.service.AccountClient;
import com.bytesvc.feign.service.InventoryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * PaymentServiceImpl.
 *
 * @author xiaoyu
 */
@Service("paymentServiceCancel")
@SuppressWarnings("all")
public class PaymentServiceCancel implements PaymentService {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceCancel.class);

    private final OrderMapper orderMapper;

    private final AccountClient accountClient;

    private final InventoryClient inventoryClient;

    @Autowired(required = false)
    public PaymentServiceCancel(OrderMapper orderMapper,
                                AccountClient accountClient,
                                InventoryClient inventoryClient) {
        this.orderMapper = orderMapper;
        this.accountClient = accountClient;
        this.inventoryClient = inventoryClient;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean makePayment(Order order) {
        LOGGER.info("=========进行订单cancel操作================");
        order.setStatus(OrderStatusEnum.PAY_FAIL.getCode());
        return  orderMapper.update(order)>0;
    }




}
