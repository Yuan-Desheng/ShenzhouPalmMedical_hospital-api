package com.example.hospital.api.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.example.hospital.api.common.PageUtils;
import com.example.hospital.api.db.dao.DoctorDao;
import com.example.hospital.api.exception.HospitalException;
import com.example.hospital.api.service.DoctorService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorDao doctorDao;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = null;
        long count = doctorDao.searchCount(param);
        if (count > 0){
            list = doctorDao.searchByPage(param);
        }else{
            list = new ArrayList<>();
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);
        return pageUtils;
    }

    @Override
    public HashMap searchContent(int id) {
        HashMap map = doctorDao.searchContent(id);
        JSONArray tag = JSONUtil.parseArray(MapUtil.getStr(map, "tag"));
        map.replace("tag", tag);
        return map;
    }

    @Override
    @Transactional
    public void updatePhoto(MultipartFile file, Integer doctorId) {
        try {
            // 医生照片文件名称
            String fileName = "doctor" + doctorId + ".jpg";

            // 创建minio连接
            MinioClient client = new MinioClient.Builder().endpoint(endpoint).credentials(accessKey, secretKey).build();

            // 上传医生照片文件
            client.putObject(PutObjectArgs.builder().bucket("hospital")
                    .object("doctor/" + fileName)
                    .stream(file.getInputStream(), -1, 5 * 1024 * 1024)
                    .contentType("image/jpeg")
                    .build());

            // 更新医生表photo字段
            doctorDao.updatePhoto(new HashMap(){{
                put("id", doctorId);
                put("photo", "/doctor/" + fileName);
            }});
        } catch (Exception e) {
            log.error("保存医生照片失败", e);
            throw new HospitalException("保存医生照片失败");
        }
    }
}
