import java.util.ArrayList;
import java.io.PrintWriter;

/**
 * The Permutator class is meant to generate every possible permutation of a list of symbols of a certain length.
 * (e.g. aaa,aab,aac,aad, ... ,zzz)
 * <p>
 * Generating the output Strings is achieved with the algorithm() method.
 * Permutator objects contain the bounds of the range of integers the algorithm() method operates on.
 * Since Permutator objects outline the "task to be completed" they are referred to in teh documentation as "task objects".
 * Although the algorithm() method is public, the generateToFile() and generateToConsole() methods allow for the execution of a task object and the storing of the output.
 * The divide() method is provided to split up task objects into many smaller task objects for parrallel processing.
 * 
 * @author Jason Paximadas
 */
public class Permutator extends Thread
{
    private int chars; //numbres of symbols from the array to be chosen
    private String[] symbolsarr; //the array of symbols
    private int startpoint; //first number to be processed
    private int endpoint; //last number to be processed
    private int symbols; //length of the symbols array
    private Thread t;
    private String taskName;
    private PrintWriter out;

    /**
     * Creates a task object and makes no assumptions about the start and endpoint.
     * @param inputchars The number of characters chosen from the list of symbols
     * @param inArr The array of symbols
     * @param inputstartpoint The beginning of the iteration
     * @param inputendpoint The end of the iteration
     * PRECONDITION: Startpoint and endpoint must encompass a range of whole numbers
     */
    public Permutator(int inputchars, String[] inArr, int inputstartpoint, int inputendpoint, String inputTaskName){
        this.chars = inputchars;
        this.symbolsarr = inArr;
        this.startpoint = inputstartpoint;
        this.endpoint = inputendpoint;
        this.symbols = this.symbolsarr.length;
        this.taskName = inputTaskName;
    }
    
    /**
     * Constructs a task object assuming that all the premutations in a given String array for a given number of character is desired.
     * @param inputchars The number of characters chosen from the list of symbols
     * @param inArr The array of symbols
     * PRECONDITION: Startpoint and endpoint must encompass a range of whole numbers
     */
    public Permutator(int inputchars, String[] inArr, String inputTaskName){
        this.chars = inputchars;
        this.symbolsarr=  inArr;
        this.startpoint = 0; //assumes startpoint if every permutation was to be generated
        this.symbols = this.symbolsarr.length; //stores length of the array
        this.endpoint = (int) Math.pow(this.symbols,this.chars)-1; //assumes the endpoint if every permutation was to be generated
        this.taskName = inputTaskName;
    }
    
    /**
     * Divides the task object into many smaller task objects.
     * @param portions The number of new task objects to create from the initial one
     * @return The array of new task objects
     * PRECONDITION: portions must be >1
     */
    public Permutator[] divide(int portions){
        int computations = this.endpoint-this.startpoint+1; //finds total computations encompassed by task object
        int compsdivided = 0;  //records how many computations have been reallocated
        Permutator[] returnArr = new Permutator[portions]; //creates an array of task objects of portions size
        int curdiv; //declares the curdiv variable to be used later
        for(int i = portions; i > 0;i--){
            curdiv = computations/i; //slices off the next set of computations to be reallocated
            computations -= curdiv;//substracts computations to be reallocated from unallocated computations
            returnArr[portions-i] = new Permutator(this.chars,this.symbolsarr,compsdivided,compsdivided+curdiv-1,this.taskName+"-"+(portions-i));//reallocates computations by computing new startpoint and endpoint and transferring object data
            compsdivided += curdiv;//records the number of reallocated computations
        }
        return returnArr; //returns the array
    }
    
    public void setPrintWriter(PrintWriter toBeSet){
        this.out = toBeSet;
    }
    
    /**
     * Computes the size of a file in bytes that would be generated if the task object was generated to a file.
     * @return filesize in bytes
     */
    public int estimateFileSize(){
        return (this.endpoint - this.startpoint+1) * this.chars; //assumes each character takes up a byte of space
    }
    
    /**
     * Perform the task object and display the output on the console.
     */
    public void printToConsole(){
        for(int i = this.startpoint; i <= this.endpoint; i++) //iterates over all the integers specified by the task object
            System.out.println(this.algorithm(i)); //prints the output of the algorithm to the console
    }
    
    public void printToFile(){
        for(int i = this.startpoint; i <= this.endpoint; i++){ //iterates over all the integers specified by the task object
            this.out.println(this.algorithm(i)); //uses the PrintWriter to write the output of the algorithm to the specified file
        }
    }
    
    /**
     * Perform the task object and write the output to a file, inserting newlines between each output.
     * @param The PrintWriter object that will write to the file
     */
    public void run(){      
        for(int i = this.startpoint; i <= this.endpoint; i++){ //iterates over all the integers specified by the task object
            this.out.println(this.algorithm(i)); //uses the PrintWriter to write the output of the algorithm to the specified file
        }
        System.out.println(taskName + " is done.");
    }
    
    public void start(){
        if(t==null){
            System.out.println("Starting " + this.taskName);
            t = new Thread(this, this.taskName);
            t.start();
        }
    }
    
    /**
     * An algorithm that generates a string based upon the numbers it is given
     * @param seedInt The number that is processed into a String.
     * @return A String made from the number.
     */
    public String algorithm(int seedInt){
        String seedString = Integer.toString(seedInt,this.symbols); //convert seedInt to base this.symbols number
        ArrayList<Integer> intarr = new ArrayList<Integer>(); //create an ArrayList for each character of seedString to be stored in as int
            
            //take the seedString and push the base 10 value of each digit to an integer aray
            for(int i = 0; i < seedString.length(); i++){ //iterate over the seedString
                String cur=seedString.substring(i,i+1); //specify a letter of seedString
                int curint =Integer.valueOf(cur,this.symbols); //convert cur to a base 10 integer
                intarr.add(curint); //append to the intarr
            }
            
            //push zeros to the intarr to make the rest of the permutation
            while(intarr.size() < this.chars){ //run if the ArrayList isn't long enough
                intarr.add(0,0); //push a zero to the beginning of the ArrayList
            }
             
            //convert the numbers in intarr to symbols
            String output = ""; //declare and initialize the output String
            for(int i = 0; i <= intarr.size()-1; i++){ //iterate over the intarr
                output = output + this.symbolsarr[intarr.get(i)];//access the appropriate element of the symbols array and concatentate it the output String
            }
            
            return output; //returns the String
    }
}