package com.han.lanshan.generator.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.han.lanshan.generator.constant.Constant;
import com.han.lanshan.generator.service.IGeneratorService;
import com.han.lanshan.generator.utils.EntityNameUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
@RequestMapping("/generator")
public class GeneratorController {

	@Autowired
	private IGeneratorService generatorService;
	@Autowired
    private Configuration configuration;
	
	@ResponseBody
	@RequestMapping("/createFile")
	public String createFile(HttpServletRequest request) {
		
		try {
			String tableName = request.getParameter("tableName");
			String tableNamePrefix = request.getParameter("tableNamePrefix");
			
			List<Map<String, String>> columnsList = generatorService.selectColumnsByTableName(tableName);
			for (Map<String, String> map : columnsList) {
				String underlineToCamelCase = EntityNameUtils.underlineToCamelCase(map.get("COLUMN_NAME"));
				//实体类字段名称
				map.put("entryClumu", underlineToCamelCase);
				
				//根据数据库字段类型获取实体类来行
				String entryType = EntityNameUtils.entryType(map.get("DATA_TYPE"));
				map.put("fieldClass", entryType);
			}
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("columnsList", columnsList);
			dataMap.put("prefix", Constant.templeteStringEnum.prefix.getValue());
			dataMap.put("suffix", Constant.templeteStringEnum.suffix.getValue());
			dataMap.put("jinghao", Constant.templeteStringEnum.jinghao.getValue());
			dataMap.put("daolefu", Constant.templeteStringEnum.daolefu.getValue());
			dataMap.put("jing", Constant.templeteStringEnum.jing.getValue());
			dataMap.put("aite", Constant.templeteStringEnum.aite.getValue());
			//String tableName = columnsList.get(0).get("TABLE_NAME");
			//数据库表名
			dataMap.put("tableName", tableName);
			//包名
			dataMap.put("package", Constant.templeteStringEnum.packageName.getValue());
			//实体类名称	//先去掉数据库名前缀admin_,然后把  _  转化为驼峰命名法,再转化首字母大写
			String upperCaseFirst = EntityNameUtils.upperCaseFirst(EntityNameUtils.underlineToCamelCase(tableName.replace(tableNamePrefix, "")));
			String lowerCaseFirst = EntityNameUtils.lowerCaseFirst(EntityNameUtils.underlineToCamelCase(tableName.replace(tableNamePrefix, "")));
			//实体类名称
			dataMap.put("entryName", upperCaseFirst);
			
			dataMap.put("lowerCaseFirst", lowerCaseFirst);
			
			//生成mapper.xml文件
			createTemplete(dataMap, Constant.GeneratorEnum.mapperXml.getValue(), Constant.createFilePathEnum.mapper文件.getValue(), upperCaseFirst + "Mapper.xml");
			//生成实体类文件
			createTemplete(dataMap, Constant.GeneratorEnum.实体类.getValue(), Constant.createFilePathEnum.entry层.getValue(), upperCaseFirst + ".java");
			//生成实体类文件
			createTemplete(dataMap, Constant.GeneratorEnum.dao层.getValue(), Constant.createFilePathEnum.dao层.getValue(), upperCaseFirst + "Mapper.java");
			//生成service接口文件
			createTemplete(dataMap, Constant.GeneratorEnum.service接口.getValue(), Constant.createFilePathEnum.service层.getValue(), "I" + upperCaseFirst + "Service.java");
			//生成service实现类文件
			createTemplete(dataMap, Constant.GeneratorEnum.service实现.getValue(), Constant.createFilePathEnum.service层.getValue() + "impl/", upperCaseFirst + "ServiceImpl.java");
			//生成Controller类文件
			createTemplete(dataMap, Constant.GeneratorEnum.controller层.getValue(), Constant.createFilePathEnum.controller层.getValue(), upperCaseFirst + "Controller.java");
			//生成list类列表文件
			createTemplete(dataMap, Constant.GeneratorEnum.列表.getValue(), Constant.createFilePathEnum.list层.getValue() + lowerCaseFirst + "/", lowerCaseFirst + "List.html");
			//生成crud类列表文件
			createTemplete(dataMap, Constant.GeneratorEnum.新增.getValue(), Constant.createFilePathEnum.list层.getValue() + lowerCaseFirst + "/", lowerCaseFirst + "Cru.html");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		return "ok";
	}

	/**
	 * 
	  * 描述：   
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午4:49:16   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午4:49:16
	  * 修改备注：   
	 * @param dataMap	数据库表字段信息
	 * @param upperCaseFirst	首字母大写
	 * @param templetePath	模板路径
	 * @param fileSuffix	生成的文件后缀
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @version
	 */
	private void createTemplete(Map<String, Object> dataMap, String templetePath, String createPath, String fileSuffix)
			throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		Template template = configuration.getTemplate(templetePath);
		String path = createPath + fileSuffix;
		File upload = new File(path);
		if(!upload.getParentFile().exists()) {
			upload.getParentFile().mkdirs();
		}
		upload.createNewFile();
		FileWriter fileWriter = new FileWriter(upload);
		
		template.process(dataMap, fileWriter);
		fileWriter.close();
	}
	
	
	
}
