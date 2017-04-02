package com.synwing.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synwing.demo.parameter.JQplotQueryParameter;
import com.synwing.demo.resultbean.JQplotChartData;


@Controller
@RequestMapping("/jqplot")
public class JQplotController {
	
	@RequestMapping(value="/getJQplotDataByDateTime",method=RequestMethod.POST)
	public @ResponseBody List<JQplotChartData> getJQplotDataByDateTime(@RequestBody JQplotQueryParameter queryParameter) throws Exception{ 
		List<JQplotChartData> results = new ArrayList<JQplotChartData>();
		JQplotChartData jQplotChartData =null;
		for(int i =1;i<31;i++){
			jQplotChartData = new JQplotChartData();
			jQplotChartData.setDateTimeStr(String.valueOf(i));
			Double value = Math.random();
			if(value < 0.3){
				value = value+0.3;
			}
			if(value>0.6){
				value = value-0.3;
			}
			jQplotChartData.setCgmValue(value*10);
			results.add(jQplotChartData);
		}	
		return results;
	}

}
