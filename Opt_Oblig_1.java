import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Opt_Oblig_1{

	public static void main(String args[]){

		Scanner read = new Scanner(System.in);
		System.out.println("enter a number: ");
		int n = read.nextInt();					//n = amount of cities

		int[][] city_graph = new int[n][n];
		int[] connections = new int[n];
		ArrayList<Integer> route = new ArrayList<Integer>();
		ArrayList<Integer> randomRoute = new ArrayList<Integer>();
		ArrayList<Integer> iterativeRandomRoute = new ArrayList<Integer>();
		ArrayList<Integer> greedyRoute = new ArrayList<Integer>();


		fillArray(n, city_graph);
		fillArray(n, connections);
		printArray(n, city_graph);
		randomMethod(n, connections, city_graph, route);
		printArray(n, route);
		printArray(n, connections);
		System.out.println("kostnaden for Random er: " + calculateCost(n, route, city_graph));
		fillArray(n, connections);
		iterativeRandomMethod(n, connections, city_graph, route);
	}

	public static void randomMethod(int n, int[] connections, int[][] array, ArrayList<Integer> route){
		route.clear();
		Random random = new Random();
		int random_city = random.nextInt(n);
		connections[random_city] = 1;
		int counter = n-1;
		route.add(random_city);
		while(counter != 0){
			random_city = random.nextInt(n);
			if(connections[random_city] == 0){
				connections[random_city] = 1;
				route.add(random_city);
				counter--;
			}
		}
	}

	public static void iterativeRandomMethod(int n, int[] connections, int[][] array, ArrayList<Integer> route) {
		route.clear();
		fillArray(n, connections);
		int cost = 0;
		int temp_cost = 0;
		int iterator = 0;
		ArrayList<Integer> temp_route = new ArrayList<Integer>();
		int[][] saved_routes;

		while (iterator != 5){
			fillArray(n,connections);
			randomMethod(n, connections, array, temp_route);
			temp_cost = calculateCost(n, temp_route, array);
			
			if(temp_cost < cost){
				route.clear();
				cost = temp_cost;
				for(int i = 0; i < n ; i++){
					route.add(temp_route.get(i));
				}
				fillArray(n, connections);
			}
			else if(cost == 0){
				cost = temp_cost;
				for(int i = 0; i < n ; i++){
					route.add(temp_route.get(i));
				}
			}
			iterator++;
			printArray(n,route);
			temp_route.clear();
		}
		System.out.println("kostnaden for Iterativ random er: " + cost);
	}

	public static void greedyMethod(){
		
		
	}

	public static int calculateCost(int n, ArrayList<Integer> route, int[][] array){
		int cost = 0;
		
		for(int i = 0; i < n -1; i++){
			cost = cost + array[route.get(i)][route.get(i+1)];
		}
		
		return cost;
	}

	public static void printArray(int n, int[] connections){
		for(int i = 0; i < n ; i++)
				System.out.println(connections[i]);
	}

	public static void printArray(int n, ArrayList<Integer> route){
		for(int i = 0; i < n; i++){
			if(i == 0)
				System.out.println("\nby " + route.get(i) + " til ");
			else if(i==n-1)
				System.out.println("by " + route.get(i) + "\n");
			else
			System.out.println("by " + route.get(i) + " til ");

		}
	}

	public static void printArray(int n, int[][] array){
		for(int i = 0; i < n ; i++){
			for(int j = 0; j < n ; j++)
				System.out.print(array[i][j] + " ");
			System.out.println();
		}
	}

	public static void fillArray(int n, int[] array){
		for(int i = 0; i < n ; i++)
			array[i] = 0;
	}

	public static void fillArray(int n, int[][] array){
		//fills a two dimensional array with random numbers
		Random random_number = new Random();
		int temp = 0;

		//fill in array with random number between 1 to 10
		for(int row = 0; row < n ;row++){
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
}