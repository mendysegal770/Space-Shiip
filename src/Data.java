public class Data {
    public  static void main(String[]args){
        int []arr={1,2,3};
        function(arr);
    }

    public static void function( int[]arr){
        int n=arr.length;
        int i=1;
        while (i<=n){
            for ( int j=n; j>i+1; j--){
                System.out.println(arr[j-1]);
                i+=5;
            }
        }

    }
}
