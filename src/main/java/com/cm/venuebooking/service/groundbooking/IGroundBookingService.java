package com.cm.venuebooking.service.groundbooking;

import com.alibaba.fastjson.JSONObject;
import com.cm.common.exception.SaveException;
import com.cm.common.exception.SearchException;
import com.cm.common.pojo.ListPage;
import com.cm.common.result.SuccessResult;
import com.cm.common.result.SuccessResultData;
import com.cm.common.result.SuccessResultList;
import com.cm.venuebooking.pojo.dtos.groundbooking.GroundBookingInfoListDTO;

import java.util.List;
import java.util.Map;

/**
 * @author xwangs
 * @create 2020-05-01 18:25
 * @description
 */
public interface IGroundBookingService {

    Object listGroundBooking(Map<String, Object> reqParams) throws SearchException;

    SuccessResult saveBookingInfo(Map<String, Object> params) throws SaveException;

    Object getTreeGroundInfo(Map<String, Object> param) throws SearchException;

    SuccessResultData listBookingByDate(Map<String, Object> params) throws SearchException;

    void saveBookingItems(String token,JSONObject jsonObject) throws Exception;

    SuccessResultData listMyBookingOrder(String token) throws SearchException;

    SuccessResultList<List<GroundBookingInfoListDTO>> listPageBookingOrder(ListPage page) throws SearchException;
}
