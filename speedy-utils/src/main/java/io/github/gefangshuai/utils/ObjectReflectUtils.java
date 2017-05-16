package io.github.gefangshuai.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ObjectReflectUtils {
	
	public static Map<String,String> getAllParams(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String,String> map = new HashMap<String,String>();
		for(Class<?> clazz = model.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {   
			Field[] field = null;        //获取实体类的所有属性，返回Field数组  
			try {    
	            field =  clazz.getDeclaredFields();
	        } catch (Exception e) {    
	            //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。    
	            //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了                      
	        }     
			for(int j=0 ; j<field.length ; j++){     //遍历所有属性
				String name = field[j].getName();    //获取属性的名字
				if("interfaceName".equals(name)||"enterpriseCode".equals(name)){
					continue;
				}
				String newName = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString();    //获取属性的类型
				Method m = clazz.getMethod("get"+newName);
				if("class java.lang.Date".equals(type)){
					
					Date date = (Date) m.invoke(model);
					if(date != null){
						map.put(name, date.toLocaleString());
					}
				}else{
					Object inte = m.invoke(model);
					if(inte != null){
						map.put(name, inte.toString());
					}
				}
			}
		}
		System.out.println("params:"+map.toString());
        return map;
    }

	public static Map<String, Object> getMap(Object o) {
	    Map<String, Object> result = new HashMap<String, Object>();
	    Class clazz = o.getClass();
	    Field[] declaredFields = clazz.getDeclaredFields();
	    
	    for (Field field : declaredFields) {
	        try {
	        	Method method = clazz.getDeclaredMethod("get" + StringUtils.capitalize(field.getName()));
				result.put(field.getName(), method.invoke(o));
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return result;
	}
}
