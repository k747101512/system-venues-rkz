package com.cm.venuebooking.dao.groundbooking;

import com.cm.venuebooking.pojo.dtos.bookingorder.GroundBookingInfoDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.GroundBookingItemDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.MyTicketListDTO;
import com.cm.venuebooking.pojo.dtos.bookingorder.VenueProjectDTO;
import com.cm.venuebooking.pojo.dtos.groundinfo.GroundItemDTO;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoOwDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 场馆订单数据层
 * @author xwangs
 * @create 2020-06-04 21:53
 * @description
 */
@Repository
public interface IGroundBookingDao {

    /**
     * 管理人所有场馆列表
     * @param param
     * @return
     */
    List<VenuesInfoOwDTO> listVenuesInfoOw(Map<String, Object> param);


    /**
     * 查询场馆下所有订单
     * @param params
     * @return
     */
    List<GroundBookingInfoDTO> listPageBookingOrder(Map<String, Object> params);


    /**
     * 保存预订订单
     * @param param
     */
    void saveBookingInfo(Map<String, Object> param);

    /**
     * 查询我的订单列表
     * @param params
     * @return
     */
    List<MyTicketListDTO> listPageMyTicket(Map<String, Object> params);

    /**
     * 场馆项目场地信息
     * @param venuesProjectId 项目主键
     * @return
     */
    VenueProjectDTO getVenueFromProject(String venuesProjectId);

    /**
     *
     * @param param
     */
    void saveBookingItem(Map<String, Object> param);

    /**
     * 根据时间和订单ID查询预订项
     * @param param
     * @return
     */
    List<GroundItemDTO> getItemByDateTime(Map<String, Object> param);

    /**
     * 查询订单
     * @param params
     * @return
     */
    GroundBookingInfoDTO getBookingInfo(Map<String, Object> params);

    /**
     * 查询我的预订项
     * @param params
     * @return
     */
    List<GroundBookingItemDTO> listMyBookingItem(Map<String, Object> params);

    /**
     * 取消我的订单
     * @param params
     */
    void removeMyTicket(Map<String, Object> params);

    /**
     * 取消我的订单中所有预订的场次时段
     * @param params
     */
    void removeMyItemTicket(Map<String, Object> params);

    /**
     * 取消订单下某个预订场次
     * @param params
     */
    void removeMyTicketItem(Map<String, Object> params);

}
