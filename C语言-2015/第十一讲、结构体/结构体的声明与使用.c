#include "stdio.h"
#include "stdlib.h"
#include "time.h"


/**
	结构体是自定义的数据类型
 struct 结构体的名称 {
 	变量声明;
 };
 
 声明结构体类型的变量
 struct 结构体的名称 变量名称;  （大部分情况下此处的变量会是一个数组）
 
 使用结构体内部声明的变量: 结构体变量.变量名
 
*/
struct stu {
	int number;
	int chinese;
	int math;
	int cs;
};

struct birthday {
	int year;
	int month;
	int day;
} birth = {1990, 1, 1}; 

/**
	struct {
	
	} 结构体名称[10];    全局变量
*/
struct person{
	int account;
	float money;
	struct birthday birth; // 结构体嵌套使用
} person[10];

void printPerson() {
	person[0].account = 11111;
    person[0].money = 0.f;
    person[0].birth.month = 2;
    person[0].birth.day = 18;
    printf("printPerson: %d %f\n", person[0].account, person[0].money);
    printf("生日:%d月%d日\n", person[0].birth.month, person[0].birth.day);
}

void main()
{
	struct stu stu1;
	struct stu stu2 = {90, 90, 90}; // 这种类型的初始化一定要注意成员的数据类型及顺序
    struct stu stuData[10]; // 定义存储10个stu结构体类型的数组
    int i;
    stu1.chinese = 100; // 用成员访问符“.”来访问结构体内的成员
    stu1.math = 100;
    stu1.cs = 100;
    printf("stu1: %d %d %d\n", stu1.chinese, stu1.math, stu1.cs);
    printf("stu2: %d %d %d\n", stu2.chinese, stu2.math, stu2.cs);
    stu1 = stu2; // 结构体类型变量整体赋值，要注意必须是同一个结构体类型
    printf("重新整体赋值后stu1: %d %d %d\n", stu1.chinese, stu1.math, stu1.cs);
    srand(time(NULL));
    for(i = 0; i < 10; i++) {
    	stuData[i].number = i + 1;
    	stuData[i].chinese = rand();
    	stuData[i].math = rand();
    	stuData[i].cs = rand();	
    }
    printf("Number\tChinese\tMath\tCS\n");
    for(i = 0; i < 10; i++) {
    	printf("%d\t", stuData[i].number);
    	printf("%d\t", stuData[i].chinese);
    	printf("%d\t", stuData[i].math);
    	printf("%d\t", stuData[i].cs);
    	printf("\n");
    }
    
    person[0].account = 11111;
    person[0].money = 0.f;
    printf("main person: %d %f\n", person[0].account, person[0].money);
    printPerson();
}