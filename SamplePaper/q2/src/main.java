/**
 * Created by Zhenbang Xiao on 2015/1/16 0016.
 */
public class main {
    public static void main(String[] args){
        for(int i=1;i<=100;i++){
            if((i%5==0)&&(i%3==0)){
                System.out.println("FizzBuzz");
            }else if(i%5==0){
                System.out.println("Buzz");
            }else if(i%3==0){
                System.out.println("Fizz");
            }else{
                System.out.println(i);
            }
        }
    }
}
