package com.hotusm.thread.detail.book.safetypublish;

public class Test {

	public static void main(String[] args) {
		
		NotSafePublish p=new NotSafePublish();
		Bo bo=new Bo();
		bo.setMsg("124");
		p.setSecond(bo);
		//��������������ǿ��Կ����� һ���������ȷ������ʽ  
		//�������ֱ�ӷ��ص������е����û��߻�������ֵ��ʱ��ֱ�ӽ����ø�ֵ�Ļ� 
		//��ô��������Ķ�����Ѿ������
		System.out.println(p.getSecond().getMsg());
		bo.setMsg("567");
		System.out.println(p.getSecond().getMsg());
	}
}
