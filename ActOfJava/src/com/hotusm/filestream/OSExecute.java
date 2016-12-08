package com.hotusm.filestream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OSExecute {

	public static void command(String command) throws IOException{
		boolean isError=false;
		Process process=new ProcessBuilder(command.split(" ")).start();
		BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
		String s;
		while((s=br.readLine())!=null){
			System.out.println(s);
		}
		
		StringBuffer sb=new StringBuffer();
		BufferedReader error=new BufferedReader(new InputStreamReader(process.getErrorStream()));
		while((s=error.readLine())!=null){
			sb.append(s);
			isError=true;
		}
		
		if(isError){
			System.out.println(sb.toString());
			throw new RuntimeException("≥ˆœ÷“Ï≥£");
		}
	}
	
	public static void main(String[] args) {
		
		try {
			command("javap OSExecute");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
