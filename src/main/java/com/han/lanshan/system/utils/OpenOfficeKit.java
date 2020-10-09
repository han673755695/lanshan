package com.han.lanshan.system.utils;

import java.io.File;

import javax.annotation.PostConstruct;

import org.jodconverter.DocumentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 
  * 描述：   html转化为excel
  * 创建人：HYD
  * 创建时间：2019年9月13日 下午1:35:59   
  * 修改人：HYD
  * 修改时间：2019年9月13日 下午1:35:59
  * 修改备注：   
 * @version
 */
@Component
public class OpenOfficeKit {

	private static final Logger logger = LoggerFactory.getLogger(OpenOfficeKit.class);

	@Autowired
    private DocumentConverter document;
	
	private static DocumentConverter documentConverter;
	
	@PostConstruct
	public void init() {
		documentConverter = document;
	}
	
	public static void cvtXls(File inputFile, File outputFile) throws Exception {
		documentConverter.convert(inputFile).to(outputFile).execute();
	}

}
