package com.hotusm.fm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.StringUtil;

public class FreeMarkerUtil {
	
	public static Template getTemplate(String name){
		
		try {
			
			Configuration cfg=new Configuration();
			cfg.setClassForTemplateLoading(Thread.currentThread().getClass(), "/ftl");
			Template temp=cfg.getTemplate(name);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
     * ����̨���
     * 
     * @param name
     * @param root
     */
    public static void print(String name, Map<String, Object> root) {
        try {
            // ͨ��Template���Խ�ģ���ļ��������Ӧ����
            Template temp = getTemplate(name);
            temp.process(root, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���HTML�ļ�
     * 
     * @param name
     * @param root
     * @param outFile
     */
    public static void fprint(String name, Map<String, Object> root, String outFile) {
        FileWriter out = null;
        try {
            // ͨ��һ���ļ���������Ϳ���д����Ӧ���ļ��У��˴��õ��Ǿ���·��
            out = new FileWriter(new File("E:\\" + outFile));
            Template temp = getTemplate(name);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String render(String name,Map<String, Object> root){
    	StringWriter out = new StringWriter();
    	String html=null;
    	try {
			getTemplate(name).process(root,out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	html=out.toString();
    	return html;
    }
	
}
