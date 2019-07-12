package com.cqut.indoor.utills;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class StringUtils {

    public static String randomCode(int number) {
        String verifyCode = "";

        for(int i = 0; i < number; ++i) {
            Random r = new Random();
            verifyCode = verifyCode + String.valueOf(r.nextInt(10));
        }

        return verifyCode;
    }

    public static boolean isContainSql(String str) {
        if(isNullOrNone(str)) {
            return false;
        } else {
            String para = str.toUpperCase();
            return para.contains("SELECT") && para.contains("FROM") || (para.contains("UPDATE") && para.contains("SET") || (para.contains("DELETE") || (para.contains("TRUNCATE") && para.contains("TABLE") || (para.contains("DROP") && para.contains("TABLE") || (para.contains("ALTER") && para.contains("TABLE") || para.contains("ALTER") && para.contains("USER"))))));
        }
    }

    public static boolean isLetter(String str) {
        return (str != null && !"".equals(str) )&& Pattern.matches("^[A-Za-z]+$", str);
    }

    public static int lengthByteInPrepared(String s) {
        int length = 0;
        if(s == null) {
            return length;
        } else {
            for(int i = 0; i < s.length(); ++i) {
                if(s.charAt(i) <= 127) {
                    ++length;
                } else {
                    length += 3;
                }
            }

            return length;
        }
    }

    public static boolean isNullOrNone(String s) {
        return s == null || "".equals(s.trim());
    }

    public static boolean isNullOrNone(Object obj) {
        return obj == null || (obj instanceof String && isNullOrNone((String) obj));
    }


    public static boolean isNullOrNone(String[] ss) {
        if(ss != null && ss.length != 0) {
            for(int i = 0; i < ss.length; ++i) {
                if(ss[i] == null || "".equals(ss[i].trim())) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean isAllNullOrNone(String[] ss) {
        if(ss != null && ss.length != 0) {
            for(int i = 0; i < ss.length; ++i) {
                if(ss[i] != null && !"".equals(ss[i].trim())) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static int countChar(String src, char c) {
        if(isNullOrNone(src)) {
            return 0;
        } else {
            int k = 0;

            for(int i = 0; i < src.length(); ++i) {
                if(src.charAt(i) == c) {
                    ++k;
                }
            }

            return k;
        }
    }

    public static boolean isNumber(String str) {
        return (str != null && !"".equals(str)) && Pattern.matches("^[0-9.]+$", str);
    }


    public static boolean isPhone(String str) {
        return (str != null && !"".equals(str)) && Pattern.matches("^1[0-9.]{10}$", str);
    }

    public static String sensitive(String s){
        StringBuilder sb = new StringBuilder();
        if(org.springframework.util.StringUtils.isEmpty(s)) return s;
        if(isPhone(s)){
            int cpCount = s.codePointCount(0, s.length());
            for (int i = 0; i < cpCount; i++) {
                int index = s.offsetByCodePoints(0, i);
                int cp = s.codePointAt(index);
                String word = !Character.isSupplementaryCodePoint(cp) ? String.valueOf((char)cp) : String.valueOf(cp);
                if(i > 2 && i < 7){
                    sb.append("*");
                }else{
                    sb.append(word);
                }
            }
            return sb.toString();
        }else{
            int l = s.length();
            if(l == 1){
                return "*";
            }
            if(l == 2){
                return s.substring(0,1) + "*";
            }if(l == 3){
                return s.substring(0,1) + "*" + s.substring(2);
            }else {
                int cpCount = s.codePointCount(0, s.length());
                for (int i = 0; i < cpCount; i++) {
                    int index = s.offsetByCodePoints(0, i);
                    int cp = s.codePointAt(index);
                    String word = !Character.isSupplementaryCodePoint(cp) ? String.valueOf((char) cp) : String.valueOf(cp);
                    if (i >= l/3 && i <= l*2/3) {
                        if(isNumberOrLetterOrChinese(word))sb.append("*");
                        else sb.append(word);
                    } else {
                        if(isNumber(word) && i%2 == 1){
                            sb.append("*");
                        }else{
                            sb.append(word);
                        }
                    }
                }
                return sb.toString();
            }
        }
    }

    public static boolean isNumberOrLetterOrChinese(String str) {
        return (str != null && !"".equals(str)) && Pattern.matches("^[0-9A-Za-z\u4e00-\u9fa5]+$", str);
    }

    public static boolean isNumberOrLetter(String str) {
        return (str != null && !"".equals(str)) && Pattern.matches("^[0-9A-Za-z]+$", str);
    }

    public static boolean isNumberOrLetterOrDot(String str) {
        return (str != null && !"".equals(str)) && Pattern.matches("^[.0-9A-Za-z]+$", str);
    }


    public static String toString(Object obj){
        return ToStringBuilder.reflectionToString(obj, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 人民币分转换成元
     * @param cent
     * @return
     */
    public static String toRmbYuan(String cent){
    	if(StringUtils.isNullOrNone(cent)){
    		return null;
    	}
    	double _cent = Double.valueOf(cent);
    	DecimalFormat df = new DecimalFormat("0.00");
    	try {
			String yuan = df.format(_cent/100);
			return yuan;
		} catch (IllegalArgumentException e) {
			return null;
		}
    }
    
    /**
     * 人民币元转换成分
     * @return
     */
    public static String toRmbCent(String yuan){
    	if(StringUtils.isNullOrNone(yuan)){
    		return null;
    	}
    	double _yuan = Double.valueOf(yuan);
    	DecimalFormat df = new DecimalFormat("0");
    	try {
			String cent = df.format(_yuan*100);
			return cent;
		} catch (IllegalArgumentException e) {
			return null;
		}
    }
    
    /**
     * 重量克转换为千克
     * @param gram
     * @return
     */
    public static String toKilogram(String gram){
    	if(StringUtils.isNullOrNone(gram)){
    		return null;
    	}
    	double _gram = Double.valueOf(gram);
    	DecimalFormat df = new DecimalFormat("0.000");
    	try {
			String kilogram = df.format(_gram/1000);
			return kilogram;
		} catch (IllegalArgumentException e) {
			return null;
		}
    }
    
    /**
     * 重量千克转换为克
     * @param kilogram
     * @return
     */
    public static String toGram(String kilogram){
    	if(StringUtils.isNullOrNone(kilogram)){
    		return null;
    	}
    	double _kilogram = Double.valueOf(kilogram);
    	DecimalFormat df = new DecimalFormat("0");
    	try {
			String gram = df.format(_kilogram*1000);
			return gram;
		} catch (IllegalArgumentException e) {
			return null;
		}
    }


    /**
     * 字符过滤
     */
    public static String StringFilter(String str) throws PatternSyntaxException {
        if (!isNullOrNone(str)) {
            String regEx = "[^a-zA-Z0-9\u4e00-\u9fa5\\s+]";
            //String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }else
            return "";

    }


    public static String codelist(Object sb) throws UnsupportedEncodingException {
        String iso8859 = new String(sb.toString().getBytes("iso8859-1"));
        String gbk = new String(sb.toString().getBytes("gbk"));
        String utf8 = new String(sb.toString().getBytes("utf-8"));

        if(iso8859.equals(sb.toString())){
            return "iso8859";
        }else  if(gbk.equals(sb.toString())){
            return "gbk";
        }else  if(utf8.equals(sb.toString())){
            return "utf8";
        }else
            return "unknow";
    }


}
