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
		System.out.println("������·����");
		Scanner scanner = new Scanner(System.in);
		String f =scanner.nextLine();
		File file = new File(f);
			fun = new functions();
			System.out.println("ָ��˵����");
			System.out.println("wc.exe -c �����ļ�file.c���ַ���:");
			System.out.println("wc.exe -w �����ļ�file.c�Ĵʵ���Ŀ:");
			System.out.println("wc.exe -l �����ļ�file.c������:");
			System.out.println("wc.exe -s �ݹ鴦��Ŀ¼�·����������ļ�");
			System.out.println("wc.exe -a �����ļ�file.c�Ŀ��У������У�ע����:");
			System.out.println("wc.exe -x ��ʾͼ�ν����Լ�ѡ���ļ���ȫ��ͳ����Ϣ");
			System.out.println("������ָ�:");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			switch(str) {
			case "wc.exe -c":
				
				if(file.exists()== false) {
					System.out.println("·������������������룡");
					start();
				}
				else {
				System.out.println("���ļ����ַ���Ϊ��:"+fun.getCodenum(file));
				}
				break;
			case "wc.exe -w":
			if(file.exists()== false) {
				System.out.println("·������������������룡");
				start();
			}
			else {
				System.out.println("���ļ��Ĵʵ���ĿΪ��:"+fun.getWordnum(file));
			}
				break;
			case "wc.exe -l":
				if(file.exists()== false) {
					System.out.println("·������������������룡");
					start();
				}else {
				System.out.println("���ļ�������Ϊ��:"+fun.getLinenum(file));
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
					System.out.println("·������������������룡");
					start();
				}else {
				int[]i=fun.getCount(file);
				System.out.println("���ļ��Ŀ���Ϊ��:"+i[0]);
				System.out.println("���ļ���ע����Ϊ��:"+i[1]);
				System.out.println("���ļ��Ĵ�����Ϊ��:"+i[2]);
				}
				break;
			case "wc.exe -x":
				windows win = new windows();
				win.go();
				break;
			default:
				System.out.println("��������");
				start();
				break;
			}
		
	}
	
}
