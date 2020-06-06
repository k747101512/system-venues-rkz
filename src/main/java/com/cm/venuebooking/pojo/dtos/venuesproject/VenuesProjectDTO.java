package com.cm.venuebooking.pojo.dtos.venuesproject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @ClassName: VenuesProjectDTO
 * @Description: 场馆项目表
 * @Author: WenG
 * @Date: 2020-04-26 12:07
 * @Version: 1.0
 **/
@ApiModel
public class VenuesProjectDTO {

    @ApiModelProperty(name = "venuesProjectId", value = "主键")
    private String venuesProjectId;
    @ApiModelProperty(name = "venueId", value = "场馆主键")
    private String venueId;
    @ApiModelProperty(name = "projectCategory", value = "项目分类")
    private String projectCategory;
    @ApiModelProperty(name = "projectName", value = "项目名称")
    private String projectName;
    @ApiModelProperty(name = "businessHours", value = "营业时间")
    private String businessHours;
    @ApiModelProperty(name = "projectDescription", value = "项目描述")
    private String projectDescription;
    @ApiModelProperty(name = "facilities", value = "场地设施")
    private String facilities;

    public String getVenuesProjectId() {
        return venuesProjectId == null ? "" : venuesProjectId;
    }

    public void setVenuesProjectId(String venuesProjectId) {
        this.venuesProjectId = venuesProjectId;
    }
    public String getVenueId() {
        return venueId == null ? "" : venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getProjectCategory() {
        return projectCategory == null ? "" : projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public String getBusinessHours() {
        return businessHours == null ? "" : businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getProjectDescription() {
        return projectDescription == null ? "" : projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getFacilities() {
        return facilities == null ? "" : facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getProjectName() {
        return projectName == null ? "" : projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
