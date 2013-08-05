<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Data Manager</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
	</head>
	<body>
		<c:if test="${sessionScope.user eq null}">
			<script type="text/javascript">
				window.location = "login.html";
			</script>
		</c:if>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="main">
				<script type="text/javascript" charset="utf-8">
					var chart, chart2;
					function buildChart() {
						//近一月财务报表
						doGetAjax("<%=request.getContextPath()%>/report/last15daysmoney.ajax", '', function(data, textStatus, XMLHttpRequest) {
							newChart1(data.data);
						});
						//近一月交易数报表
						doGetAjax("<%=request.getContextPath()%>/report/last15dayscount.ajax", '', function(data, textStatus, XMLHttpRequest) {
							newChart2(data.data);
						});
					};

					function newChart1(datas) {
						var cats = datas.cats;
						var name = datas.name;
						var data = datas.data;
						chart = new Highcharts.Chart({
							credits : {
								enabled : false
							},
							exporting : {
								enabled : false
							},
							chart : {
								renderTo : 'container1',
								type : 'column'
							},
							title : {
								text : name,
								x : -20 //center
							},
							xAxis : {
								categories : cats
							},
							yAxis : {
								min : 0,
								title : {
									text : '金额（元）'
								}
							},
							tooltip : {
								formatter : function() {
									return this.x + '月 ' + this.series.name + '：' + this.y + '元';
								}
							},
							plotOptions : {
								column : {
									pointPadding : 0.2,
									borderWidth : 0
								}
							},
							series : data
						});
					};

					function newChart2(datas) {
						var cats = datas.cats;
						var name = datas.name;
						var data = datas.data;
						chart2 = new Highcharts.Chart({
							credits : {
								enabled : false
							},
							exporting : {
								enabled : false
							},
							chart : {
								renderTo : 'container2',
								type : 'line'
							},
							title : {
								text : name,
								x : -20 //center
							},
							xAxis : {
								categories : cats
							},
							yAxis : {
								title : {
									text : '数量'
								},
								plotLines : [{
									value : 0,
									width : 1,
									color : '#808080'
								}]
							},
							tooltip : {
								formatter : function() {
									return '<b>' + this.series.name + '</b><br/>' + this.x + '日: ' + this.y + '笔';
								}
							},
							plotOptions : {
								line : {
									dataLabels : {
										enabled : false
									},
									enableMouseTracking : true
								}
							},
							series : data
						});
					};

					$(function() {
						buildChart();
					});

				</script>
				<!--近一月财务报表，柱状图-->
				<div id="container1" style="width: 100%; min-height: 200px;"></div>
				<!--近一月交易数报表，线型图-->
				<div id="container2" style="width: 100%; min-height: 200px;"></div>
			</div>
			<%@ include file="foot.jsp"%>
		</div>
	</body>
</html>
