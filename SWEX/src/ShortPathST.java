//최단경로 신장 트리
/* input sample
 * 
 * 노드수, 간선수, 시작점
 * 시작노드,종료노드,간선가중치
 * ...
 * ...
 * 
 * 
4 4 1
0 1 10
1 2 104
2 3 6
0 3 100


4 4 1
0 1 10
1 2 104
2 3 7
0 3 100


4 4 0
0 1 10
1 2 10
2 3 90
0 3 100
*/


//Dijkstra Algorithm

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class ShortPathST {
	private static final int VMAX = 200005, INF = (int) 1e9;
	private static int V, E, S;
	private static long[] D = new long[VMAX]; // 거리 배열
	private static long[] ST = new long[VMAX];
	private static ArrayList<Edge>[] A = new ArrayList[VMAX];

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		for (int i = 0; i < V; i++) {
			A[i] = new ArrayList<>();
			D[i] = INF;
			ST[i] = 0;
		}

		// 간선 정보 읽기
		int s, e, c;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			A[s].add(new Edge(e, c));
			A[e].add(new Edge(s, c));
		}

		solve(S);

//		for (int i = 1; i <= V; i++)
//			sb.append(D[i] == INF ? "INF" : D[i]).append('\n');
//
//		System.out.println(sb.toString());
		long sum = 0;
		for (int i = 0; i < V; i++) {
			System.out.println("ST[" + i + "] = " + ST[i] );
			sum += ST[i];
		}
		System.out.println("sum : " + sum);
	}

	public static void solve(int start) {
		PriorityQueue<Edge> q = new PriorityQueue<>();

		q.add(new Edge(start, 0));
		D[start] = 0;

		while (!q.isEmpty()) {
			Edge cur = q.poll();
			//System.out.println("1 curr : " + cur.vertex + " " + cur.cost);
			if (D[cur.vertex] < cur.cost)
				continue;

			for (Edge next : A[cur.vertex]) {
				if (D[next.vertex] >= cur.cost + next.cost) {
					D[next.vertex] = cur.cost + next.cost;
					q.add(new Edge(next.vertex, D[next.vertex]));
					ST[next.vertex] = next.cost;
//					if(ST[cur.vertex] > next.cost ) {
//						ST[cur.vertex] = next.cost;
//					}
					System.out.println("curr : " + cur.vertex + " " + cur.cost + " next : " + next.vertex + " " + next.cost);
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int vertex;
		long cost;

		public Edge(int vertex, long cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			if (cost > o.cost)
				return 1;
			else if (cost == o.cost)
				return 0;
			else
				return -1;
		}
	}
}