package com.example.hospital.api.db.dao;

import com.example.hospital.api.db.pojo.DoctorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DoctorDao {

    public ArrayList<HashMap> searchByPage(Map Param);

    public long searchCount(Map param);

    public HashMap searchContent(int id);

    public void updatePhoto(Map param);

    @Transactional
    public void insert(DoctorEntity entity);

    public Integer searchIdByUuid(String uuid);
}




