package com.example.hospital.api.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DoctorDao {

    public ArrayList<HashMap> searchByPage(Map Param);

    public long searchCount(Map param);

    public HashMap searchContent(int id);
}




