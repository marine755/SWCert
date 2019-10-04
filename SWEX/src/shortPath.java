
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class shortPath {
	private static final int VMAX = 200005, INF = (int) 1e9;
	private static int V, E, S;
	private static long[] D = new long[VMAX];
	private static ArrayList<Edge>[] A = new ArrayList[VMAX];

	public static void main(String[] args) throws Exception {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			StringBuffer sb = new StringBuffer();
			StringTokenizer st = new StringTokenizer(in.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= V; i++) {
				A[i] = new ArrayList<>();
				D[i] = INF;
			}

			S = Integer.parseInt(in.readLine());

			int s, e, c;
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				A[s].add(new Edge(e, c));
			}

			solve(S);

			for (int i = 1; i <= V; i++)
				sb.append(D[i] == INF ? "INF" : D[i]).append('\n');

			System.out.println(sb.toString());
		}
	}

	public static void solve(int start) {
		PriorityQueue<Edge> q = new PriorityQueue<>();

		q.add(new Edge(start, 0));
		D[start] = 0;

		while (!q.isEmpty()) {
			Edge cur = q.poll();

			if (D[cur.vertex] < cur.cost)
				continue;

			for (Edge next : A[cur.vertex]) {
				if (D[next.vertex] > cur.cost + next.cost) {
					D[next.vertex] = cur.cost + next.cost;
					q.add(new Edge(next.vertex, D[next.vertex]));
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