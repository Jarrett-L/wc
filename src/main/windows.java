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
	int charNum = 0; // ������������ͳ���ַ��������ķ���ֵ
	int wordNum = 0; // ������������ͳ�ƴ��������ķ���ֵ
	int lineNum = 0; // ������������ͳ�����������ķ���ֵ
	int[] resultList = new int[3]; // ��������ͳ����������Ŀ�����ķ���ֵ������get()��������������������ֵ
	int blankLineNum = 0;
	int codeLineNum = 0;
	int annotationLineNum = 0;

	// �򵥵�Gui������JFrame����������ʾ�����TextArea���Լ�һ��button��������
	public void go() {
		frame = new JFrame("�ļ�ͳ��GUI");
		textArea = new JTextArea();
		frame.getContentPane().add(BorderLayout.CENTER, textArea);

		JButton button = new JButton("ѡ���ļ������и���ͳ��");
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		button.addActionListener(this);

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		JFileChooser chooser = new JFileChooser(); // ����JFileChooser��������ѡ��Ҫͳ�Ƶ��ļ�
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // �޶���ѡ�ļ���ʽ
		chooser.showDialog(new JLabel(), "ѡ��"); // ѡ���
		File file = chooser.getSelectedFile(); // ��ȡ�ļ�
		for (int i = 0; i < 4; i++) { // ��������д�õķ�������������ֵ������Ӧ����
			
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
		// �����չʾ��TextArea��
		textArea.append("�ַ�����" + charNum + "\n������" + wordNum + "\n������" + lineNum + "\n��������" + blankLineNum + "\n����������"
				+ codeLineNum + "\nע������" + annotationLineNum);
	}
}
