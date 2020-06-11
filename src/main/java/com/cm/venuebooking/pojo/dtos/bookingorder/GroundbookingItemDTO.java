package com.cm.venuebooking.pojo.dtos.bookingorder;

import io.swagger.annotations.ApiModelProperty;

/**
 * 所选预订时段
 * @author xwangs
 * @create 2020-06-11 21:31
 * @description
 */
public class GroundbookingItemDTO {

    @ApiModelProperty(name = "booking_item_id", value = "主键")
    private String bookingItemId;
    @ApiModelProperty(name = "booking_info_id", value = "预订订单ID")
    private String bookingInfoId;
    @ApiModelProperty(name = "ground_item_id", value = "预定项ID")
    private String groundItemId;
    @ApiModelProperty(name = "booking_order_date", value = "所定日期")
    private String bookingOrderDate;
    @ApiModelProperty(name = "time_str", value = "时间始")
    private String timeStr;
    @ApiModelProperty(name = "time_end", value = "时间止")
    private String timeEnd;
    @ApiModelProperty(name = "price", value = "价格")
    private String price;
    @ApiModelProperty(name = "arrive_type", value = "到场状态(0未到场1已到场2已过期)")
    private String arriveType;
    @ApiModelProperty(name = "resason", value = "预留")
    private String resason;

    public String getBookingItemId() {
        return bookingItemId == null ? "" : bookingItemId;
    }

    public void setBookingItemId(String bookingItemId) {
        this.bookingItemId = bookingItemId;
    }

    public String getBookingInfoId() {
        return bookingInfoId == null ? "" : bookingInfoId;
    }

    public void setBookingInfoId(String bookingInfoId) {
        this.bookingInfoId = bookingInfoId;
    }

    public String getGroundItemId() {
        return groundItemId == null ? "" : groundItemId;
    }

    public void setGroundItemId(String groundItemId) {
        this.groundItemId = groundItemId;
    }

    public String getBookingOrderDate() {
        return bookingOrderDate == null ? "" : bookingOrderDate;
    }

    public void setBookingOrderDate(String bookingOrderDate) {
        this.bookingOrderDate = bookingOrderDate;
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

    public String getPrice() {
        return price == null ? "" : price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArriveType() {
        return arriveType == null ? "" : arriveType;
    }

    public void setArriveType(String arriveType) {
        this.arriveType = arriveType;
    }

    public String getResason() {
        return resason == null ? "" : resason;
    }

    public void setResason(String resason) {
        this.resason = resason;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"bookingItemId\":\"")
                .append(bookingItemId).append('\"');
        sb.append(",\"bookingInfoId\":\"")
                .append(bookingInfoId).append('\"');
        sb.append(",\"groundItemId\":\"")
                .append(groundItemId).append('\"');
        sb.append(",\"bookingOrderDate\":\"")
                .append(bookingOrderDate).append('\"');
        sb.append(",\"timeStr\":\"")
                .append(timeStr).append('\"');
        sb.append(",\"timeEnd\":\"")
                .append(timeEnd).append('\"');
        sb.append(",\"price\":\"")
                .append(price).append('\"');
        sb.append(",\"arriveType\":\"")
                .append(arriveType).append('\"');
        sb.append(",\"resason\":\"")
                .append(resason).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
