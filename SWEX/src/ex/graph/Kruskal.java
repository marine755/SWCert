//9월 사전 문제 풀이

package ex.graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Kruskal {

	static class Edge implements Comparable<Edge> {
		int v1;
		int v2;
		int cost;
		
		public Edge(int v1, int v2, int cost) {
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		
		public int compareTo(Edge o) {
			if(this.cost < o.cost) {
				return -1;
			} else if(this.cost == o.cost) {
				return 0;
			} else {
				return 1;
			}
		}		
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x!=y) {
			parent[y] = x;
		}
	}
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	public static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) {
			return true;
		} else {
			return false;
		}
	}

	static int[] parent;
	static int N;
	static int M;
	static int start;
	static int end;
	static ArrayList<Edge> edgeList;
	static Set<Integer> speedset;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int t = 0; t < T ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			edgeList = new ArrayList<Edge>();
			speedset = new HashSet<>();
			
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int speed = Integer.parseInt(st.nextToken());
				edgeList.add(new Edge(a,b,speed));
				speedset.add(speed);
			}
			
			parent = new int[N+1];
			
			Collections.sort(edgeList);
			
			st = new StringTokenizer(br.readLine().trim());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			System.out.println("#" + (t+1) +  " " + minSpeedDiff());			
		}
	}
	
	static void init() {
		for(int i = 1 ; i <= N; i++) {
			parent[i] = i;
		}
	}
	static int findMaxSpeed(int idx) {
		init();
		for (int i = 0 ; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			
			if(edge.cost < list.get(idx))
				continue;
			
			union(edge.v1, edge.v2);
			
			if(isSameParent(start,end)) {
				return edge.cost;
			}
		}
		return Integer.MAX_VALUE;
	}
	
	static int minSpeedDiff() {
		int diff = Integer.MAX_VALUE;
		for(int i = 0 ; i < list.size() ; i++) {
			diff = Math.min(diff, findMaxSpeed(i)-list.get(i));
		}
		return diff;
	}

}
