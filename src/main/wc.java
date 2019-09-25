package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class wc {
	public static void main(String[] args){
		start();
	}

	private static void start() {
		// TODO Auto-generated method stub
		functions fun = null;
		System.out.println("请输入路径：");
		Scanner scanner = new Scanner(System.in);
		String f =scanner.nextLine();
		File file = new File(f);
			fun = new functions();
			System.out.println("指令说明：");
			System.out.println("wc.exe -c 返回文件file.c的字符数:");
			System.out.println("wc.exe -w 返回文件file.c的词的数目:");
			System.out.println("wc.exe -l 返回文件file.c的行数:");
			System.out.println("wc.exe -s 递归处理目录下符合条件的文件");
			System.out.println("wc.exe -a 返回文件file.c的空行，代码行，注释行:");
			System.out.println("wc.exe -x 显示图形界面以及选中文件的全部统计信息");
			System.out.println("请输入指令：:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			switch(str) {
			case "wc.exe -c":
				
				if(file.exists()== false) {
					System.out.println("路径输入错误，请重新输入！");
					start();
				}
				else {
				System.out.println("该文件的字符数为：:"+fun.getCodenum(file));
				}
				break;
			case "wc.exe -w":
			if(file.exists()== false) {
				System.out.println("路径输入错误，请重新输入！");
				start();
			}
			else {
				System.out.println("该文件的词的数目为：:"+fun.getWordnum(file));
			}
				break;
			case "wc.exe -l":
				if(file.exists()== false) {
					System.out.println("路径输入错误，请重新输入！");
					start();
				}else {
				System.out.println("该文件的行数为：:"+fun.getLinenum(file));
				}
				break;
			case "wc.exe -s":
				String f1 =f.substring(0,f.lastIndexOf("\\"));
				try {
					fun.foundFilePath(f1, f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "wc.exe -a":
				if(file.exists()== false) {
					System.out.println("路径输入错误，请重新输入！");
					start();
				}else {
				int[]i=fun.getCount(file);
				System.out.println("该文件的空行为：:"+i[0]);
				System.out.println("该文件的注释行为：:"+i[1]);
				System.out.println("该文件的代码行为：:"+i[2]);
				}
				break;
			case "wc.exe -x":
				windows win = new windows();
				win.go();
				break;
			default:
				System.out.println("输入有误！");
				start();
				break;
			}
		
	}
	
}
