package com.synwing.demo.websockets;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ServerEndpoint("/websocket")
public class WebSocketTest {
	
	private int startLine =1;
	private int numLine = 1000;

	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		// Print the client message for testing purposes
		System.out.println("Received: " + message);
		
		int sentMessages = 0;
		//无限循环一直更新图表数据
		
		while(true){
			Thread.sleep(5000);
			List<JQplotChartData> results =FileOperater.readFileToList("F:\\test01\\ecg_data_20150523-4.dat",startLine,numLine);
			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(results);
			JSONObject  returnObj = new JSONObject();  
			returnObj.put("data", jsonArray);
			//session.getBasicRemote().sendObject(returnObj.toString());
			session.getBasicRemote().sendText(returnObj.toString());
			sentMessages++;
			startLine = startLine+1;
			if(startLine>80000){
				startLine =1;
			}
		}
		
    }
	
	@OnOpen
    public void onOpen () {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose () {
    	System.out.println("Connection closed");
    }
}
