package com.geo.geoCode;

import java.io.BufferedReader;
import java.sql.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.google.gson.Gson;

public class geoFinder {
	
	private static final String API_KEY = "AIzaSyCiGKGvyHBL3CDTuLlHErpXZRgigF5IQZc";

	public static void main(String[] args) {
		
		String csvFile = "C:\\Users\\user\\Downloads\\input.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] timeData = line.split(cvsSplitBy);
				System.out.print(timeData[0]+","+timeData[1]+","+timeData[2]+",");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				// sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				java.util.Date date;
				try {
					date = sdf.parse(timeData[0]);
					// System.out.println(date);
			        long unixtime = date.getTime();  
			        unixtime=unixtime/1000;
					
					//System.out.println(""+unixtime);
					String output  = geoFinder.dateFinder(Double.parseDouble(timeData[1]),Double.parseDouble(timeData[2]),unixtime, date);
					System.out.println(output);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		

		
		//sdf.setTimeZone(TimeZone.getDefault());        
	//	localTime = new Date(sdf.format(gmtTime));
//		System.out.println("Local:" + localTime.toString() + "," + localTime.getTime() + " --> UTC time:" + gmtTime.toString() + "-" + gmtTime.getTime());

		return;
	}
	
	public static String dateFinder(double latitude, double longtitude, long time, java.util.Date date) {
	try {
        URL url = new URL(
                "https://maps.googleapis.com/maps/api/timezone/json?location="+latitude+","+longtitude+"&timestamp="+time+"&key=" + API_KEY ) ; 
       //System.out.println("ur;"+url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output = "", full = "";
        while ((output = br.readLine()) != null) {
            //System.out.println(output);
            full += output;
        }

        GMapJson  gson = new Gson().fromJson(full, GMapJson.class);
        TimeZone timeZone = TimeZone.getTimeZone(gson.getTimeZoneId());
        DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        pstFormat.setTimeZone(timeZone);
        //java.util.Date newDate = new  java.util.Date (pstFormat.format(date));
        //System.out.println(newDate);
       // System.out.println(gson.getTimeZoneId());
        

        conn.disconnect();
        return gson.getTimeZoneId()+","+pstFormat.format(date);

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
  //  } catch (ParseException e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	}
		return null;
	}
	
}
