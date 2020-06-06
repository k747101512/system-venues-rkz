package com.cm.venuebooking.pojo.vos.groundbooking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xwangs
 * @create 2020-05-13 10:30
 * @description
 */
@ApiModel
public class GroundBookingInfoVO {

    @ApiModelProperty(name = "groundInfoId", value = "场地主键")
    private String groundInfoId;
    @ApiModelProperty(name = "userName", value = "预订人姓名")
    private String userName;
    @ApiModelProperty(name = "idCardNumber", value = "身份证号")
    private String idCardNumber;
    @ApiModelProperty(name = "phoneNumber", value = "联系电话")
    private String phoneNumber;
    @ApiModelProperty(name = "bookingOrderDate", value = "场次预订所在日期")
    private String bookingOrderDate;
    @ApiModelProperty(name = "timeStr", value = "预订时段开始")
    private String timeStr;
    @ApiModelProperty(name = "timeEnd", value = "预订时段截止")
    private String timeEnd;
    @ApiModelProperty(name = "singlePrice", value = "价格")
    private String singlePrice;

    public String getGroundInfoId() {
        return groundInfoId == null ? "" : groundInfoId;
    }

    public void setGroundInfoId(String groundInfoId) {
        this.groundInfoId = groundInfoId;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNumber() {
        return idCardNumber == null ? "" : idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTimeStr() {
        return timeStr == null ? "" : timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getTimeEnd() {
        return timeEnd == null ? "" : timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getSinglePrice() {
        return singlePrice == null ? "" : singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getBookingOrderDate() {
        return bookingOrderDate == null ? "" : bookingOrderDate;
    }

    public void setBookingOrderDate(String bookingOrderDate) {
        this.bookingOrderDate = bookingOrderDate;
    }
}
