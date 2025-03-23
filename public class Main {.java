public class Main {
    int[] myArray= {11,21,31,51};
    public InsertElement (int [] numbers, int position, int value){
        for(i=number.length()-1; i>position; i--){
            numbers[i]=numbers[i-1];
        }
        numbers[position]=value;
    }
    InsertElement(myArray, 3, 41);
    system.out.println(myArray);
}
