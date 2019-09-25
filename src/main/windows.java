package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
public class windows implements ActionListener {
	JFrame frame;
	JTextArea textArea;
	int charNum = 0; // 用来接收上面统计字符数方法的返回值
	int wordNum = 0; // 用来接收上面统计词数方法的返回值
	int lineNum = 0; // 用来接收上面统计行数方法的返回值
	int[] resultList = new int[3]; // 接收上面统计特殊行数目方法的返回值，调用get()方法给下面三个变量赋值
	int blankLineNum = 0;
	int codeLineNum = 0;
	int annotationLineNum = 0;

	// 简单的Gui，创建JFrame对象、用来显示结果的TextArea、以及一个button用来监听
	public void go() {
		frame = new JFrame("文件统计GUI");
		textArea = new JTextArea();
		frame.getContentPane().add(BorderLayout.CENTER, textArea);

		JButton button = new JButton("选择文件并进行各类统计");
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		button.addActionListener(this);

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		JFileChooser chooser = new JFileChooser(); // 创建JFileChooser对象用来选择要统计的文件
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 限定被选文件形式
		chooser.showDialog(new JLabel(), "选择"); // 选择框
		File file = chooser.getSelectedFile(); // 获取文件
		for (int i = 0; i < 4; i++) { // 调用上面写好的方法，并将返回值赋给相应变量
			
				functions fun =new functions(); 
				switch (i) {
				case 0:
					charNum = fun.getCodenum(file);
					break;
				case 1:
					wordNum = fun.getWordnum(file);
					break;
				case 2:
					lineNum = fun.getLinenum(file);
					break;
				case 3:
					String f = file.getAbsolutePath();
					String f1 =f.substring(0,f.lastIndexOf("\\"));
					resultList =fun.getCount(file);
					blankLineNum = resultList[0];
					codeLineNum = resultList[1];
					annotationLineNum = resultList[2];
					break;
				}
		}
		// 将结果展示在TextArea上
		textArea.append("字符数：" + charNum + "\n词数：" + wordNum + "\n行数：" + lineNum + "\n空行数：" + blankLineNum + "\n代码行数："
				+ codeLineNum + "\n注释行数" + annotationLineNum);
	}
}
