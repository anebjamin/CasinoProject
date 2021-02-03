package casino;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class loginSystem {
    
    public static String folderDirectory= System.getProperty("user.dir") + "\\loginListTest.txt";
    
    public static void main(String[]args) throws FileNotFoundException{
        File file=new File(folderDirectory);
        Scanner scanFile=new Scanner(file);
        Scanner input=new Scanner(System.in);
        System.out.println(scanFile.nextLine());

    }
}
