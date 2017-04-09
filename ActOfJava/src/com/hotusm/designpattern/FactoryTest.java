package com.hotusm.designpattern;

/**
 * 
 * @author Hotusm
 * 1.���ģʽ->�����������ģʽ
 * 
 * ʹ�ö�̳�ʵ�ֹ����������ģʽ,���ģʽ����Ȼ��ĳ���
 * ���ڲ�ͬ��������в�ͬ�Ĺ�������������,ͬʱ���ö�̬������,
 * �����ô����ʱ��,�ڷ���������д����˸���ӿڵ�����.
 * 
 */
public class FactoryTest {
	/*
	 * ��̳�,���ؾ����ʵ��,�����޸�ʵ�ֲ���Ӱ��ʹ��
	 * */
	public static void factory(CycleFactory cFactory){
		Cycle cycle = cFactory.getInstance();
		cycle.run();
	}
	
	public static void main(String[] args) {
		factory(new UnicycleFactory());
		factory(new BicycleFactory());
	}
}
/*1.�������ĳ���ӿ�
 * 
 * */
interface Cycle{
	public void run();
}
/*
 * ʵ���������ӿ�->���ֳ�
 */
class Unicycle implements Cycle{

	@Override
	public void run() {
		System.out.println("-�����ֳ���-");
	}
}
/*
 * ʵ���������ӿ�->��̤��
 */
class Bicycle implements Cycle{

	@Override
	public void run() {
		System.out.println("=����̤����=");
	}
}
/*
 * ʵ���������ӿ�->���ֳ�
 */
class Tricycle implements Cycle{

	@Override
	public void run() {
		System.out.println("�������ֳ�����");
	}
}
/*
 *������������������ӿ�,���ڲ�ͬ�Ĺ������в�ͬ��ʵ�� 
 * */
interface CycleFactory{
	Cycle getInstance();
}
/*
 * ʵ�ʹ���->���ֳ�����
 * */
class UnicycleFactory implements CycleFactory{

	@Override
	public Cycle getInstance() {
		return new Unicycle();
	}
}
/*
 * ʵ�ʹ���->��̤������
 * */
class BicycleFactory implements CycleFactory{

	@Override
	public Cycle getInstance() {
		return new Bicycle();
	}
}
/*
 * ʵ�ʹ���->���ֳ�����
 * */
class TricycleFactory implements CycleFactory{

	@Override
	public Cycle getInstance() {
		return new Tricycle();
	}
}
