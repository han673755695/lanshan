package com.han.lanshan.generator.constant;

public class Constant {
	
	/**
	 * 	模板路径位置
	 * @author my
	 *
	 */
	public static enum GeneratorEnum{
		新增("/code-generator/ui/layui-update.html"), 列表("/code-generator/ui/layui-list.html"), 
		mapperXml("/code-generator/mybatis/MenuMapper.html"),dao层("/code-generator/jpa/UserMapper.html"),
		service接口("/code-generator/jpa/IUserService.html"),service实现("/code-generator/jpa/UserServiceImpl.html"),
		实体类("/code-generator/jpa/User.html"),controller层("/code-generator/jpa/UserController.html");
		private String value;
		
		GeneratorEnum(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	/**
	 *  生成的文件位置
	 * @author my
	 *
	 */
	public static enum createFilePathEnum{
		mapper文件("D:/templates/mapper/"),controller层("D:/templates/controller/"), 
		service层("D:/templates/service/"), dao层("D:/templates/dao/"),
		entry层("D:/templates/entry/"),list层("D:/templates/ui/");
		private String value;
		
		createFilePathEnum(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 
	  * 描述：   模板特殊符号字符串
	  * 创建人：HYD
	  * 创建时间：2019年9月13日 下午3:21:44   
	  * 修改人：HYD
	  * 修改时间：2019年9月13日 下午3:21:44
	  * 修改备注：   
	 * @version
	 */
	public static enum templeteStringEnum{
		prefix("${"), suffix("}"), jinghao("#{"), daolefu("$"), jing("#"),aite("@"),
		packageName("com.han.lanshan.system"), tableNamePrefix("t_");
		private String value;
		
		templeteStringEnum(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}

}
