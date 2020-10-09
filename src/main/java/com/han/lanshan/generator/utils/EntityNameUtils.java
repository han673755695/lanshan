package com.han.lanshan.generator.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * string tool
 *
 * @author xuxueli 2018-05-02 20:43:25
 */
public class EntityNameUtils {

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        //2019-2-10 解决StringUtils.lowerCaseFirst潜在的NPE异常@liutf
        return (str!=null&&str.length()>1)?str.substring(0, 1).toLowerCase() + str.substring(1):"";
    }

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
    
    
    /**
     * 
      * 描述：   根据数据库类型返回实体类类型
      * 创建人：HYD
      * 创建时间：2019年9月13日 下午4:42:15   
      * 修改人：HYD
      * 修改时间：2019年9月13日 下午4:42:15
      * 修改备注：   
     * @param databaseFileType
     * @return
     * @version
     */
    public static String entryType(String databaseFileType) {
    	
        String fieldClass = Object.class.getSimpleName();
        if (databaseFileType.equals("int") || databaseFileType.equals("smallint")) {
            fieldClass = Integer.class.getSimpleName();
        } else if (databaseFileType.equals("bigint")) {
            fieldClass = Long.class.getSimpleName();
        } else if (databaseFileType.equals("float")) {
            fieldClass = Float.class.getSimpleName();
        } else if (databaseFileType.equals("double")) {
            fieldClass = Double.class.getSimpleName();
        } else if (databaseFileType.equals("datetime") || databaseFileType.equals("timestamp") || databaseFileType.equals("date")) {
            fieldClass = Date.class.getSimpleName();
        } else if (databaseFileType.equals("varchar") || databaseFileType.equals("text")|| databaseFileType.equals("char")
                || databaseFileType.equals("clob")||databaseFileType.equals("blob")||databaseFileType.equals("json")) {
            fieldClass = String.class.getSimpleName();
        } else if (databaseFileType.equals("decimal")) {
        	fieldClass = BigDecimal.class.getSimpleName();
        } else if (databaseFileType.equals("boolean")|| databaseFileType.equals("tinyint") ) {
            //20190910 MOSHOW.K.ZHENG 新增对boolean的处理（感谢@violinxsc的反馈）以及修复tinyint类型字段无法生成boolean类型问题（感谢@hahaYhui的反馈）
            fieldClass = Boolean.class.getSimpleName();
        } else {
            fieldClass = String.class.getSimpleName();
        }
    	
        return fieldClass;
    }

    public static void main(String[] args) {
    	String underlineToCamelCase = underlineToCamelCase("user_id");
    	System.out.println(underlineToCamelCase);
    }
}
