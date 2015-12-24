import java.io.*;

public class PermutatorTester
{
    public static void main(String[] args){
        String[] inArr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        //String[] inArr = new String[]{"0","1"," "};
        Permutator bigTask = new Permutator(3,inArr);
        Permutator[] taskArr = bigTask.divide(6);
        
        //start the PrintWriter
        PrintWriter out = null;
        try{
            out = new PrintWriter("output.txt");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        for(int i=0; i<taskArr.length; i++)
            taskArr[i].generateToFile(out);
            
        out.close();
    }
}
