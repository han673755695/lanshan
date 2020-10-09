package com.han.lanshan.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.han.lanshan.system.common.GlobalStatic;
import com.han.lanshan.system.common.ReturnData;
import com.han.lanshan.system.utils.ImageUtils;
import com.han.lanshan.system.utils.UUIDUtils;

/**
 * 上传类
 */
@Controller
@RequestMapping(value = "/lanshan/{businessId}/file")
public class FileUploadController {

    @RequestMapping(value = "/logoupload", method = RequestMethod.POST)
    @ResponseBody
    public ReturnData logoUpload(HttpServletRequest request, @PathVariable String businessId) throws IOException {
    	ReturnData returnDatas = ReturnData.getSuccess();
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        List<Map<String, String>> fileList = uploadFile(multiRequest, businessId);
        returnDatas.setData(fileList);
        return returnDatas;
    }

    private List<Map<String, String>> uploadFile(MultipartHttpServletRequest multiRequest, String businessId)
            throws IOException {
        Iterator<String> iter = multiRequest.getFileNames();
        List<Map<String, String>> fileList = new ArrayList<>();
        while (iter.hasNext()) {
            MultipartFile tempFile = multiRequest.getFile(iter.next());
            int lastSplit = tempFile.getOriginalFilename().lastIndexOf('.');
            String prefix = tempFile.getOriginalFilename().substring(lastSplit + 1);
            String fileName = tempFile.getOriginalFilename().substring(0, lastSplit);
            String path = "/upload/kindergarten/" + businessId + "/" + UUIDUtils.getUUID() + "." + prefix;
            File file = new File(GlobalStatic.rootDir + path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                boolean createNewFile = file.createNewFile();
                if (!createNewFile) {
                    return null;
                }
            }
            tempFile.transferTo(file);
            Map<String, String> uploadFileMap = new HashMap<>();
            uploadFileMap.put("name", fileName);
            uploadFileMap.put("path", path);
            uploadFileMap.put("size", file.getTotalSpace() + "");
            uploadFileMap.put("prefix", prefix);
            fileList.add(uploadFileMap);
            
            ImageUtils.resize(file, 720, true);
        }
        return fileList;
    }
}
