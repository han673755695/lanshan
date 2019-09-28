package com.han.lanshan.system.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {
    /**
     * 将汉字转换为全拼,字符串中的非汉字字符不转换,其中汉字将转为全小写的全拼</br>
     * 如果{@code src}为"",则返回"",若{@code src}为null,则抛出 NullPointerException
     * @param src
     * 			指定的字符
     * @return 汉字字符对应的拼音字符串
     * <p>如:
     * <p><blockquote><pre>
     * 		System.out.println(StringUtils.getPingYin("测试ce试"));
     * 		输出:ceshiceshi
     * </pre></blockquote>
     * */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else
                    t4 += Character.toString(t1[i]);
            }
            // System.out.println(t4);
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 获取中文的首字母,其中非汉字字符保持不变(汉字将转为全小写的全拼)</br>
     * 如果{@code src}为"",则返回"",若{@code src}为null,则抛出 NullPointerException
     * @param str
     * 		指定字符串
     * @return 目标参数的中文首字母
     * <p>如:
     * <p><blockquote><pre>
     * 		getPingYin("a=大b"); 	返回    "a=db"
     * 		System.out.println(StringUtils.getPinYinHeadChar("测试ce试"));
     * 		输出:csces
     * </pre></blockquote>
     * */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

}
