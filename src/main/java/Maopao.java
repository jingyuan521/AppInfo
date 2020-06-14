import java.util.Scanner;
public class Maopao {
    static int jiecheng(int num){
        if (num==1) {
            return 0;
        }else{
            return jiecheng(num--);
        }

    }
    public static void main(String[] args) {
/*        int[] nums = {12,1,22,21,15,14};
        for (int a:nums) {
            System.out.print(a+"   ");
        }
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = 0; j < nums.length-1-i; j++) {
                if (nums[j]>nums[j+1]) {
                    int num = nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=num;
                }
            }
        }
        System.out.println();
        for (int b:nums) {
            System.out.print(b+"   ");
        }*/

//非递归
/*     int num = 0;
        for (int i = 9; i > 0; i--) {
            num+=(i-1)*i;
            if (i == 1) {
                System.out.print(i+"");
            }else{
                System.out.print(i+" * ");
            }

        }
        System.out.print(" = "+num);*/
       //递归
        int num=Maopao.jiecheng(5);
        System.out.println(num);
    }



}
