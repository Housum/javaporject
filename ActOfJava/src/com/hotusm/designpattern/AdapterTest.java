package com.hotusm.designpattern;

import java.util.Arrays;

/**
 * 
 * @author Hotusm

 *	设计模式->适配器模式
 */
/*1.因为Apply类使用了策略模式,如果现在的业务逻辑是需要使用到这个别人
 *  定义的策略.但是Apply中的方法参数只能够接受Processor1,并且Filter
 *  并不是Processor1子类,所以不能作为方法的参数.这时候我们就需要定义一个适配器
 *  AdapterTest继承Processor1,这样Apply策略的方法参数就可以使用AdapterTest.
 *  同时在AdapterTest中对Filter继承
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

	/*2.这个方法是Processor1子类都有的,Filter中也有这个方法.通过代理
	 * 调用这儿方法
	 */
	@Override
	public Object process(Object o) {
		return this.filter.process((Waveform)o);
	}
	public static void main(String[] args) {
		Waveform w=new Waveform();
		//LowPass不是Processor1的子类,这个时候我们可以使用适配器模式(适配器是Processor1的子类).
		Apply.process(new AdapterTest(new LowPass(1.0)), w);
		//一般策略的使用
		Apply.process(new Upcase1(), StrategyTest.s);
		Apply.process(new Downcase1(), StrategyTest.s);
		Apply.process(new Splitter1(), StrategyTest.s);
		//适配器
		Apply.process(new StringAdapter1(new StringSwap()), "abc edf");
		
	}
	
}


/*
 * 1.策略模式
 * */
class Apply{
	public static void process(Processor1 p,Object s){
		System.out.println("Using Processor "+p.name());
		System.out.println(p.process(s));
	}
}
//--------------------------start
//通过继承Processor1实现String的业逻辑
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
/*3.对StringSwap进行适配,这样Apply和p就不需要修改任何代码
 * 了,而且Apply能够直接使用(只需要StringAdapter1进行适配一下)
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



