package com.ctw.workstation.rackAsset;

import com.ctw.workstation.rackAsset.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class RackAssetRepository implements PanacheRepositoryBase<RackAsset, UUID> {}