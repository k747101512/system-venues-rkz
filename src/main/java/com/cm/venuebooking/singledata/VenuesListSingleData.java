package com.cm.venuebooking.singledata;

import com.cm.venuebooking.dao.venuesinfo.IVenuesInfoDao;
import com.cm.venuebooking.pojo.dtos.venuesinfo.VenuesInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xwangs
 * @create 2020-06-13 17:39
 * @description
 */
public class VenuesListSingleData {

    private static VenuesListSingleData instance = new VenuesListSingleData();

    private static List<VenuesInfoDTO> venuesList = null;

    private VenuesListSingleData(){}

    public static VenuesListSingleData getInstance(){
        return instance;
    }

    public List<VenuesInfoDTO> getVenuesList(IVenuesInfoDao venuesInfoDao){
        if(venuesList == null || venuesList.size() ==0){
            venuesList = venuesInfoDao.listVenuesByKeyWords(null);
        }
        return venuesList;
    }

    public void setVenuesList(List<VenuesInfoDTO> list){
        venuesList = list;
    }
}
