package com.remove.duplicacy;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
class CSVFile {

     public static void main(String[] args) throws Exception {

         Scanner file = new Scanner(new File("C:\\Users\\Admin2\\Downloads\\Treadmill-social-media.csv"));
         Set<String> wordSet = new HashSet<String>();
         FileWriter data = new FileWriter("D:\\Data.csv"+System.currentTimeMillis());
         file.useDelimiter(",");

         String word;

         while (file.hasNext())
         {
             word = file.next().replaceAll("[^a-zA-Z0-9 ]", "");
             wordSet.add(word);


         }
         file.close();

         for (String wordlist : wordSet) {
             StringBuilder line =new StringBuilder();
             line.append(wordlist);
             line.append(',');
             data.write(String.valueOf(line));
         }
         data.close();
     }

 }