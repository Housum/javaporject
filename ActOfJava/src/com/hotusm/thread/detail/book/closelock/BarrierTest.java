package com.hotusm.thread.detail.book.closelock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarrierTest {
	
	public static void main(String[] args) {
		
		//0  ������Ա�Ǽ�
		List<JoinRunStudent> plays=new ArrayList<JoinRunStudent>();
		Random random=new Random();
		
		int playerNumber=random.nextInt(20)+10;
		
		for(int i=0;i<playerNumber;i++){
			plays.add(new JoinRunStudent("student:"+i));
		}
		
		//һ:  ������5���ֳ�Ϊһ��
		List<List<JoinRunStudent>> groups = group(plays,5);
		//��:  һ��һ��ı���
		
		List<JoinRunStudent> secondGames=new ArrayList<JoinRunStudent>();
		for(int i=0;i<groups.size();i++){
			
		}
		//��:ÿ��ĵ�һ�����б���
		
		
	}
	/**
	 * 
	 * @param plays       �˶�Ա
	 * @param runwayHold  �ܵ����
	 * @return
	 */
	public static List<List<JoinRunStudent>> group(List<JoinRunStudent> plays,int runwayHold){
		if(plays==null||plays.size()<1){
			throw new NullPointerException();
		}
		
		List<List<JoinRunStudent>> students=new ArrayList<List<JoinRunStudent>>();
		List<JoinRunStudent> runway=new ArrayList<JoinRunStudent>();
		for(int i=0,j=0;i<plays.size();i++){
			if(j>runwayHold){
				students.add(runway);
				runway=new ArrayList<JoinRunStudent>();
				j=0;
			}
			runway.add(plays.get(i));
		}
		
		return students;
	}
	
}
