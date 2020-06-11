package com.cm.venuebooking.pojo.vos.groundbooking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 预订订单实体类
 * @author xwangs
 * @create 2020-05-13 10:30
 * @description
 */
@ApiModel
public class GroundTicketVO {

    @ApiModelProperty(name = "smsCode", value = "验证码")
    private String smsCode;
    @ApiModelProperty(name = "phoneNumber", value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(name = "venuesProjectId", value = "项目ID")
    private String venuesProjectId;
    @ApiModelProperty(name = "bookingItemList", value = "预订日期和时刻")
    private List<GroundTicketItemVO> bookingItemList;

    public String getSmsCode() {
        return smsCode == null ? "" : smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVenuesProjectId() {
        return venuesProjectId == null ? "" : venuesProjectId;
    }

    public void setVenuesProjectId(String venuesProjectId) {
        this.venuesProjectId = venuesProjectId;
    }

    public List<GroundTicketItemVO> getBookingItemList() {
        if (bookingItemList == null) {
            return new ArrayList<>();
        }
        return bookingItemList;
    }

    public void setBookingItemList(List<GroundTicketItemVO> bookingItemList) {
        this.bookingItemList = bookingItemList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"smsCode\":\"")
                .append(smsCode).append('\"');
        sb.append(",\"phoneNumber\":\"")
                .append(phoneNumber).append('\"');
        sb.append(",\"venuesProjectId\":\"")
                .append(venuesProjectId).append('\"');
        sb.append(",\"bookingItemList\":")
                .append(bookingItemList);
        sb.append('}');
        return sb.toString();
    }
}
