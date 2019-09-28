package com.han.lanshan.system.utils;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.han.lanshan.system.common.ReturnData;

@Controller
@RequestMapping("/admin/upload")
public class UploadUtils {

	
	/**
	  * 上传单张或多张图片
	 * @param file
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping("/uploadFile")
	@ResponseBody
	public ReturnData uploadFile(MultipartFile[] file, Model model, HttpServletRequest request) {
		ReturnData success = ReturnData.getSuccess();
		try {
			if (file.length == 0) {
				throw new RuntimeException("文件不能为空");
			}

			String realPath = request.getServletContext().getRealPath("/");
			for (MultipartFile multipartFile : file) {
				String fileName = multipartFile.getOriginalFilename(); // 文件名
				String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
				fileName = UUID.randomUUID() + suffixName; // 新文件名
				String path = "/upload/" + fileName;
				File dest = new File(realPath + path);
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();
				}
				multipartFile.transferTo(dest);
				System.out.println("path: " + path);
				success.setData(path);
			}
		} catch (Exception e) {
			success.setStatus(ReturnData.ERROR);
			e.printStackTrace();
		}
		return success;
	}
	
	
}
