package com.han.lanshan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.lanshan.system.utils.JsonUtils;
import com.han.lanshan.system.utils.PinyinUtils;
import com.han.lanshan.system.utils.SecUtils;
import com.han.lanshan.system.utils.UUIDUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanshanApplicationTests {

	@Test
	public void contextLoads() {

		String jsonStr = "{name:\"Ravi Tamada\",email:\"ravi8x@gmail.com\",phone:{home:\"08947 000000\",mobile:\"9999999999\"}}";

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map readValue = objectMapper.readValue(jsonStr, Map.class);
			System.out.println(readValue);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String uuid = UUIDUtils.getUUID();
			System.out.println(uuid);
		}
		
		String md5With32Bit = SecUtils.encoderByMd5With32Bit("123");
		System.out.println("md5With32Bit: " + md5With32Bit);
		
	}

}
