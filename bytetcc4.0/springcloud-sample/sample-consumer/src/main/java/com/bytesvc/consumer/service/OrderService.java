package com.bytesvc.consumer.service;/**
 * Created by William on 2018/10/24.
 */

import java.math.BigDecimal;

/**
 * <br>
 * Description: IOrderService<br>
 * Company    : 上海黄豆网络科技有限公司 <br>
 * Author     : WangLei<br>
 * Date       : 2018/10/24 10:27<br>
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号<br>
 * v1.0.0      2018/10/24             WangLei         新增              1001<br>
 ********************************************************************/
public interface OrderService {


    String orderPay(Integer count, BigDecimal amount);
}
