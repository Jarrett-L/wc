package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class functions {
	//�����ַ���
	public static int getCodenum(File file) {
		int codenum = 0;
		try {
			FileReader fr = new FileReader(file); 
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s = br.readLine())!=null) {
				codenum+=s.length();
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return codenum;
	}
	//��������
	public static int getLinenum(File file) {
		int linenum = 0;
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while(br.readLine()!=null) {
				linenum ++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linenum;
	}
	//�������
	public static int getWordnum(File file) {
		int wordnum = 0;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String l =null;
			while((l=br.readLine())!=null) {
				String w ="\\d+.\\d+|\\W+";
				Pattern pattern = Pattern.compile(w);
				Matcher mat = Pattern.compile(w).matcher(l);
				while(mat.find()) {
					wordnum++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return wordnum;
	}
	//������У������У�ע���У�
	public int[] getCount(File file) {
		int[] count = new int[3];
		int spacecount = 0;
	    int notecount = 0;
	    int codecount = 0;
	    boolean state = false;
	    try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			 String c;
	    while((c=br.readLine())!=null) {
	           if(c.contains("/*")) {   //����ע�Ϳ�ʼ���
	               notecount++;
	               state = true;
	               if(c.contains("*/")) {
	            	   state = false;
	               }
	           }
	           else if(state) {
	               notecount++;
	               if(c.contains("*/")) {  //����ע�ͽ������
	               state = false;}
	           }
	           else if(c.contains("//")) {  //����ע�ͱ��
	               notecount++;
	           }
	           else if(c.trim().length()>1) {  //�ж�Ϊ����������
	               codecount++;
	           }
	           else {spacecount++;}
	       }
		}catch(Exception e) {
			e.printStackTrace();
		}
	    count[0]=spacecount;
	    count[1]=notecount;
	    count[2]=codecount;
	   return count;
	}
	//�ݹ鹦��
	public static void foundFilePath(String path, String fileName) throws IOException {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("�ļ����ǿյ�!");
                return;
            }else if(fileName.contains("*")||fileName.contains("?")) {
            	for (File file2 : files) {
                    if (file2.isDirectory()) {                    
                        foundFilePath(file2.getAbsolutePath(), fileName);
                    } else if (match(file2.getAbsolutePath(),fileName)) {
                  
        				System.out.println("���ļ����ַ���Ϊ��:"+getCodenum(file2));
        			
        				System.out.println("���ļ��Ĵʵ���ĿΪ��:"+getWordnum(file2));
     
        				System.out.println("���ļ�������Ϊ��:"+getLinenum(file2));                    
        				}
                }
            } 
            
            else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {                    
                        foundFilePath(fileName,file2.getAbsolutePath());
                    } else if (file2.getName().contains(fileName)) {
                    	System.out.println("���ļ����ַ���Ϊ��:"+getCodenum(file2));
            			
        				System.out.println("���ļ��Ĵʵ���ĿΪ��:"+getWordnum(file2));
     
        				System.out.println("���ļ�������Ϊ��:"+getLinenum(file2));                    
        				}
                    }
                }
        } else {
            System.out.println("�ļ�������!");
        }
    }
	/**
     * ͨ����㷨�� ����ƥ��"*"��"?"
     * ��a*b?d����ƥ��aAAAbcd
     * @param pattern ƥ����ʽ
     * @param str ƥ����ַ���
     * @return
     */
    public static boolean match(String pattern, String str) {
        if (pattern == null || str == null)
            return false;
 
        boolean result = false;
        char c; // ��ǰҪƥ����ַ���
        boolean beforeStar = false; // �Ƿ�����ͨ���*
        int back_i = 0;// ����,������ͨ���ʱ,ƥ�䲻�ɹ������
        int back_j = 0;
        int i, j;
        for (i = 0, j = 0; i < str.length();) {
            if (pattern.length() <= j) {
                if (back_i != 0) {// ��ͨ���,����ƥ��δ�ɹ�,����
                    beforeStar = true;
                    i = back_i;
                    j = back_j;
                    back_i = 0;
                    back_j = 0;
                    continue;
                }
                break;
            }
 
            if ((c = pattern.charAt(j)) == '*') {
                if (j == pattern.length() - 1) {// ͨ����Ѿ���ĩβ,����true
                    result = true;
                    break;
                }
                beforeStar = true;
                j++;
                continue;
            }
 
            if (beforeStar) {
                if (str.charAt(i) == c) {
                    beforeStar = false;
                    back_i = i + 1;
                    back_j = j;
                    j++;
                }
            } else {
                if (c != '?' && c != str.charAt(i)) {
                    result = false;
                    if (back_i != 0) {// ��ͨ���,����ƥ��δ�ɹ�,����
                        beforeStar = true;
                        i = back_i;
                        j = back_j;
                        back_i = 0;
                        back_j = 0;
                        continue;
                    }
                    break;
                }
                j++;
            }
            i++;
        }
 
        if (i == str.length() && j == pattern.length())// ȫ���������
            result = true;
        return result;
    }
    
}
