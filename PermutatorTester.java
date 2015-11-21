
/**
 * Write a description of class PermutatorTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PermutatorTester
{
    
    public static void main(String[] args){
        String[] inArr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        //String[] inArr = new String[]{"0","1"," "};
        Permutator task = new Permutator(3,inArr);
        task.generateToFile("output.txt");
    }
}
