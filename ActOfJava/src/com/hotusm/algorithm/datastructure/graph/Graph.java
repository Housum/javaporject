package com.hotusm.algorithm.datastructure.graph;

public class Graph {
	
	public static final int INFINITY=Integer.MAX_VALUE;
	
	private char[] vexs; //�����
	private int[][] arc; //�ٽӱ�
	private int numVertexes; //������
	private int numEdges;   //����
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
	 * ��ͼ������ȱ���
	 * @description <br/>
	 */
	public void dfsTraverse(){
		boolean[] visited=new boolean[numVertexes]; //��¼�����������
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
	 * ��ͼ���й�ȱ���
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
	 * ������С������(Prim)
	 * @description <br/>
	 */
	public void buildMinimumTreeByPrim(){
		
		int min,i,j,k;
		int[] adjvex=new int[numVertexes];
		int[] lowcost=new int[numVertexes];
		
		/*
		 * �����������������  ��һ�����þ��Ǵ�����Щ�ڵ��Ǳ���������,�������Ļ� ��ô������Ϊ0.
		 * �ڶ������þ�������adjvex ��¼ÿһ���ڵ����ͨ�ڵ�֮��Ȩֵ��С�Ľڵ� (lowcost
		 * ��¼������С���Ǹ�Ȩֵ,adjvex ��¼������С���Ǹ�Ȩֵ)
		 */
		lowcost[0]=0;
		/*
		 * ������Ǵ�������Ǹ���ӦȨֵ�����
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
					k=j;  //�����k���Ǽ�¼0����СȨֵ�Ľڵ���±�
				}
				j++;
			}
			System.out.println(adjvex[k]+" "+k);
			lowcost[k]=0;   //��k�±�ļ�Ϊ0  ��ʾ����k�Ѿ���������  ���ٱ���
			for(j=1;j<numVertexes;j++){
				
				/*
				 *���뿴�����ܼ�  �������������˺ܶ����Ϣ
				 *1.lowcost[j]=arc[k][j]  ���д����� �� k�����Ǳ���������һ����СȨֵ���±�,�������ǽ�Ҫ����
				 *  �ǽ�k����ͨ�ڵ�ȫ������һ��,�ҵ�k��������ͨ�ڵ����СȨֵ���±꣨��ȥ�Ѿ������Ľڵ�)�����������j 
				 *  ��ô����Ϊʲô����Ҫ�ж�һ��arc[k][j]<lowcost[j]��? ������Ϊԭ�ȵ�lowcost�Ѿ���¼��
				 *  һϵ�е���Сֵ(��һЩ����Сֵ���������������ڵ�֮���)�������ԭ������Щ����Ļ�,��ô��������Ҫ�Ž�ȥ,û��Ҫ
				 *  ��Ϊ�㷨�Ǵ���Щѡȡ��С���Ǹ�Ȩֵ��.
				 *  ע��������ʵ�ǽ�������Ϣ��¼������ ���� j��k��j֮ǰ��Ȩֵ
				 *  ����һ����adjvex[j]=k��¼��СȨֵ���±�j�Ǻ�k�������ڵ�  ��������С��Ȩֵ
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
	 * @param serachIndex  �Ѿ��������Ķ���   ��,���зָ�
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
