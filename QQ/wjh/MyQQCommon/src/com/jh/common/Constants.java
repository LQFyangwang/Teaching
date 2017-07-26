package com.jh.common;

public class Constants {

	// 公用常量区
	public static final int PORT = 8888; // 端口号
	public static final int MESSAGE_SLEEP = 1000; // 读写线程的休眠时间，单位为ms
	public static final int BACKGROUND_SLEEP = 5000; // 背景图片切换休眠时间
	
	// 公用变量区
	public static int frameX = 500;
	public static int frameY = 200;
	public static int codeX = 170;
	public static int codeY = 100;
	public static boolean mainOk = false;
	public static boolean saveData = false; // 是否保存数据
	public static boolean saveWin = false; // 是否保存成功
	public static boolean select = true; // 判断资料卡是否已经打开
	public static boolean isRegisterFrameOpen = true; // 判断是否可以打开注册界面
	public static boolean isAlterSkinFrameOpen = true; // 判断是否可以打开皮肤窗口
	public static boolean isSeekFirendFrameOpen = true; // 判断是否可以打开查找好友的窗口
	public static boolean isAlterHeadFrameOpen = true; // 设置头像的窗口
	public static boolean isFriendMessageFrameOpen = true; // 查看好友资料的窗体
	public static boolean isFriendMessageExtendsOpen = true; // 聊天界面打开的好友资料
	public static boolean isApplyManageFrameOpen = true; // 功能窗体
	public static boolean isSelectPasswordFrameOpen = true; // 修改密码窗体
	
	public static boolean rememberPwd = false; // 记住密码是否被勾选
	public static String lastPwd = ""; // 用来存储上一次的密码
	
	public static boolean isFile = false; // 是否有发送文件
	public static String fileName = "";
	
	public static String cutName = ""; // 截图图片
	
	
}
