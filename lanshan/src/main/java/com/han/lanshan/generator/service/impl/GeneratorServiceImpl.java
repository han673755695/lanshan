package com.han.lanshan.generator.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.han.lanshan.generator.dao.GeneratorMapper;
import com.han.lanshan.generator.service.IGeneratorService;

@Service
@Transactional
public class GeneratorServiceImpl implements IGeneratorService {

	@Autowired
	private GeneratorMapper generatorMapper;
	
	@Override
	public List<Map<String, String>> selectColumnsByTableName(String tableName) {
		
		return generatorMapper.selectColumnsByTableName(tableName);
	}

}
