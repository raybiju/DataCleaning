package com.remove.duplicacy;

import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Translator {

    public static void main(String[] args) throws IOException, JSONException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter("D:\\Data.csv"));
        BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Admin2\\Downloads\\Treadmill-social-media.csv"));
          String row;
        while ((row = csvReader.readLine()) != null) {
         String record = row.replaceAll("[^a-zA-Z0-9 ,]", "");
        String result = translate("auto", "en",record);
         String[] words = result.split(",");
        csvWriter.writeNext(words);

    }
         csvReader.close();
    csvWriter.close();
}


//        double nol = 10.0;
//        int count = 9025;
////        String strLine;
////
//////      while  (( csvReader.readLine()) != null)
//////        {
//////         // System.out.println(row);
//////            count++;
//////        }
////
//        double temp = (count / nol);
//        int temp1 = (int) temp;
//        int nof = 0;
//        if (temp1 == temp) {
//            nof = temp1;
//        } else {
//            nof = temp1 + 1;
//        }
//        String strLine;
//        for (int j = 1; j <= nof; j++) {
//            csvWriter = new CSVWriter(new FileWriter("D:\\Data" + j + ".csv"));
//
//            for (int i = 1; i <= nol; i++) {
//                strLine = csvReader.readLine();
////                //  System.out.println(strLine);
////
//                if (strLine != null) {
//                    String record = strLine.replaceAll("[^a-zA-Z0-9 ,]", "");
//                    //  String result = record.replaceAll("[^a-zA-Z0-9 ,]", "");
//                    String result= translate("auto", "en", record);
//                    String[] words = result.split(",");
//                    csvWriter.writeNext(words);
////
////
//                }
//
//            }
//        }
//        csvWriter.close();
//        csvReader.close();
//    }


    private static String translate(String langFrom
            , String langTo, String result) throws IOException,JSONException {
        String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="
                + langFrom + "&tl=" + langTo + "&dt=t&q=" + URLEncoder.encode(result, StandardCharsets.UTF_8.toString());

        URL url = new URL(urlStr);
        StringBuilder requiredText = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
       con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            JSONArray jsonarray = new JSONArray(inputLine).getJSONArray(0);

            for (int i = 0; i < jsonarray.length(); i++) {

                JSONArray array = jsonarray.getJSONArray(i);

                requiredText.append(array.getString(0));
            }

        }
        in.close();
        return requiredText.toString();
    }
}