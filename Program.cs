using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OptOblig1
{
    class Program{
        static void Main(string[] args){

            Console.WriteLine("choose amount of cities: ");
            int n = Convert.ToInt32(Console.ReadLine());
            int[,] city_graph = new int[n, n];

            fillArray(n, city_graph);
            printArray(n, city_graph);
        }

        void fillArrayWithZeros() {

        }
        public static void fillArray(int n, int[,] graph){
            Random randomNumber = new Random();
            int number = 0;
            int column = 1;

            for(int row = 0; row < n; row++) {
                while (column < n) {
                    number = randomNumber.Next(1, 11);
                    graph[row, column] = number;
                    graph[column, row] = number;
                    column++;
                }
                column = row + 2;
            }            
        }

        public static void printArray(int n, int[,] graph) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    Console.Write(graph[i, j] + " ");
                }
				Console.WriteLine();
            }
        }
    }
}
