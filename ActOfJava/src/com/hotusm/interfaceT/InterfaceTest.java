package com.hotusm.interfaceT;
/**
 * 
 * @author Hotusm
 * ʹ�þ�̬���������ĺô�
 */
public class InterfaceTest {
	public static void main(String[] args) {
		PeopleProvider p=new PeopleProvider();
		People p1 = p.getInstace();
		p1.eat(new Meat());
	}
}
class PeopleProvider{
	
	public People getInstace(){
		return new PeopleImpl();
	}
	
	//���ڿͻ��˳���˵,����֪���ڲ�ʵ��,��Ϊ����һ��private���͵�����
	//��Ҳ������ӿڱ�̵�һ��˼��
	
	private class PeopleImpl implements People{

		@Override
		public void eat(Object object) {
			if(!(object instanceof Meat)){
				say("����ʳ���");
			}else{
				say("�Ե�����");
			}
		}

		@Override
		public void say(String language) {
				System.out.println(language);
			}
		}
}
