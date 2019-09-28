package com.han.lanshan.generator.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface GeneratorMapper {
	
	List<Map<String,String>> selectColumnsByTableName(String tableName);

}
