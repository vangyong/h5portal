<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverNamePort = request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>Testing websockets</title>
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/excanvas.js"></script><![endif]--> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.dateAxisRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.categoryAxisRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.ohlcRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.logAxisRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.canvasTextRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.highlighter.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.canvasOverlay.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/plugins/jqplot.cursor.min.js"></script>
	<link rel="stylesheet" type="text/css" hrf="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/jquery.jqplot.min.css" />
	<link rel="stylesheet" type="text/css" hrf="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" hrf="<%=request.getContextPath()%>/resources/plugins/jqplot.1.0.8/jquery.jqplot.css" />

    <script>
	 $(function(){
		// start();
	  });
	//页面加载完成后执行
	// window.onload=start;//不要括号
	</script>

</head>
<body onload="start()">
	<div>
        <div>
            <div id="chart1" style="width:1000px; height:500px;"></div>
        </div>
    </div>	
    <div>
		<input type="submit" value="restart" onclick="start()"/>
		<!-- <input type="submit" value="stop" onclick="stop()" /> -->
	</div>
	
	<script type="text/javascript">

		var dataSets ={data:[]};  
		
		//var line1 = [[1,0.2, 2,0.1, 2.2,0.4, 3,1.7, 1.5,1.6, 1.6,0.8, 2.7,1.2]];
		
		// 警戒线设置
	    var userLine3Val = 1.5; 
	    var userLine4Val = 2.5; 
	    
	    var userLines =[{horizontalLine: {
	         name: "line3",
	          y: userLine3Val,
	          color: "#EEEE00",
	          xOffset: 0,
	          shadow: false,
	          showTooltip: true,
	          tooltipFormatString: "PCE=%'d",
	          showTooltipPrecision: 0.5
	      }},
	      {horizontalLine: {
	          name: "line4",
	          y: userLine4Val,
	          color: "#EEEE00",
	          xOffset: 0,
	          shadow: false,
	         // showTooltip: true,
	         // tooltipFormatString: "PCE=%'d",
	          showTooltipPrecision: 0.5
	      }}]
	
		//var webSocket = new WebSocket('ws://127.0.0.1:9090/websockets_demo/websocket');
	    var webSocket = new WebSocket("ws://"+'<%=serverNamePort%>'+"websocket");
		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};
		
/* 	 	webSocket.onclose = function(event) {
			onClose(event)
		};
 */
		function onMessage(event) {
				var data =event.data;
				var jsonObj = $.parseJSON(data);
				var arrs =jsonObj.data;
				//JSON.parse(jsonstr); //可以将json字符串转换成json对象 
				//JSON.stringify(jsonobj); //可以将json对象转换成json对符串
				var array1 = [];   
				for (var i=0;i<arrs.length;i++)
		    	{
		        	//每条数据对象[time,value]   
		        	//array1.push(arrs[i].number);
		        	array1.push(arrs[i].cgmValue);
		    	}
				dataSets.data.length=0;
				dataSets.data.push(array1);
				drawChart();
		}

		function onOpen(event) {
			/* document.getElementById('messages').innerHTML 
				= 'Connection established'; */
		}

		function onError(event) {
			alert(event.data);
		}
		
		function onClose(event) {
			alert("websocket closed!");
		}

		function start() {
			webSocket.send('hello');
			return false;
		}
		
	/*     function stop(event) {
			webSocket.onclose(event);
		} */ 
		
		//画图
		function drawChart() {
			 document.getElementById("chart1").innerHTML ="";
			 plot1 = $.jqplot("chart1",  dataSets.data /* line1 */, 
					  {
				 	        title: "CGM 血糖浓度检测结果",
				 	        cursor: {
				 	            show: true,
				 	            showTooltip: true,
				 	            useAxesFormatters: true,
				 	            //tooltipFormatString:'%d %.2d',
				 	            followMouse:true
				 	        },
				 	        highlighter: {
				 	            //show: false,
				 	            //showMarker: false,
				 	            //useAxesFormatters: false
				 	           // formatString: '%d, %.1f'
				 	        },
				 	        axesDefaults: {
				 	            labelRenderer: $.jqplot.CanvasAxisLabelRenderer
				 	        },
				 	        seriesDefaults: {
				 	            showMarker: false
				 	        },
				 	        axes: {
				 	            xaxis: {
				 	               /*  renderer: $.jqplot.CanvasAxisLabelRenderer,
				 	                pad:0,
				 	                label:x_label, */
				 	                min: 1,
				 	                max: 30,
				 	                tickInterval: 1
				 	               // tickOptions: {formatString: x_formatString},
				 	               // drawMajorGridlines: true
				 	            },
				 	            yaxis: {
				 	                label: " ",
				 	                forceTickAt0: true,
				 	               // pad: 0,
				 	                min: 1,
				 	                max: 3,
				 	                tickInterval: 0.1
				 	            }
				 	        },
				 	        grid: {
				 	            drawBorder: false,
				 	            shadow: true,
				 	            // background: 'rgba(0,0,0,0)'  works.
				 	            background: "white"
				 	        },
				 	        canvasOverlay: {
				 	            show: true,
				 	            objects: [
				 	               {verticalLine: {
				                    name: "line1",
				                    x: 8250,
				                    color: "#d4c35D",
				                    yOffset: 0,
				                    shadow: true,
				                    showTooltip: true,
				                    tooltipFormatString: "PCE=%'d",
				                    showTooltipPrecision: 0.5
				                    }},
				 	                {horizontalLine: {
				 	                    name: "line3",
				 	                    y: userLine3Val,
				 	                    color: "#FF7F00",
				 	                    xOffset: 0,
				 	                    shadow: true,
				 	                    showTooltip: true,
				 	                    tooltipFormatString: "PCE=%'d",
				 	                    showTooltipPrecision: 0.5
				 	                }},
				 	                {horizontalLine: {
				 	                    name: "line4",
				 	                    y: userLine4Val,
				 	                    color: "#EEEE00",
				 	                    xOffset: 0,
				 	                   	//fill:true,
				 	                   	//fillColor:"FF0000",
				 	                    shadow: false,
				 	                    showTooltip: true,
				 	                    tooltipFormatString: "高血糖警戒线",//"PCE=%'d",
				 	                    showTooltipPrecision: 0.5
				 	                }}
				 	            ]
				 	        }
				 	    }
			 );
		}
		
		
	</script>
</body>
</html>