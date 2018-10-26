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

package com.bytesvc.provider.controller;

import com.bytesvc.provider.dto.InventoryDTO;
import com.bytesvc.provider.service.InventoryService;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xiaoyu
 */
@RestController
@RequestMapping("/inventory")
@Compensable(interfaceClass = InventoryService.class)
@SuppressWarnings("all")
public class InventoryController {

    @Autowired
    @Qualifier("inventoryService")
    private  InventoryService inventoryService;

//    @Autowired
//    public InventoryController(InventoryService inventoryService) {
//        this.inventoryService = inventoryService;
//    }

    @RequestMapping("/decrease")
    @Transactional
    public Boolean decrease(@RequestBody InventoryDTO inventoryDTO) throws Exception {
        Boolean flag = false;
        try {
            flag =  inventoryService.decrease(inventoryDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("【Inventory错误】"+e.getMessage());
        }
        return flag;
    }



}
