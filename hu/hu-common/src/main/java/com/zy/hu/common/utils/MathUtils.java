package com.zy.intelligentdevice.common.utils;



import com.github.pagehelper.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;

public class MathUtils implements Serializable {

	private static Long min = 60000l;
    private static Long hour = 3600000l;
    private static Long day = 86400000l;
    private static Long twoDay = 172800000l;
    
    

	
	public static String getProportion(Double d1, Double d2){
		// 创建一个数值格式化对象  
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位  
        numberFormat.setMaximumFractionDigits(2); 
        String result = numberFormat.format((double) d1 / (double) d2 * 100);
		return result;
	}
	
	/**
	 * double  加法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double add(Double d1, Double d2){
		if(d1!=null&&d2!=null){
			BigDecimal bd1 = new BigDecimal(d1);
	        BigDecimal bd2 = new BigDecimal(d2);
	        return bd1.add(bd2).doubleValue(); 
		}
        return null;
	}
	
	/**
	 * 减法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double subtract(Double d1, Double d2){
		if(d1!=null&&d2!=null){
			BigDecimal bd1 = new BigDecimal(d1);
	        BigDecimal bd2 = new BigDecimal(d2);
	        return bd1.subtract(bd2).doubleValue(); 
		}
        return null;
	}
	
	/**
	 * 乘法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double multiply(Double d1, Double d2){
		if(d1!=null&&d2!=null){
			BigDecimal bd1 = new BigDecimal(d1);
	        BigDecimal bd2 = new BigDecimal(d2);
	        return bd1.multiply(bd2).doubleValue(); 
		}
        return null;
	}
	
	/**
	 * 除法
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Double divide(Double d1, Double d2){
		try{
			if(d1!=null&&d2!=null){
				if(d1.equals(0.0)){//分子为0，返回0.0
					return d1;
				}
				BigDecimal bd1 = new BigDecimal(d1);
		        BigDecimal bd2 = new BigDecimal(d2);
		        return bd1.divide(bd2,2, BigDecimal.ROUND_DOWN).doubleValue();
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 除法
	 * @param d1
	 * @param d2
	 * @param index 保留小数点几位
	 * @return
	 */
	public static Double divide(Double d1, Double d2, Integer index){
		try{
			if(d1!=null&&d2!=null){
				BigDecimal bd1 = new BigDecimal(d1);
		        BigDecimal bd2 = new BigDecimal(d2);
		        return bd1.divide(bd2,index, BigDecimal.ROUND_DOWN).doubleValue();
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 随机50 到 100的整数
	 * @return
	 */
	public static Integer random50to100(){
		Random r = new Random();
		return r.nextInt(50)+50;
	}
	
	/**
	 * 随机10位数字 字符串
	 * @return
	 */
	public static String randomInt_10(){
		Random r = new Random();
		String s = "";
		for(int i = 0;i<10;i++){
			s += r.nextInt(10)+"";
		}
		return s;
	}
	
	/**
	 * 随机6位数字 字符串
	 * @return
	 */
	public static String randomInt_6(){
		Random r = new Random();
		String s = "";
		for(int i = 0;i<6;i++){
			s += r.nextInt(10)+"";
		}
		return s;
	}
	
	//生成随机数字和字母,  
    public static String getStringRandom(int length) {
          
        String val = "";
        Random random = new Random();
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));
            }  
        }  
        return val;  
    }  
    
    /**
     * 比较大小
     * @return
     */
    public static Integer compareSize(String s1, String s2){
    	if(StringUtil.isNotEmpty(s1)&& StringUtil.isNotEmpty(s2)){
    		Double d1 = Double.valueOf(s1);
        	Double d2 = Double.valueOf(s2);
	        if(d1>d2){//大于
	        	return 1;
	        }else if(d1<d2){//小于
	        	return -1;
	        }else{//等于
	        	return 0;
	        }
		}else{
			return null;
		}
    	
    }
    
    
    /**
     * 冒泡排序，从小到大
     * @param list
     * @return
     */
    public static List<Integer> getSort(List<Integer> list){
    	for(int i=0;i<list.size()-1;i++){
    		for(int j=0;j<list.size()-1-i;j++){
    			Integer a = list.get(j);
    			Integer b = list.get(j+1);
    			if(a>b){
    				int temp = a;
    				a = b;
    				b = temp;
    				list.set(j, a);
    				list.set(j+1, b);
    			}
    		}
    	}
    	for(int num:list){
    		System.out.println(num);
    	}
    	return list;
    }

	//随机获取数组里一个值
	public static String getOneString(String[] arr) {
		Integer count = arr.length;
		Random rand = new Random();
		int num = rand.nextInt(count);
		return arr[num];
	}

	//四舍五入
	public static Double roundHalfUp(Double num , Integer scale) {
		return new BigDecimal(num).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 *  对整个List的内容求和
	 * */
	public static Double sumWholeList(List<String> list) {
		Double sum = 0d;
		for (String str : list) {
			try {
				sum += Double.valueOf(str);
			} catch (Exception e){
				continue;
			}
		}
		return sum;
	}

}
