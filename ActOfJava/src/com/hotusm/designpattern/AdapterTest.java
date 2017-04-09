package com.hotusm.designpattern;

import java.util.Arrays;

/**
 * 
 * @author Hotusm

 *	���ģʽ->������ģʽ
 */
/*1.��ΪApply��ʹ���˲���ģʽ,������ڵ�ҵ���߼�����Ҫʹ�õ��������
 *  ����Ĳ���.����Apply�еķ�������ֻ�ܹ�����Processor1,����Filter
 *  ������Processor1����,���Բ�����Ϊ�����Ĳ���.��ʱ�����Ǿ���Ҫ����һ��������
 *  AdapterTest�̳�Processor1,����Apply���Եķ��������Ϳ���ʹ��AdapterTest.
 *  ͬʱ��AdapterTest�ж�Filter�̳�
 * */
public class AdapterTest implements Processor1{

	Filter filter;
	public AdapterTest(Filter filter) {
		this.filter=filter;
	}
	@Override
	public String name() {
		return this.filter.getClass().getSimpleName();
	}

	/*2.���������Processor1���඼�е�,Filter��Ҳ���������.ͨ������
	 * �����������
	 */
	@Override
	public Object process(Object o) {
		return this.filter.process((Waveform)o);
	}
	public static void main(String[] args) {
		Waveform w=new Waveform();
		//LowPass����Processor1������,���ʱ�����ǿ���ʹ��������ģʽ(��������Processor1������).
		Apply.process(new AdapterTest(new LowPass(1.0)), w);
		//һ����Ե�ʹ��
		Apply.process(new Upcase1(), StrategyTest.s);
		Apply.process(new Downcase1(), StrategyTest.s);
		Apply.process(new Splitter1(), StrategyTest.s);
		//������
		Apply.process(new StringAdapter1(new StringSwap()), "abc edf");
		
	}
	
}


/*
 * 1.����ģʽ
 * */
class Apply{
	public static void process(Processor1 p,Object s){
		System.out.println("Using Processor "+p.name());
		System.out.println(p.process(s));
	}
}
//--------------------------start
//ͨ���̳�Processor1ʵ��String��ҵ�߼�
abstract class StringProcessor implements Processor1{
	
	public String name(){return getClass().getSimpleName();}
	public abstract String process(Object input);
}
class Upcase1 extends StringProcessor{

	@Override
	public String process(Object input) {
		return ((String)input).toUpperCase();
	}
	
}
class Downcase1 extends StringProcessor{

	@Override
	public String process(Object input) {
		return ((String)input).toLowerCase();
	}
	
}
class Splitter1 extends StringProcessor{

	@Override
	public String process(Object input) {
		return Arrays.toString(((String)input).split(" "));
	}
	
}
//--------------------------end

//--------------------------start
class Waveform{
	private static long counter;
	private final long id=counter++;
	public String toString(){return "Waveform "+id;}
}
class Filter{
	
	public String name(){
		return getClass().getSimpleName();
	}
	public Waveform process(Waveform input){return input;}
	
}
class LowPass extends Filter{
	double cutff;
	public LowPass(double cutff) {
		this.cutff=cutff;
	}
	public Waveform process(Waveform input){return input;}
}
class HighPass extends Filter{
	double cutoff;
	public HighPass(double cutoff) {
		this.cutoff=cutoff;
	}
	public Waveform process(Waveform input){return input;}
}

class BandPass extends Filter{
	double lowCutoff,highCutoff;
	public BandPass(double lowCutoff,double highCutoff) {
		this.lowCutoff=lowCutoff;
		this.highCutoff=highCutoff;
	}
	public Waveform process(Waveform input){return input;}
}

//----------------------------------end


class StringAdapter implements Processor1{

	@Override
	public String name() {
		return null;
	}

	@Override
	public Object process(Object o) {
		return null;
	}
	
}
//-----------------
class StringSwap{
	
	public String swap(String s){
		String[] strs=s.split(" ");
		if(strs.length>2){
			return "";
		}
		String i=strs[0];
		strs[0]=strs[1];
		strs[1]=i;
		return strs[0]+" "+strs[1];
	}
	
}
/*3.��StringSwap��������,����Apply��p�Ͳ���Ҫ�޸��κδ���
 * ��,����Apply�ܹ�ֱ��ʹ��(ֻ��ҪStringAdapter1��������һ��)
 * */
class StringAdapter1 implements Processor1{
	
	StringSwap swap;
	
	public StringAdapter1(StringSwap swap) {
		this.swap=swap;
	}
	@Override
	public String name() {
		return this.swap.getClass().getSimpleName();
	}

	@Override
	public Object process(Object o) {
		return this.swap.swap((String)o);
	}
	
}



