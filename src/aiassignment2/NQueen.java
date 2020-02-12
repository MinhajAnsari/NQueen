
package aiassignment2;

import java.util.Arrays;
import java.util.Random;

public class NQueen {


    public static void main(String[] args) {
        //index of array queen are columns which are constant
        //value at the indexed queen are rows which are checked according to constraints
        //this arrangment of array queens is used in backtracking and genetic algorithm both
        
        int[] queen = new int[8];
        Random rand=new Random();
        int r=rand.nextInt(7);
        
        queen[0]=r;
        placed(queen,1);
        
        //Printing Answer for Backtracking
        System.out.println("Bactracking: ");
        for(int i=0; i<8; i++){
            System.out.print(" "+queen[i]+" ");
        }
        
        
        
        
        
        int[][] PopChromo = new int[4][8];
        int fitness[]={1,1,1,1};
        PopChromo=MakePopulation(PopChromo);
        
        while(fitness[0]!=1){        //Loop will continue until fitness=0;
            for(int i=0; i<4; i++)
                fitness[i]=0;
            
            fitness=FitnessFunction(PopChromo, fitness);
            PopChromo=SortPop(PopChromo, fitness);
            PopChromo=CrossOver(PopChromo);
            PopChromo=Mutation(PopChromo);            
        }
        
        //Printing Answer for Genetic Algorithm
        System.out.println("\n\nGenetic Algorithm: ");
        for(int j=0; j<8; j++)
            System.out.print(" "+PopChromo[0][j]+" ");
        
        System.out.println("");
    }

    
    
    
    
    
    
   //Functions for Bactracking 
   public static boolean placed(int[] queen, int col){
        if(col==8){
            return true;
        }
        
        boolean QPlace=false;
        
        for(int row=0; row<8; row++){
            queen[col]=row;
            
            if(NoAttack(queen, col))
                QPlace=placed(queen, col+1);
            
            if(QPlace)
                return true;
        }
        col--;
        return false;   //Backtracking
    }
    
    
    public static boolean NoAttack(int[] queen, int col){
        for(int i=0; i<=col; i++){
            for(int j=i+1; j<=col; j++){    
                if(queen[i]==queen[j])                                                  //For Checking Rows
                    return false;
                if(queen[i] == (queen[j]-(j-i)) || queen[i] == (queen[j]+(j-i)))        //For Checking diagonals
                    return false;
            }
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    //Functions for Genetic Algorithm
    public static int[][] MakePopulation(int[][] PopChromo){
        Random rand=new Random();
        int Queen;
        
        for(int Population=0; Population<4; Population++){
            for(int Chromosome=0; Chromosome<8; Chromosome++){
                Queen=rand.nextInt(7);
                PopChromo[Population][Chromosome]=Queen;
            }
        }
        return PopChromo;
    }
    
    
    public static int[] FitnessFunction(int[][] PopChromo, int[] fitness){
        for(int i=0; i<4; i++){
            for(int j=0; j<8; j++){
                for(int k=j+1; k<8; k++){
                    if(PopChromo[i][j]==PopChromo[i][k])            //For Checking Rows
                        fitness[i]++;
                    if(PopChromo[i][j] == (PopChromo[i][k]-(k-j)) || PopChromo[i][j] == (PopChromo[i][k]+(k-j)))    //For Checking diagonals
                        fitness[i]++;       
                        //Attaciing pairs of queen = fitness (fitness should be 0)
                }
            }
        }
        return fitness;
    }
    
    
    public static int[][] SortPop(int[][] PopChromo, int[] fitness){
        for(int i=0; i<3; i++){
            for(int j=0; j<(3-i); j++){
                if(fitness[j] > fitness[j+1]){
                    int temp[]=PopChromo[j];
                    PopChromo[j]=PopChromo[j+1];
                    PopChromo[j+1]=temp;
                }
            }
        }
        return PopChromo;
    }
    

    public static int[][] CrossOver(int[][] PopChromo){
        Random rand=new Random();
        int CrossPoint=rand.nextInt(6);
        int temp[][]=PopChromo;
        
        for(int j=0; j<CrossPoint; j++){
            PopChromo[0][j]=temp[1][j];
            PopChromo[1][j]=temp[0][j];
            PopChromo[2][j]=temp[0][j];
            
            temp[0][j]=temp[2][j];
            PopChromo[3][j]=temp[0][j];
        }
        
        return PopChromo;
    }

    
    public static int[][] Mutation(int[][] PopChromo){
        Random rand=new Random();
        
        int Population=rand.nextInt(3);
        int Chromosome=rand.nextInt(7);
        int Queen=rand.nextInt(7);
        
        PopChromo[Population][Chromosome]=Queen;
        
        return PopChromo;
    }  
}


