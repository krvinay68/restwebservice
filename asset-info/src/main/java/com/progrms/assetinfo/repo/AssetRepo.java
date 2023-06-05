package com.progrms.assetinfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progrms.assetinfo.entities.AssetInfo;

public interface AssetRepo extends JpaRepository<AssetInfo, Long>{
    
}
