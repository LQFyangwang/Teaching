#include "stdio.h"

#define PI 3.1415

void main() {
	/**
	short -> int -> long -> float -> double
	
	double -> float -> int -> short(强制转化)
	*/
	short a = 10;
	int b = a;
	int c = 10000000;
	short d = c;// int值超出short所能存储的范围，就会出错
	int e = 1000;
	short f = e;// 1000这个int值未超出short所能存储的范围，则没问题
	short g = (short) e;// (short) 告诉编译器，e这个int值强制转换为short值
	
	/*******************/
	int h = 2147483647;
	float i = h; //int类型可以转化成float类型，也有可能丢失数据
	float j = 10.5f;//定义float类型变量时，初始化的值在后面加个f，准确地告诉编译器，此初始化值为float类型
	int k = j; //float类型可以转化成int类型，但是丢失了精度，这种转化会出数据问题
	float l = 10.65646565f;
	double m = l;// float转化为double时，数据也有可能精度减小
	double n = 10.78787878787;
	float o = n;// double转化为float时，数据丢失，精度减小
	
	/*******************/
	int p = 5;
	int q = 3;
	float r = (float) p / q;// 先把p转化成float类型，再除以q, (float) (p / q)
	float s = ((float) p) / q;
	double t = (double) p / q;
	/*******************/
	printf("short a: %d, int b: %d\n", a, b);
	printf("int c: %d, short d: %d\n", c, d);
	printf("int e: %d, short f: %d\n", e, f);
	printf("int e: %d, short g: %d\n", e, g);
	/*******************/
	printf("int h: %d, float i: %f\n", h, i);
	printf("float j: %f, int k: %d\n", j, k);
	printf("float l: %f, float m: %.9f\n", l, m);
	printf("double n: %.11f, float o: %f\n", n, o);
	/******************/
	printf("5 / 3 = %f, %f, %.9f\n", r, s, t);
}