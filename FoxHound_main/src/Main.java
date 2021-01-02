import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String buffer = "Empty";
//        Xprint(XgetChar());
        array();
    }

    public static void Xprint(String content){
        System.out.println(content);
    }
    public static String XgetLine(){
        String line;
        System.out.println("Please Enter Input:");
        Scanner input = new Scanner(System.in);
        line = input.nextLine()+"!";
        return line;
    }
    public static String XgetChar(){
        String charX;
        System.out.println("Please Enter Input:");
        Scanner input = new Scanner(System.in);
        charX = input.findInLine("p");
        return charX;
    }
    public static void array(){
        String[] arrayX = {"s","d"};
        arrayX[0] = "a";
        System.out.println(arrayX[0]);
    }
}
