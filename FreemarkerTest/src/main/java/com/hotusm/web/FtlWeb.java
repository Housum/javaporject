package com.hotusm.web;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("ftl")
@Controller
public class FtlWeb {

	private final static SimpleDateFormat SIMPLE_DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping("show/{id}")
	public String show( Model model,@PathVariable("id") String id,@RequestParam(/*value="date",*/required=false/*,defaultValue="1991-10-10"*/) Date date){
		
		System.out.println("id:"+id);
		model.addAttribute("username", "¬����");
		
		return "/WEB-INF/view/ftl";
	}
	
	
	/**
	 * 
	 * @description <br/> 
	 * @param dataBinder
	 * @InitBinder ���������ܽ���������ת����ʱ��  
	 * ���ǿ���ͨ�����ַ�ʽ����ע������ת���� 
	 * @link  http://elim.iteye.com/blog/1753271
	 */
	@InitBinder
	public void dataBinder(WebDataBinder dataBinder){
		
		dataBinder.registerCustomEditor(Date.class, 
				new PropertyEditorSupport(){

					@Override
					public String getAsText() {
						
						return SIMPLE_DATE_FORMAT.format(getValue());
						
					}

					@Override
					public void setAsText(String text) throws IllegalArgumentException {
						
						try {
							setValue(SIMPLE_DATE_FORMAT.parse(text));
						} catch (ParseException e) {
							setValue(text);
							
							e.printStackTrace();
						}
					}
			
			}
		
				);
	}
}
