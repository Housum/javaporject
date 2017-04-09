package com.hotusm.designpattern;

/**
 * 
 * @author Hotusm
 * 设计模式->调停者模式
 * 1.调停者模式是对象的行为模式。调停者模式包装了一系列对象相互作用的方式，
 * 使得这些对象不必相互明显引用。从而使它们可以较松散地耦合。当这些对象中的某些
 * 对象之间的相互作用发生改变时，不会立即影响到其他的一些对象之间的相互作用。从而保
 * 证这些相互作用可以彼此独立地变化。
 * 2.通俗的讲就是由一个中心的调节者来进行各种类之间的协调,而不是将各个类直接的耦合,这样写出来的
 * 代码就是松耦合的
 * 3.主板和声卡,显卡,CPU之间就是这种关系:主板负责调节这些东西的合作,而不是直接通过他们之间条用（图片:调停者模式2）
 * 4.各个被协调的类叫做同事
 * 
 * 调停者模式的有点:
 *	①松散耦合
 *　调停者模式通过把多个同事对象之间的交互封装到调停者对象里面，从而使得同事对象之间松散耦合，基本上可以做到互补依赖。
 * 这样一来，同事对象就可以独立地变化和复用，而不再像以前那样“牵一处而动全身”了。
 *  ②集中控制交互
 *  多个同事对象的交互，被封装在调停者对象里面集中管理，使得这些交互行为发生变化的时候，只需要修改调停者对象就可以了，
 *  当然如果是已经做好的系统，那么就扩展调停者对象，而各个同事类不需要做修改。
 *  ③多对多变成一对多
 *  没有使用调停者模式的时候，同事对象之间的关系通常是多对多的，引入调停者对象以后，调停者对象和同事对象的关系通常变
 *  成双向的一对多，这会让对象的关系更容易理解和实现。
 * 调停者模式的缺点:
 *  ①调停者模式的一个潜在缺点是，过度集中化。如果同事对象的交互非常多，而且比较复杂，当这些复杂性全部集中到调停者的时候，
 * 会导致调停者对象变得十分复杂，而且难于管理和维护。
 */
public class MediatorTest {
	
	public static void main(String[] args) {
		  MainMediator mediator = new MainMediator();
	        //创建同事类
	        CDDriver cd = new CDDriver(mediator);
	        CPU cpu = new CPU(mediator);
	        Video vc = new Video(mediator);
	        Sound sc = new Sound(mediator);
	        //让调停者知道所有同事
	        mediator.setCdDriver(cd);
	        mediator.setCpu(cpu);
	        mediator.setVideo(vc);
	        mediator.setSound(sc);
	        //开始看电影，把光盘放入光驱，光驱开始读盘
	        cd.readCD();
	}
	
}
//调停者对象接口
interface Mediator{
	
	public void changed(Colleague colleague);
}
//同事对象基类
abstract class Colleague{
	
	protected Mediator mediator;

	public Colleague(Mediator mediator) {
		super();
		this.mediator = mediator;
	}

	public Mediator getMediator() {
		return mediator;
	}
	
}
//光驱同事
class CDDriver extends Colleague{
	
	//从光驱获取到的数据
	private String data="";
	
	public CDDriver(Mediator mediator) {
		super(mediator);
	
	}
	public String getData() {
		return data;
	}
	public void readCD(){
		//对象是视频,后面是声音
		this.data="One Price,海贼王我当定了";
		mediator.changed(this);
	}
	
}
//CPU同事
class CPU extends Colleague{

	private String videoData;
	private String soundData;
	
	public String getVideoData() {
		return videoData;
	}

	public void setVideoData(String videoData) {
		this.videoData = videoData;
	}

	public String getSoundData() {
		return soundData;
	}

	public void setSoundData(String soundData) {
		this.soundData = soundData;
	}

	public CPU(Mediator mediator) {
		super(mediator);
	}
	
	 public void executeData(String data){
	        //把数据分解开，前面是视频数据，后面是音频数据
	        String[] array = data.split(",");
	        this.videoData = array[0];
	        this.soundData = array[1];
	        //通知主板，CPU完成工作
	        getMediator().changed(this);
	    }
	 
}

class Video extends Colleague{

	
	public Video(Mediator mediator) {
		super(mediator);
	}
	
	/**
	 * 显示视频数据
	 */
	public void showData(String data){
		System.out.println("你正在收看的是:"+data);
	}

}
class Sound extends Colleague{

	public Sound(Mediator mediator) {
		super(mediator);
	}
	
	/**
	 * 显示声频数据
	 */
	public void showData(String data){
		System.out.println("画外音:"+data);
	}
}
/**
 * 真实的调停者,负责对这些各个组件进行调节,使各个组件能够正确的运行,并且
 * 耦合度不高
 * @author Hotusm
 *
 */
class MainMediator implements Mediator{
	
	private CDDriver cdDriver;
	private CPU cpu;
	private Sound sound;
	private Video video;

	public CDDriver getCdDriver() {
		return cdDriver;
	}
	public void setCdDriver(CDDriver cdDriver) {
		this.cdDriver = cdDriver;
	}
	public CPU getCpu() {
		return cpu;
	}
	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}
	public Sound getSound() {
		return sound;
	}
	public void setSound(Sound sound) {
		this.sound = sound;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	//
	@Override
	public void changed(Colleague colleague) {
		if(colleague instanceof CDDriver){
			this.opeCDDriverReadData(cdDriver);
		}
		if(colleague instanceof CPU){
			this.opeCPU((CPU)colleague);
		}
	}
	
	 private void opeCDDriverReadData(CDDriver cd){
	        //先获取光驱读取的数据
	        String data = cd.getData();
	        //把这些数据传递给CPU进行处理
	        cpu.executeData(data);
	    }
	 
	    /**
	     * 处理CPU处理完数据后与其他对象的交互
	     */
	    private void opeCPU(CPU cpu){
	        //先获取CPU处理后的数据
	        String videoData = cpu.getVideoData();
	        String soundData = cpu.getSoundData();
	        //把这些数据传递给显卡和声卡展示出来
	        video.showData(videoData);
	        sound.showData(soundData);
	    }
	
}












