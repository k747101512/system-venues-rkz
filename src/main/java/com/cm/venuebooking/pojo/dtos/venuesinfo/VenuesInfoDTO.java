package com.cm.venuebooking.pojo.dtos.venuesinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @ClassName: VenuesInfoDTO
 * @Description: 场馆信息表
 * @Author: WenG
 * @Date: 2020-04-22 11:46
 * @Version: 1.0
 **/
@ApiModel
public class VenuesInfoDTO {

    @ApiModelProperty(name = "venuesInfoId", value = "主键")
    private String venuesInfoId;
    @ApiModelProperty(name = "venueCity", value = "所属城市")
    private String venueCity;
    @ApiModelProperty(name = "venueOtherService", value = "场馆其他服务")
    private String venueOtherService;
    @ApiModelProperty(name = "venueCharge", value = "arrive直接到达booking按场次预订ticket按门票预订")
    private String venueCharge;
    @ApiModelProperty(name = "venueRoute", value = "路线")
    private String venueRoute;
    @ApiModelProperty(name = "venueArea", value = "所属城区")
    private String venueArea;
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;
    @ApiModelProperty(name = "venueTelephone", value = "场馆联系方式")
    private String venueTelephone;
    @ApiModelProperty(name = "venueName", value = "场馆名称")
    private String venueName;
    @ApiModelProperty(name = "venuePosition", value = "详细地址")
    private String venuePosition;
    @ApiModelProperty(name = "venueDescription", value = "场馆介绍")
    private String venueDescription;
    @ApiModelProperty(name = "venueOpenType", value = "场馆开闭状态")
    private Integer venueOpenType;
    @ApiModelProperty(name = "businessHours", value = "营业时间说明")
    private String businessHours;
    @ApiModelProperty(name = "venuePlan", value = "场馆平面图")
    private String venuePlan;
    @ApiModelProperty(name = "venuePanorama", value = "场馆全景图")
    private String venuePanorama;
    @ApiModelProperty(name = "bookingTips", value = "预订提示")
    private String bookingTips;
    @ApiModelProperty(name = "venueService", value = "场馆服务")
    private String venueService;
    @ApiModelProperty(name = "venueType", value = "场馆类型")
    private String venueType;
    @ApiModelProperty(name = "apart", value = "与我相距")
    private String apart;
    @ApiModelProperty(name = "viewCount", value = "浏览量")
    private String viewCount;

    public String getVenuesInfoId() {
        return venuesInfoId == null ? "" : venuesInfoId;
    }

    public void setVenuesInfoId(String venuesInfoId) {
        this.venuesInfoId = venuesInfoId;
    }
    public String getVenueCity() {
        return venueCity == null ? "" : venueCity;
    }

    public void setVenueCity(String venueCity) {
        this.venueCity = venueCity;
    }

    public String getVenueOtherService() {
        return venueOtherService == null ? "" : venueOtherService;
    }

    public void setVenueOtherService(String venueOtherService) {
        this.venueOtherService = venueOtherService;
    }

    public String getVenueCharge() {
        return venueCharge == null ? "" : venueCharge;
    }

    public void setVenueCharge(String venueCharge) {
        this.venueCharge = venueCharge;
    }

    public String getVenueRoute() {
        return venueRoute == null ? "" : venueRoute;
    }

    public void setVenueRoute(String venueRoute) {
        this.venueRoute = venueRoute;
    }

    public String getVenueArea() {
        return venueArea == null ? "" : venueArea;
    }

    public void setVenueArea(String venueArea) {
        this.venueArea = venueArea;
    }

    public String getVenueTelephone() {
        return venueTelephone == null ? "" : venueTelephone;
    }

    public void setVenueTelephone(String venueTelephone) {
        this.venueTelephone = venueTelephone;
    }

    public String getVenueName() {
        return venueName == null ? "" : venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenuePosition() {
        return venuePosition == null ? "" : venuePosition;
    }

    public void setVenuePosition(String venuePosition) {
        this.venuePosition = venuePosition;
    }

    public String getVenueDescription() {
        return venueDescription == null ? "" : venueDescription;
    }

    public void setVenueDescription(String venueDescription) {
        this.venueDescription = venueDescription;
    }

    public Integer getVenueOpenType() {
        return venueOpenType == null ? 0 : venueOpenType;
    }

    public void setVenueOpenType(Integer venueOpenType) {
        this.venueOpenType = venueOpenType;
    }

    public String getBusinessHours() {
        return businessHours == null ? "" : businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getVenuePlan() {
        return venuePlan == null ? "" : venuePlan;
    }

    public void setVenuePlan(String venuePlan) {
        this.venuePlan = venuePlan;
    }

    public String getVenuePanorama() {
        return venuePanorama == null ? "" : venuePanorama;
    }

    public void setVenuePanorama(String venuePanorama) {
        this.venuePanorama = venuePanorama;
    }

    public String getBookingTips() {
        return bookingTips == null ? "" : bookingTips;
    }

    public void setBookingTips(String bookingTips) {
        this.bookingTips = bookingTips;
    }

    public String getVenueService() {
        return venueService == null ? "" : venueService;
    }

    public void setVenueService(String venueService) {
        this.venueService = venueService;
    }

    public String getVenueType() {
        return venueType == null ? "" : venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public String getApart() {
        return apart == null ? "0" : apart;
    }

    public void setApart(String apart) {
        this.apart = apart;
    }

    public String getViewCount() {
        return viewCount == null ? "0" : viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLongitude() {
        return longitude == null ? "" : longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude == null ? "" : latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
