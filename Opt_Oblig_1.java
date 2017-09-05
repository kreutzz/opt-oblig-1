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

		fillArray(n, city_graph);
		fillArray(n, connections);
		printArray(n, city_graph);
		randomMethod(n, connections, city_graph, route);
		printArray(n, route);
		printArray(n, connections);

	}

	public static void randomMethod(int n, int[] connections, int[][] array, ArrayList route){
		
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

	public static void printArray(int n, int[] connections){
		for(int i = 0; i < n ; i++)
				System.out.println(connections[i]);
	}

	public static void printArray(int n, ArrayList route){
		for(int i = 0; i < n; i++)
			System.out.println("by " + route.get(i) + " til ");
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
			array[i]=0;
	}

	public static void fillArray(int n, int[][] array){

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