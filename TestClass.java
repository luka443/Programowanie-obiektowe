package lab1;

public class TestClass {     
    private static int count = 0;

    public static void main( String [] args) { 
        Number h1 = new Number("1.");
        System.out.println(h1.toString());
        Number h2 = new Number("2.");
        System.out.println(h2.toString());
        Number h3 =new Number("3." );
        System.out.println(h3.toString());              
    } 
}

class Number {
    String name;
    private int property;
    static int count = 0;

    public Number(String name){
        count = count +1;
        this.property = count;
        this.name = name;

    }
    public String toString(){
        String temp = "";
        temp =  this.name + " " + this.property;
        return temp;
    }

}
