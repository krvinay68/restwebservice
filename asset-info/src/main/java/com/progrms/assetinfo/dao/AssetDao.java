package com.progrms.assetinfo.dao;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.progrms.assetinfo.entities.AssetInfo;
import com.progrms.assetinfo.repo.AssetRepo;

@Component
public class AssetDao {

    @Autowired
    AssetRepo assetRepo;

    public AssetInfo saveData(AssetInfo assetInfo){

        assetInfo.setAssetName(assetInfo.getAssetName());
        assetInfo.setAssetPrice(assetInfo.getAssetPrice());
        AssetInfo asset= assetRepo.save(assetInfo);
        Predicate<AssetInfo> pre= a -> a==null;
        if(pre.test(asset)){                
        }
    
        return asset;
    }

    public List<AssetInfo> getAll(){       
        List<AssetInfo> assetInfo=assetRepo.findAll();
        return assetInfo;
    }
    
}
