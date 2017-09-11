import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Opt_Oblig_1{

	public static void main(String args[]){

		Scanner read = new Scanner(System.in);

		System.out.println("enter a number: ");
		int n = read.nextInt();					//n = amount of cities

		int[][] city_graph = new int[n][n];
		int[] visited = new int[n];

		ArrayList<Integer> route = new ArrayList<Integer>();
		ArrayList<Integer> randomRoute = new ArrayList<Integer>();
		ArrayList<Integer> iterativRandomRoute = new ArrayList<Integer>();
		ArrayList<Integer> greedyRoute = new ArrayList<Integer>();

		fillArray(n, city_graph);
		fillArray(n, visited);
		printArray(n, city_graph);

		//method call for the random method
		randomMethod(n, visited, city_graph, route);

		//print out graph and cost for random method
		printArray(n, route);
		System.out.println("Kostnaden for Random er: " + calculateCost(n, route, city_graph));
		copyRoute(n, route, randomRoute);

		//mewthod call for the iterative random method
		iterativeRandomMethod(n, visited, city_graph, route);
		copyRoute(n, route, iterativRandomRoute);

		//method call for the greedy method
		greedyMethod(n, visited, city_graph, route);
		copyRoute(n, route, greedyRoute);

		System.out.println("la oss forbedre Random");
		improvementMethod(n, visited, city_graph, randomRoute);

		System.out.println("la oss forbedre Iterativ Random");
		improvementMethod(n, visited, city_graph, iterativRandomRoute);

		System.out.println("la oss forbedre Greedy");
		improvementMethod(n, visited, city_graph, greedyRoute);

	}

	public static void randomMethod(int n, int[] visited, int[][] array, ArrayList<Integer> route){
		Random random = new Random();

		int random_city = random.nextInt(n);
		int counter = n-1;

		visited[random_city] = 1;
		route.clear();
		route.add(random_city);

		while(counter != 0){
			random_city = random.nextInt(n);

			if(visited[random_city] == 0){
				visited[random_city] = 1;
				route.add(random_city);
				counter--;
			}
		}
	}

	public static void iterativeRandomMethod(int n, int[] visited, int[][] array, ArrayList<Integer> route) {
		ArrayList<Integer> temp_route = new ArrayList<Integer>();

		int cost = 0;
		int temp_cost = 0;
		int iterator = 0;

		route.clear();

		while (iterator != 5){
			fillArray(n,visited);
			randomMethod(n, visited, array, temp_route);
			temp_cost = calculateCost(n, temp_route, array);

			if(temp_cost < cost){
				route.clear();
				cost = temp_cost;
				for(int i = 0; i < n ; i++){
					route.add(temp_route.get(i));
				}
				fillArray(n, visited);
			}
			else if(cost == 0){
				cost = temp_cost;
				for(int i = 0; i < n ; i++){
					route.add(temp_route.get(i));
				}
			}
			iterator++;
			temp_route.clear();
		}
		printArray(n,route);
		System.out.println("Kostnaden for Iterativ random er: " + cost);
	}

	public static void greedyMethod(int n,  int[] visited, int[][] array, ArrayList<Integer> route){
		Random random = new Random();

		int min_cost = 11;					//initial cost of 11 as the max cost in the graph is 10
		int current_city = random.nextInt(n);	//choose a random city to start in
		int potential_next_city = 0;		//potential city to connect to next
		int counter = n-1;				//counter to controll while loop

		fillArray(n, visited);		//reset array of visited cities
		route.clear();				//reset route
		route.add(current_city);	//add current city to the route
		visited[current_city] = 1;		//mark that city has been visited/connected

		while(counter != 0){
			for(int i = 0; i < n ; i++){
				if(visited[i] == 0 && array[current_city][i] > 0 && array[current_city][i] < min_cost){
					min_cost = array[current_city][i];
					potential_next_city = i;
				}
			}
			current_city = potential_next_city;
			route.add(current_city);
			visited[current_city] = 1;
			counter--;
			min_cost = 11;
		}
		printArray(n, route);
		System.out.println("Kostnaden for greedy er: " + calculateCost(n, route, array));
	}

	public static void improvementMethod(int n, int[] visited, int[][] array, ArrayList<Integer> route){
		Random random = new Random();
		ArrayList<Integer> temp_route = new ArrayList<Integer>();

		int city_1 = 0;
		int city_2 = 0;
		int iterator = 0;
		int current_cost = calculateCost(n, route, array);
		int new_cost = current_cost;

		while(iterator < 10000000){
			city_1 = random.nextInt(n);
			city_2 = random.nextInt(n);

			while(city_2 == city_1){
				city_2 = random.nextInt(n);
			}

			for(int i = 0; i < n ; i++){
				if(route.get(i) == city_1)
					temp_route.add(city_2);
				else if(route.get(i) == city_2)
					temp_route.add(city_1);
				else
					temp_route.add(route.get(i));
			}

			new_cost = calculateCost(n, temp_route, array);

			if(current_cost > new_cost){
				System.out.println("new_cost er: " + new_cost);
				copyRoute(n, temp_route, route);
				current_cost = new_cost;
			}

			temp_route.clear();
			iterator++;
		}
		System.out.println("ny vei: ");
		printArray(n, route);
		System.out.println("Den nye kostnaden er: " + calculateCost(n, route, array));

	}

	public static int calculateCost(int n, ArrayList<Integer> route, int[][] array){
		int cost = 0;

		for(int i = 0; i < n-1 ; i++){
			cost = cost + array[route.get(i)][route.get(i+1)];
		}
		return cost;
	}

	public static void printArray(int n, int[] visited){
		for(int row = 0; row < n ; row++)
				System.out.println(visited[row]);
	}

	public static void printArray(int n, ArrayList<Integer> route){
		for(int row = 0; row < n; row++){
			if(row == 0)
				System.out.println("\nby " + route.get(row) + " til ");
			else if(row==n-1)
				System.out.println("by " + route.get(row) + "\n");
			else
			System.out.println("by " + route.get(row) + " til ");
		}
	}

	public static void printArray(int n, int[][] array){
		for(int row = 0; row < n ; row++){
			for(int column = 0; column < n ; column++)
				System.out.print(array[row][column] + " ");
			System.out.println();
		}
	}

	public static void fillArray(int n, int[] array){
		for(int row = 0; row < n ; row++)
			array[row] = 0;
	}

	public static void fillArray(int n, int[][] array){
		//fills a two dimensional array with random numbers between 1 and 10

		Random random_number = new Random();
		int temp = 0;

		//fill in array with random number between 1 to 10
		for(int row = 0; row < n ; row++){
			for(int column = 0; column < n; column++){
				if(row == column)
					array[row][column] = 0;
				else if(array[row][column] > 0)
					continue;
				else{
					temp = random_number.nextInt(10) + 1;
					array[row][column] = temp;
					array[column][row] = temp;
				}
			}
		}
	}

	public static void copyRoute(int n, ArrayList<Integer> old_route, ArrayList<Integer> new_route){
		new_route.clear();
		for(int i = 0; i < n; i++){
			new_route.add(old_route.get(i));
		}
	}
}