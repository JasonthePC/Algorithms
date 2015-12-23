import java.lang.Math;
import java.util.ArrayList;
import java.io.*;
/**
 * 
 * @author Jason Paximadas
 */
public class Permutator
{
    // instance variables - replace the example below with your own
    private int chars;
    private String[] symbolsarr;
    private int startpoint;
    private int endpoint;
    private int symbols;

    /**
     * Task object for permutator
     */
    public Permutator(int inputchars, String[] inArr, int inputstartpoint, int inputendpoint){
        this.chars = inputchars;
        this.symbolsarr = inArr;
        this.startpoint = inputstartpoint;
        this.endpoint = inputendpoint;
        this.symbols = this.symbolsarr.length;
        
    }
    
    /**
     * Constructs a task object for permutator assuming that all the premutations in a given String array for a given number of character is desired
     */
    public Permutator(int inputchars, String[] inArr){
        this.chars = inputchars;
        this.symbolsarr=  inArr;
        this.startpoint = 0;
        this.symbols = this.symbolsarr.length;
        this.endpoint = (int) Math.pow(this.symbols,this.chars)-1;
    }
    
    public void divide(){
        
    }
    
    /**
     * Computes the size of a file in bytes that would be generated if the task object was generated to a file
     * @return filesize in bytes
     */
    public int estimateFileSize(){
        return (this.endpoint-this.startpoint+1) * this.chars;
    }
    
    /**
     * Ignore object startpoint and endpoint data and rely on user for pos.
     * NOTE: Still needs symbol array and char number from object data!
     * @param pos the input to the algorithm
     * @return the output of the algorithm at given pos
     */
    public String generate(int pos){
        return algorithm(pos);
    }
    
    /**
     * Perform the task object and write the output to a file, inserting newlines between each output.
     * @param file the file for the data to be written to, given as a string. If no path is given, current directory is used
     */
    public void generateToFile(String file){
        PrintWriter out = null;
        try{
            out = new PrintWriter(file);
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        for(int i=this.startpoint; i<=endpoint; i++){
            out.println(this.algorithm(i));
        }
        out.close();
    }
    
    /**
     * An algorithm that generates a
     */
    private String algorithm(int pos){
        //convert seedInt to base symbols number
        String seedString = Integer.toString(pos,this.symbols);
        //create an array for each character of seedString to be stored in as int
        ArrayList<Integer> intarr = new ArrayList<Integer>();
            
            for(int i=0;i<seedString.length();i++){
                //specfy a letter of seedString
                String cur=seedString.substring(i,i+1);
                //convert cur to a base 10 integer
                int curint =Integer.valueOf(cur,this.symbols);
                //append to the intarr
                intarr.add(curint);
            }
            
            //push zeros to the intarr to make the rest of the permutation
            while(intarr.size()<this.chars){
                intarr.add(0,0);
            }
            
            //convert the numbers in intarr to symbols
            String output = "";
            for(int i=0;i<=intarr.size()-1;i++){
                output = output + this.symbolsarr[intarr.get(i)];
            }
            
            return output;
    }
}