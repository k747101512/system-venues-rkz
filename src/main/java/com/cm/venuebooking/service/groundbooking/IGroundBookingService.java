package com.cm.venuebooking.service.groundbooking;

import com.alibaba.fastjson.JSONObject;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.bookingorder.GroundBookingInfoDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.MyTicketDetailDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.MyTicketListDTO;
import com.cm.venuebooking.pojo.dtos.groundinfo.GroundInfoDTO;
import com.cm.venuebooking.pojo.vos.groundbooking.GroundTicketVO;

import java.util.List;
import java.util.Map;

/**
 * 场馆订单接口
 * @author xwangs
 * @create 2020-06-04 21:49
 * @description
 */
public interface IGroundBookingService {

    /**
     * 订单分页列表接口
     * @param page
     * @return
     */
    SuccessResultList listPageBookingOrder(ListPage page);

    /**
     *
     * @param token
     * @param groundTicketVO
     * @return
     * @throws Exception
     */
    SuccessResult saveBookingInfoForApp(String token, GroundTicketVO groundTicketVO) throws Exception;

    /**
     * 查询我的订单列表
     * @param token
     * @param page
     * @return
     */
    SuccessResultList<List<MyTicketListDTO>> listPageMyTicket(String token, ListPage page);


    /**
     * 查询我的订单详情
     * @param token
     * @param params
     * @return
     */
    SuccessResultData<MyTicketDetailDTO> getMyTicketDetail(String token, Map<String, Object> params);

    /**
     * 取消订单(取消所有预订场次)
     * @param token
     * @param groundBookingId
     * @return
     */
    SuccessResult removeMyTicket(String token, String groundBookingId);

    /**
     * 取消订单下某个预订场次
     * @param token
     * @param bookingItemId
     * @param groundBookingId
     * @return
     */
    SuccessResult removeMyTicketItem(String token,String groundBookingId, String bookingItemId);
}
