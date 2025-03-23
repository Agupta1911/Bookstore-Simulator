public class arraylist {
    public static void main(String[] args) {
    int[] myArray= {11,21,31,51};
     InsertElement (int [] numbers, int position, int value){
        for(int i = numbers.length-1; i>position; i--){
            numbers[i]=numbers[i-1];
        }
        numbers[position]=value;
    InsertElement(myArray, 3, 41);
    System.out.println(myArray);
    

    }
}
}