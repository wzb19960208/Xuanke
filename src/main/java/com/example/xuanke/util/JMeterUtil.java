package com.example.xuanke.util;

import java.io.*;

public class JMeterUtil {

    public static long averageTime(){

        File file = new File("D:\\LabProject\\Xuanke\\log\\error.2018-04-13.log");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            long total = 0;
            int len = 0;
            String temp;
            while ((temp=br.readLine())!=null){
                total+=Integer.parseInt(temp);
                len++;
            }
            return total/len;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;

    }

}


