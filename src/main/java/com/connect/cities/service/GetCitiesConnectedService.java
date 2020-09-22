package com.connect.cities.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.connect.cities.util.Constants;

@Service
public class GetCitiesConnectedService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetCitiesConnectedService.class);
	
	public String getCitiesConnected(String origin , String destination) throws IOException {
		
		List<String> createCityList = createCityList();
		int[][] createCityGraph = createGraph(createCityList);
		LOGGER.info("City List:" +createCityList);
		if(isConnected(origin, destination, createCityList, createCityGraph))
			return Constants.YES; 
		else
			return Constants.NO;
	}
	
	private List<String> createCityList() throws IOException{
		List<String> cityList = new ArrayList<>();
        try {
            FileReader file = new FileReader("secrets/city.txt");
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();

            while(line != null){
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while(stringTokenizer.hasMoreTokens()){ 
                    String token = stringTokenizer.nextToken();
                    if(!cityList.contains(token.trim())){ 
                        cityList.add(token.trim());
                    }
                }
                line = br.readLine();
            }
            br.close();
        }

        catch (FileNotFoundException e) {
        	LOGGER.info("File Not Found Exception :{} ,{}", e, e.getMessage());
        }catch(Exception e) {
        	LOGGER.info("Exception :{}, {}", e, e.getMessage());
        }
        return cityList;
    }
	
	private static int[][] createGraph(List<String> cityList) throws IOException{ 
	    int[][] cityGraph = new int[cityList.size()][cityList.size()]; 

	        try {
	            FileReader file = new FileReader("secrets/city.txt");
	            BufferedReader br = new BufferedReader(file);
	            String line = br.readLine();

	            while(line != null){
	                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
	                while(stringTokenizer.hasMoreTokens()){ 
	                    String primaryToken = stringTokenizer.nextToken().trim();
	                    String secondaryToken = stringTokenizer.nextToken().trim();
	                    cityGraph[cityList.indexOf(primaryToken)][cityList.indexOf(secondaryToken)] = 1; 
	                    cityGraph[cityList.indexOf(secondaryToken)][cityList.indexOf(primaryToken)] = 1; 
	                }
	                line = br.readLine();
	            }
	            br.close();
	        }
	        catch (FileNotFoundException e) {
	        	LOGGER.info("File Not Found Exception :{} ,{}", e, e.getMessage());
	        }catch(Exception e) {
	        	LOGGER.info("Exception :{}, {}", e, e.getMessage());
	        }
	        return cityGraph;
	    }
	private boolean isConnected(String s1, String s2, List<String> cityList, int[][] cityGraph){
        int origin = cityList.indexOf(s1);
        int destination = cityList.indexOf(s2); 
        
		try {

			int startNode = origin;
			Queue<Integer> q = new PriorityQueue<Integer>();
			q.add(startNode);

			while (!q.isEmpty()) {

				for (int i = 0; i < cityList.size(); i++) {
					if (cityGraph[startNode][i] == 1) {
						if (i == destination) {
							LOGGER.info("Cities are connected : Yes");
							return true;
						}
						q.add(i);
						cityGraph[startNode][i] = 0;
					}
				}
				q.remove();
				if (!q.isEmpty()) {
					startNode = (Integer) q.element();
				}
			}
			LOGGER.info("Cities are not connected : No");
			return false;
		}
		catch(Exception e) {
			LOGGER.info("Exception :{}, {}", e, e.getMessage());
			return false;
		}
	}
}
