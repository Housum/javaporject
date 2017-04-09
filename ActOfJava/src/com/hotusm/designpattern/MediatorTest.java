package com.hotusm.designpattern;

/**
 * 
 * @author Hotusm
 * ���ģʽ->��ͣ��ģʽ
 * 1.��ͣ��ģʽ�Ƕ������Ϊģʽ����ͣ��ģʽ��װ��һϵ�ж����໥���õķ�ʽ��
 * ʹ����Щ���󲻱��໥�������á��Ӷ�ʹ���ǿ��Խ���ɢ����ϡ�����Щ�����е�ĳЩ
 * ����֮����໥���÷����ı�ʱ����������Ӱ�쵽������һЩ����֮����໥���á��Ӷ���
 * ֤��Щ�໥���ÿ��Ա˴˶����ر仯��
 * 2.ͨ�׵Ľ�������һ�����ĵĵ����������и�����֮���Э��,�����ǽ�������ֱ�ӵ����,����д������
 * �����������ϵ�
 * 3.���������,�Կ�,CPU֮��������ֹ�ϵ:���帺�������Щ�����ĺ���,������ֱ��ͨ������֮�����ã�ͼƬ:��ͣ��ģʽ2��
 * 4.������Э���������ͬ��
 * 
 * ��ͣ��ģʽ���е�:
 *	����ɢ���
 *����ͣ��ģʽͨ���Ѷ��ͬ�¶���֮��Ľ�����װ����ͣ�߶������棬�Ӷ�ʹ��ͬ�¶���֮����ɢ��ϣ������Ͽ�����������������
 * ����һ����ͬ�¶���Ϳ��Զ����ر仯�͸��ã�����������ǰ������ǣһ������ȫ���ˡ�
 *  �ڼ��п��ƽ���
 *  ���ͬ�¶���Ľ���������װ�ڵ�ͣ�߶������漯�й���ʹ����Щ������Ϊ�����仯��ʱ��ֻ��Ҫ�޸ĵ�ͣ�߶���Ϳ����ˣ�
 *  ��Ȼ������Ѿ����õ�ϵͳ����ô����չ��ͣ�߶��󣬶�����ͬ���಻��Ҫ���޸ġ�
 *  �۶�Զ���һ�Զ�
 *  û��ʹ�õ�ͣ��ģʽ��ʱ��ͬ�¶���֮��Ĺ�ϵͨ���Ƕ�Զ�ģ������ͣ�߶����Ժ󣬵�ͣ�߶����ͬ�¶���Ĺ�ϵͨ����
 *  ��˫���һ�Զ࣬����ö���Ĺ�ϵ����������ʵ�֡�
 * ��ͣ��ģʽ��ȱ��:
 *  �ٵ�ͣ��ģʽ��һ��Ǳ��ȱ���ǣ����ȼ��л������ͬ�¶���Ľ����ǳ��࣬���ұȽϸ��ӣ�����Щ������ȫ�����е���ͣ�ߵ�ʱ��
 * �ᵼ�µ�ͣ�߶�����ʮ�ָ��ӣ��������ڹ����ά����
 */
public class MediatorTest {
	
	public static void main(String[] args) {
		  MainMediator mediator = new MainMediator();
	        //����ͬ����
	        CDDriver cd = new CDDriver(mediator);
	        CPU cpu = new CPU(mediator);
	        Video vc = new Video(mediator);
	        Sound sc = new Sound(mediator);
	        //�õ�ͣ��֪������ͬ��
	        mediator.setCdDriver(cd);
	        mediator.setCpu(cpu);
	        mediator.setVideo(vc);
	        mediator.setSound(sc);
	        //��ʼ����Ӱ���ѹ��̷��������������ʼ����
	        cd.readCD();
	}
	
}
//��ͣ�߶���ӿ�
interface Mediator{
	
	public void changed(Colleague colleague);
}
//ͬ�¶������
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
//����ͬ��
class CDDriver extends Colleague{
	
	//�ӹ�����ȡ��������
	private String data="";
	
	public CDDriver(Mediator mediator) {
		super(mediator);
	
	}
	public String getData() {
		return data;
	}
	public void readCD(){
		//��������Ƶ,����������
		this.data="One Price,�������ҵ�����";
		mediator.changed(this);
	}
	
}
//CPUͬ��
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
	        //�����ݷֽ⿪��ǰ������Ƶ���ݣ���������Ƶ����
	        String[] array = data.split(",");
	        this.videoData = array[0];
	        this.soundData = array[1];
	        //֪ͨ���壬CPU��ɹ���
	        getMediator().changed(this);
	    }
	 
}

class Video extends Colleague{

	
	public Video(Mediator mediator) {
		super(mediator);
	}
	
	/**
	 * ��ʾ��Ƶ����
	 */
	public void showData(String data){
		System.out.println("�������տ�����:"+data);
	}

}
class Sound extends Colleague{

	public Sound(Mediator mediator) {
		super(mediator);
	}
	
	/**
	 * ��ʾ��Ƶ����
	 */
	public void showData(String data){
		System.out.println("������:"+data);
	}
}
/**
 * ��ʵ�ĵ�ͣ��,�������Щ����������е���,ʹ��������ܹ���ȷ������,����
 * ��϶Ȳ���
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
	        //�Ȼ�ȡ������ȡ������
	        String data = cd.getData();
	        //����Щ���ݴ��ݸ�CPU���д���
	        cpu.executeData(data);
	    }
	 
	    /**
	     * ����CPU���������ݺ�����������Ľ���
	     */
	    private void opeCPU(CPU cpu){
	        //�Ȼ�ȡCPU����������
	        String videoData = cpu.getVideoData();
	        String soundData = cpu.getSoundData();
	        //����Щ���ݴ��ݸ��Կ�������չʾ����
	        video.showData(videoData);
	        sound.showData(soundData);
	    }
	
}












