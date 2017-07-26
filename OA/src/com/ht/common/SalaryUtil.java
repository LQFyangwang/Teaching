package com.ht.common;

/**
 * 计算工资用到的类
 * @author Administrator
 *
 */
public class SalaryUtil {

	/**
	 * 计算实发工资
	 * @param basicSalary 基本工资
	 * @param jobSalary 岗位工资
	 * @param extraSalary 奖励工资
	 * @param subSalary 扣罚工资
	 * @return
	 */
	public static double totalSalary(double basicSalary, double jobSalary, double extraSalary, double subSalary) {
		
		return basicSalary + jobSalary + extraSalary - subSalary;
	}
	
	/**
	 * 计算应发工资
	 * @param basicSalary 基本工资
	 * @param jobSalary 岗位工资
	 * @param extraSalary 奖励工资
	 * @param subSalary 扣罚工资
	 * @return
	 */
	public static double shouldSalary(double basicSalary, double jobSalary, double extraSalary, double subSalary) {
		
		return basicSalary + jobSalary + extraSalary;
	}
}
