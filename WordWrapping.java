import java.util.Scanner;
public class WordWrapping {
    public  int[] wordPacking(int lineChar[],int number_word,int number_char )
    {
        //An array to store extra space
        int extraSpace[][] = new int[number_word+1][number_word+1];

        //An array to maintain the cost of placing words with a specified number of characters
        int lineCost[][] = new int[number_word+1][number_word+1];

        //An array total cost of optimal ,minimum cost
        int overallCost[] = new int[number_word+1];
        overallCost[0] = 0;

        //array final
        int answer[] = new int[number_word+1];

        //find extra space for all lines
        for (int i = 1; i <= number_word; i++)
        {
            extraSpace[i][i] = number_char - lineChar[i-1];
            //extra space when word i to j are in line
            for (int j = i+1; j <= number_word; j++)
                extraSpace[i][j] = extraSpace[i][j-1] - lineChar[j-1] - 1;
        }



        //find line cost
        for (int i = 1; i <= number_word; i++) {
            for (int j = i; j <= number_word; j++) {
                if (extraSpace[i][j] < 0)
                    lineCost[i][j] = Integer.MAX_VALUE;
                else if (j == number_word && extraSpace[i][j] >= 0)
                    lineCost[i][j] = 0;
                else
                    lineCost[i][j] = extraSpace[i][j]*extraSpace[i][j];
            }
        }


        //find minimum cost
        for (int j = 1; j <= number_word; j++) {
            overallCost[j] = Integer.MAX_VALUE;

            for (int i = 1; i <= j; i++) {
                if (overallCost[i - 1] != Integer.MAX_VALUE) {
                    if (lineCost[i][j] != Integer.MAX_VALUE) {
                        if (overallCost[i - 1] + lineCost[i][j] < overallCost[j]) {
                            overallCost[j] = overallCost[i - 1] + lineCost[i][j];
                            answer[j] = i;
                            break;
                        }
                        break;
                    }

                }
            }
        }



        int costTotal=0;

        for (int i=0;i<number_word+1;i++){
            costTotal=costTotal+overallCost[i];
        }
        System.out.println("Total cost : ");
        System.out.println(costTotal);
        return answer;


    }
   public int print(int answer[], int number_word)
    {
        int number_line;
        if (answer[number_word] == 1)
            number_line = 1;
         else
            number_line = print(answer, answer[number_word]-1) + 1;
        System.out.println("Number line in Paragraph" + " " + number_line + ": ");
        System.out.println("Number of word : "+answer[number_word]+" to "+number_word);
        return number_line;
    }
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter max char in any line : ");
        //number of characters in any line
        int number_char=scanner.nextInt();

        System.out.println("Enter number of word : ");
        //input : number of word
        int number_word=scanner.nextInt();

        //lengths of different words
        int lineChar[]=new int[number_word];
        System.out.println("Enter number of char in word : ");
        for (int i=0;i<number_word;i++){
            //lengths of any word
            lineChar[i]=scanner.nextInt();
        }
        /*int sum=0;
        for (int i=0;i<number_word;i++) {
            sum = sum + lineChar[i];
        }*/

        WordWrapping A=new WordWrapping();
        int array[]=new int[number_word+1];
        array=A.wordPacking(lineChar,number_word,number_char);

       /* for (int i=0;i<number_word+1;i++){
            System.out.println(array[i]);
        }*/
       int numberTotalLine = A.print(array,number_word);
       System.out.println("number of total line : " + numberTotalLine);

    }
}
