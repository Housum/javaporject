package com.hotusm.thread.detail.start.project.myself;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * 比赛类
 *
 */
public class Game {

	@Test
	public void game1(){
		
		Player p0=new Player();
		p0.setName("h1");
		p0.setSex("max");
		p0.setSpeed(10);
		
		Player p1=new Player();
		p1.setName("h2");
		p1.setSex("max");
		p1.setSpeed(9);
		
		Player p2=new Player();
		p2.setName("h3");
		p2.setSex("max");
		p2.setSpeed(11);
		
		Player p3=new Player();
		p3.setName("h4");
		p3.setSex("max");
		p3.setSpeed(11);
		
		Referee referee=new Referee();
		referee.setName("boss");
		List<Player> players=new ArrayList<Player>();
		players.add(p0);
		players.add(p1);
		players.add(p2);
		players.add(p3);
		game(players,referee);
		
	}
	
	public void game(List<Player> players,Referee referee){
		CountDownLatch countDownLatch=new CountDownLatch(players.size());
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10, 5, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
		
		for(Player p:players){
			Track track=new Track(p, referee, 100, countDownLatch);
			executor.submit(track);
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Log.info("========比赛结束了=======");
		for(Map.Entry<Player, Result> entry:referee.getResult().entrySet()){
			Log.info(entry.getKey().getName()+":"+entry.getValue().getSeconds());
		}
	}
	
}
