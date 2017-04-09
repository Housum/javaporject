package com.hotusm.thread.detail.start.project.csdn.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

import com.hotusm.thread.detail.start.Log;

public class OneTrack {

	 private static final String[] PLAYERNAMES = new String[]{"����ʥ��ʿ","�ƽ�ʥ��ʿ"
	        ,"��ͭʥ��ʿ","��ʿ","ڤ��ʿ","����˹","������","�����","����","������","������"};

	    /**
	     * �������У����̰߳�ȫ��
	     */
	    private List<Player> signupPlayers = new LinkedList<Player>();

	    /**
	     * ����������У��������ܣ����̰߳�ȫ��
	     */
	    private PriorityBlockingQueue<Player> preliminaries = new PriorityBlockingQueue<Player>();

	    /**
	     * ����������У��������ܣ����̰߳�ȫ��
	     */
	    private PriorityBlockingQueue<Player> finals = new PriorityBlockingQueue<Player>();

	    public void track() {
	        /*
	         * ���ܷ�Ϊ���¼����׶ν��У�
	         * 
	         * 1������
	         * 2��������10��ѡ�֣��ֳ����飬ÿ��5��ѡ�֡�
	         * �����ν��г�������Ϊ����ֻ��5��������ֻ���õ�������ɵĲ���ʹ�����������б�����
	         * 
	         * 3�������������������д�뵽һ�������н�������ֻ�гɼ���õ�ǰ����ѡ�֣����ԲμӾ�����
	         * 
	         * 4�����������ǰ�������ֱ���Ϊ���Ǽ�������������
	         * */

	        //1��================����
	        // ������ܵ���������˵��ֻ��5���ܵ�������ֻ��5��permits��
	       // Semaphore runway = new Semaphore(5);
	     //   CountDownLatch gun=new CountDownLatch(5);
	        this.signupPlayers.clear();
//	        for(int index = 0 ; index < OneTrack.PLAYERNAMES.length ; ) {
//	            Player player = new Player(OneTrack.PLAYERNAMES[index], ++index , runway,gun);
//	            this.signupPlayers.add(player);
//	        }
	        //-------����5�˽��з���-----------
	        List<CountDownLatch> guns=new LinkedList<CountDownLatch>();
	        List<Semaphore> runways=new LinkedList<Semaphore>();
	        int count=0;
	        if(OneTrack.PLAYERNAMES.length%5==0){
	        	count=OneTrack.PLAYERNAMES.length/5;
		        for(int i=0;i<count;i++){
		        	guns.add(new CountDownLatch(5));
		        	runways.add(new Semaphore(5));
		        }
	        }else{
	        	count=OneTrack.PLAYERNAMES.length/5+1;
		        if(count==1){
		        	guns.add(new CountDownLatch(OneTrack.PLAYERNAMES.length%5));
		        }else{
		        	 for(int i=0;i<count-1;i++){
				        	guns.add(new CountDownLatch(5));
				        	runways.add(new Semaphore(5));
				       }
		        	 guns.add(new CountDownLatch(OneTrack.PLAYERNAMES.length%5));
		        	 runways.add(new Semaphore(OneTrack.PLAYERNAMES.length%5));
		        }
		        
	        }
	        Log.info("һ���ֳ�:"+count+"��");
	        
	        
	        for(int index = 0,step=0,gunNum=0 ; index < OneTrack.PLAYERNAMES.length ;step++) {
	            //Player player = new Player(OneTrack.PLAYERNAMES[index], ++index , runway,null);
	           // this.signupPlayers.add(player);
	        	//step������ʾ�����ǵڼ�������  gunNum��ʾ���ǵڼ���ǹ�͵ڼ����ź���  5�ı�����ʱ���ʹ���µ��ź�ǹ
	        	
	        	/**
	        	 * �������ʹ�������һ�ַ���  ����ʹ��ͬһ���ź�����ź�ǹ
	        	 * ����������5���û�֮�� ���Ͽ�ʼ����  signupPlayers����5֮��  
	        	 * �ڱ�������Ժ�  Ȼ���ڼ�5�����н���  �������ܹ�ʹ��֮ǰ���ź�ǹ���ź�����
	        	 */
	        	if(step%5==0&&step!=0){
	        		gunNum++;
	        	}
	        	Player player = new Player(OneTrack.PLAYERNAMES[index], ++index , runways.get(step),guns.get(step));
	        	this.signupPlayers.add(player);
	        }
	        
	        //2��================���г���
	        // ���ǲ���
	        ExecutorService refereeService = Executors.newFixedThreadPool(5);
	        for (final Player player : this.signupPlayers) {
	            Future<Result> future = null;
	            future = refereeService.submit(player);
	            new FutureThread(future, player, this.preliminaries).start();
	        }
	        //! ֻ�е�PLAYERNAMES.lengthλѡ�ֵĳɼ��������ˣ����ܽ�������������Ҫ
	        while(this.preliminaries.size() < OneTrack.PLAYERNAMES.length) {
	            try {
	                synchronized (this.preliminaries) {
	                    this.preliminaries.wait();
	                }
	            } catch(InterruptedException e) {
	                e.printStackTrace(System.out);
	            }
	        }
	        
	        Log.info("�������� ���濪ʼ����");
	        
	        //------��Ϊǰ����˶�����ͬһ�����  ��������Ҫ��������ͬһ�����  ����ͬһ���ź���-------
	        CountDownLatch cdl=null;
	        Semaphore runway=null;
	        if(OneTrack.PLAYERNAMES.length<=5){
	        	cdl=new CountDownLatch(OneTrack.PLAYERNAMES.length);
	        	runway=new Semaphore(OneTrack.PLAYERNAMES.length);
	        }else{
	        	cdl=new CountDownLatch(5);
	        	runway=new Semaphore(5);
	        }
	        // 3��============����(ֻ�г��������ǰ5�����Բμ�)
	        for(int index = 0 ; index < 5 ; index++) {
	            Player player = this.preliminaries.poll();
	            player.setRunway(runway);
	            player.setStartGun(cdl);
	            Future<Result> future = null;
	            future = refereeService.submit(player);
	            new FutureThread(future, player, this.finals).start();
	        }
	        //! ֻ�е�5λѡ�ֵľ����ɼ��������ˣ����ܵ���һ���������ɼ�
	        while(this.finals.size() < 5) {
	            try {
	                synchronized (this.finals) {
	                    this.finals.wait();
	                }
	            } catch(InterruptedException e) {
	                e.printStackTrace(System.out);
	            }
	        }

	        // 4��============���������ɼ���ǰ������
	        for(int index = 0 ; index < 3 ; index++) {
	            Player player = this.finals.poll();
	            switch (index) {
	            case 0:
	                System.out.println("��һ����"  + player.getName() + "[" + player.getNumber() + "]���ɼ���" + player.getResult().getTime() + "��");
	                break;
	            case 1:
	                System.out.println("�ڶ�����"  + player.getName() + "[" + player.getNumber() + "]���ɼ���" + player.getResult().getTime() + "��");
	                break;
	            case 2:
	                System.out.println("��������"  + player.getName() + "[" + player.getNumber() + "]���ɼ���" + player.getResult().getTime() + "��");
	                break;
	            default:
	                break;
	            }
	        }
	    }

	    public static void main(String[] args) throws RuntimeException {
	        new OneTrack().track();
	    }
}
