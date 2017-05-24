package com.kanasinfo.utils;

import java.math.BigDecimal;

/**
 * 精度计算工具类
 * 
 * @author Devid
 *
 */
public class MathUtils {
	
	
	/**
	 * 加法
	 */
	public static double add(Double d1, Double d2) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 减法
	 */
	public static double sub(Double d1, Double d2) { 
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 乘法
	 */
	public static double mul(Double d1, Double d2) { // 进行乘法运算
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 除法
	 */
	public static double div(Double d1, Double d2, int len) {
		if (d1 == null || d2 == null) {
			System.out.println("[除法运算]数值为空");
			return 0;
		}
		if(d2.doubleValue() == 0) {
			System.out.println("[除法运算]分母为0");
			return 0;
		}
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 对数值进行四舍五入操作
	 */
	public static double round(Double d, int len) { // 进行四舍五入操作
		BigDecimal b1 = new BigDecimal(d);
		BigDecimal b2 = new BigDecimal(1);
		// 任何一个数字除以1都是原数字
		// ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
		return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static void main(String[] args) {
        System.out.println(MathUtils.div(20.0, 0.0, 2));

    }
}
