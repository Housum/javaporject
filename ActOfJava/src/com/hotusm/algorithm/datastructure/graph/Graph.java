package com.hotusm.algorithm.datastructure.graph;

public class Graph {
	
	public static final int INFINITY=Integer.MAX_VALUE;
	
	private char[] vexs; //顶点表
	private int[][] arc; //临接表
	private int numVertexes; //顶点数
	private int numEdges;   //边树
	public char[] getVexs() {
		return vexs;
	}
	public void setVexs(char[] vexs) {
		this.vexs = vexs;
	}
	public int[][] getArc() {
		return arc;
	}
	public void setArc(int[][] arc) {
		this.arc = arc;
	}
	public int getNumVertexes() {
		return numVertexes;
	}
	public void setNumVertexes(int numVertexes) {
		this.numVertexes = numVertexes;
	}
	public int getNumEdges() {
		return numEdges;
	}
	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}
	/**
	 * 对图进行深度遍历
	 * @description <br/>
	 */
	public void dfsTraverse(){
		boolean[] visited=new boolean[numVertexes]; //记录顶点表访问情况
		for(int i=0;i<numVertexes;i++){
			visited[i]=false;
		}
		for (int i = 0; i < numVertexes; i++) {
			if(!visited[i]){
				dfsTraverse(i, visited);
			}
		}
	}
	/**
	 * 对图进行广度遍历
	 * @description <br/>
	 */
	public void hfsTraverse(){
		boolean[] visited=new boolean[numVertexes];
		for(int i=0;i<numVertexes;i++){
			visited[i]=false;
		}
		for(int i=0;i<numVertexes;i++){
			if(!visited[i]){
				hfsTraverse(i+",", visited);
			}
		}
		
	}
	
	/**
	 * 构建最小二叉树(Prim)
	 * @description <br/>
	 */
	public void buildMinimumTreeByPrim(){
		
		int min,i,j,k;
		int[] adjvex=new int[numVertexes];
		int[] lowcost=new int[numVertexes];
		
		/*
		 * 这个数组有两个作用  第一个作用就是储存哪些节点是被遍历过的,遍历过的话 那么就设置为0.
		 * 第二个作用就是联合adjvex 记录每一个节点和连通节点之间权值最小的节点 (lowcost
		 * 记录的是最小的那个权值,adjvex 记录的是最小的那个权值)
		 */
		lowcost[0]=0;
		/*
		 * 这个就是存放上面那个对应权值的序号
		 * */
		adjvex[0]=0;
		
		for(i=0;i<numVertexes;i++){
			lowcost[i]=arc[0][i];
			adjvex[i]=0;
		}
		
		for(i=1;i<numVertexes;i++){
			
			min=INFINITY;
			j=1;k=0;
			while(j<numVertexes){
				if(lowcost[j]!=0&&lowcost[j]<min){
					min=lowcost[j];
					k=j;  //这里的k就是记录0到最小权值的节点的下标
				}
				j++;
			}
			System.out.println(adjvex[k]+" "+k);
			lowcost[k]=0;   //将k下标的记为0  表示的是k已经遍历过了  不再遍历
			for(j=1;j<numVertexes;j++){
				
				/*
				 *代码看起来很简单  但是这里隐藏了很多的信息
				 *1.lowcost[j]=arc[k][j]  这行代码中 ： k是我们遍历过的上一次最小权值的下标,这里我们将要做的
				 *  是将k的连通节点全部遍历一遍,找到k和它的连通节点的最小权值的下标（除去已经遍历的节点)，就是下面的j 
				 *  那么我们为什么还需要判断一下arc[k][j]<lowcost[j]呢? 这是因为原先的lowcost已经记录了
				 *  一系列的最小值(这一些列最小值可能是其他两个节点之间的)，如果比原本的那些都大的话,那么根本不需要放进去,没必要
				 *  因为算法是从那些选取最小的那个权值的.
				 *  注意这里其实是将两个信息记录了下来 坐标 j和k和j之前的权值
				 *  最后的一步是adjvex[j]=k记录最小权值的下标j是和k这两个节点  构成了最小的权值
				 */
				if(lowcost[j]!=0&&arc[k][j]<lowcost[j]){
					lowcost[j]=arc[k][j];  
					adjvex[j]=k;
				}
			}
		}
	}
	/**
	 * 
	 * @description <br/> 
	 * @param serachIndex  已经遍历过的顶点   用,进行分割
	 * @param visited
	 */
	private void hfsTraverse(String searchIndexs,boolean[] visited){
		
		String[] strIndexs=searchIndexs.split(",");
		int[] need2Search=new int[strIndexs.length];
		int i=0;
		for(String index:strIndexs){
			if(index.equals("")){
				continue;
			}
			need2Search[i]=Integer.valueOf(index);
			if(!visited[Integer.valueOf(index)]){
				visited[i]=true;
			}
			i++;
		}
		for(int index:need2Search){
			System.out.print(vexs[index]);
		}
		StringBuffer sb=new StringBuffer();
		for(int j=0;j<i;j++){
			for(int k=0;k<numVertexes;k++){
				//
				if(arc[need2Search[j]][k]!=0&&!visited[k]
						&&arc[need2Search[j]][k]!=INFINITY){
					sb.append(k).append(",");
					visited[k]=true;
				}
			}
		}
		
		if(!sb.toString().equals("")){
			hfsTraverse(sb.toString(), visited);
		}
		
	}
	private void dfsTraverse(int serachIndex,boolean[] visited){
		
		visited[serachIndex]=true;
		System.out.print(vexs[serachIndex]); 
		for(int i=0;i<numVertexes;i++){
			if(arc[serachIndex][i]==1&&!visited[i]){
				dfsTraverse(i, visited);
			}
		}
	}
	
	
	public static void main(String[] args) {
		int[][] arc=new int[4][4];
		
		arc[0][1]=3;
		arc[0][2]=INFINITY;
		arc[0][3]=4;
		
		arc[1][0]=3;
		arc[1][2]=5;
		arc[1][3]=INFINITY;
		
		arc[2][0]=INFINITY;
		arc[2][1]=5;
		arc[2][3]=6;
		
		arc[3][0]=4;
		arc[3][1]=INFINITY;
		arc[3][2]=6;
		
//		arc[4][0]=1;
//		arc[4][3]=1;
		
		Graph aGraph=new Graph();
		aGraph.setArc(arc);
		aGraph.setNumEdges(4);
		aGraph.setNumVertexes(4);
		aGraph.setVexs(new char[]{'a','b','c','d'});
		
		aGraph.dfsTraverse();
		System.out.println();
		aGraph.hfsTraverse();
		System.out.println();
		aGraph.buildMinimumTreeByPrim();
		
	}
	
}
