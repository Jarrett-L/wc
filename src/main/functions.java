package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class functions {
	//计算字符数
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
	//计算行数
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
	//计算词数
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
	//计算空行，代码行，注释行；
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
	           if(c.contains("/*")) {   //多行注释开始标记
	               notecount++;
	               state = true;
	               if(c.contains("*/")) {
	            	   state = false;
	               }
	           }
	           else if(state) {
	               notecount++;
	               if(c.contains("*/")) {  //多行注释结束标记
	               state = false;}
	           }
	           else if(c.contains("//")) {  //单行注释标记
	               notecount++;
	           }
	           else if(c.trim().length()>1) {  //判定为代码行条件
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
	//递归功能
	public static void foundFilePath(String path, String fileName) throws IOException {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            }else if(fileName.contains("*")||fileName.contains("?")) {
            	for (File file2 : files) {
                    if (file2.isDirectory()) {                    
                        foundFilePath(file2.getAbsolutePath(), fileName);
                    } else if (match(file2.getAbsolutePath(),fileName)) {
                  
        				System.out.println("该文件的字符数为：:"+getCodenum(file2));
        			
        				System.out.println("该文件的词的数目为：:"+getWordnum(file2));
     
        				System.out.println("该文件的行数为：:"+getLinenum(file2));                    
        				}
                }
            } 
            
            else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {                    
                        foundFilePath(fileName,file2.getAbsolutePath());
                    } else if (file2.getName().contains(fileName)) {
                    	System.out.println("该文件的字符数为：:"+getCodenum(file2));
            			
        				System.out.println("该文件的词的数目为：:"+getWordnum(file2));
     
        				System.out.println("该文件的行数为：:"+getLinenum(file2));                    
        				}
                    }
                }
        } else {
            System.out.println("文件不存在!");
        }
    }
	/**
     * 通配符算法。 可以匹配"*"和"?"
     * 如a*b?d可以匹配aAAAbcd
     * @param pattern 匹配表达式
     * @param str 匹配的字符串
     * @return
     */
    public static boolean match(String pattern, String str) {
        if (pattern == null || str == null)
            return false;
 
        boolean result = false;
        char c; // 当前要匹配的字符串
        boolean beforeStar = false; // 是否遇到通配符*
        int back_i = 0;// 回溯,当遇到通配符时,匹配不成功则回溯
        int back_j = 0;
        int i, j;
        for (i = 0, j = 0; i < str.length();) {
            if (pattern.length() <= j) {
                if (back_i != 0) {// 有通配符,但是匹配未成功,回溯
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
                if (j == pattern.length() - 1) {// 通配符已经在末尾,返回true
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
                    if (back_i != 0) {// 有通配符,但是匹配未成功,回溯
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
 
        if (i == str.length() && j == pattern.length())// 全部遍历完毕
            result = true;
        return result;
    }
    
}
