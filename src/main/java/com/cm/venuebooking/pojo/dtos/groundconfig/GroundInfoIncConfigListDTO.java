package com.cm.venuebooking.pojo.dtos.groundconfig;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xwangs
 * @create 2020-05-11 16:09
 * @description
 */
public class GroundInfoIncConfigListDTO extends GroundInfoDTO{

    @ApiModelProperty(name = "configSplitDTOS", value = "场次安排实体类列表")
    private List<GroundConfigSplitDTO> configSplitDTOS;

    public List<GroundConfigSplitDTO> getConfigSplitDTOS() {
        return configSplitDTOS;
    }

    public void setConfigSplitDTOS(List<GroundConfigSplitDTO> configSplitDTOS) {
        this.configSplitDTOS = configSplitDTOS;
    }
}
