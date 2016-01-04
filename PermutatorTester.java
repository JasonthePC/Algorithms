import java.io.*;

public class PermutatorTester
{
    public static void main(String[] args){
        String[] inArr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        //String[] inArr = new String[]{"0","1"," "};
        
        //start the PrintWriter
        PrintWriter out1 = null;
        try{
            out1 = new PrintWriter("multithread4letters-1.txt");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        PrintWriter out2 = null;
        try{
            out2 = new PrintWriter("multithread4letters-2.txt");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        PrintWriter out3 = null;
        try{
            out3 = new PrintWriter("multithread4letters-3.txt");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        PrintWriter out4 = null;
        try{
            out4 = new PrintWriter("multithread4letters-4.txt");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        PrintWriter[] writerArr = {out1,out2,out3,out4}; //push all the PrintWriters to an array
        Permutator bigTask = new Permutator(4,inArr,"4letters"); //create the task
        Permutator[] taskArr = bigTask.divide(4); //divy up the work across 4 task objects
        
        //give it a PrintWriter and start the task
        for(int i=0;i<4;i++){
            taskArr[i].setPrintWriter(writerArr[i]);
            taskArr[i].start();
            
        }
    }
}
