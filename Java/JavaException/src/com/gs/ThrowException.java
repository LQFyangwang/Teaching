package com.gs;

public class ThrowException {
	
	// throws抛出异常的声明
	public void test() throws MyException {
		// throw为抛出异常
		throw new MyException("MyException异常产生");
	}
	
	public void test1() throws MyException {
		throw new MyException("MyException异常产生");
	}
	
	/**
	 * 可以抛出多个异常
	 * @param a
	 * @param b
	 * @return
	 * @throws MathException
	 * @throws MyException
	 */
	public int add(int a, int b) throws MathException, MyException {
		if (a <= 0 || b <= 0) {
			throw new MathException("两个加数都必须是正整数"); // 一旦有异常抛出，后面的代码就不会执行
		}
		if (a == -1) {
			throw new MyException();
		}
		return a + b;
		// throw new MathException(); // return后方法执行完毕，不能再抛出异常
	}

}
