<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>月财务报表</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/lib-base.jsp"></script>
	</head>
	<body>
		<%@ include file="left.jsp"%>
		<div id="page">
			<%@ include file="../top.jsp"%>
			<div id="main">
				<script type="text/javascript">
					var chart;
					function buildChart(year, month) {
						doGetAjax("<%=request.getContextPath()%>/report/" + year + "/" + month + ".ajax", '', function(data, textStatus, XMLHttpRequest) {
							newChart(data.data);
						});
					};

					function newChart(datas) {
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
								renderTo : 'container',
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
									text : '金额（元）'
								},
								plotLines : [{
									value : 0,
									width : 1,
									color : '#808080'
								}]
							},
							tooltip : {
								formatter : function() {
									return '<b>' + this.series.name + '</b><br/>' + this.x + '日: ' + this.y + '元';
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


					$(document).ready(function() {
						var currentYear = new Date().getFullYear();
						$("#year_select").append("<option value='" + currentYear + "' selected='selected'>" + currentYear + "</option>");
						$("#year_select").append("<option value='" + (currentYear - 1) + "'>" + (currentYear - 1) + "</option>");
						$("#year_select").append("<option value='" + (currentYear - 2) + "'>" + (currentYear - 2) + "</option>");
						$("#year_select").append("<option value='" + (currentYear - 3) + "'>" + (currentYear - 3) + "</option>");
						$("#year_select").append("<option value='" + (currentYear - 4) + "'>" + (currentYear - 4) + "</option>");
						var currentMonth = new Date().getMonth() + 1;
						$("#year_select").val(currentYear);
						$("#month_select").val(currentMonth);
						buildChart(currentYear, currentMonth);
						$("#year_select").change(function() {
							buildChart($("#year_select").val(), $("#month_select").val());
						});
						$("#month_select").change(function() {
							buildChart($("#year_select").val(), $("#month_select").val());
						});
					});

				</script>
				<label>年度：</label>
				<select id="year_select"></select>
				<label>月份：</label>
				<select id="month_select">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
				<div id="container" style="width: 100%; min-height: 400px;"></div>
			</div>
			<%@ include file="../foot.jsp"%>
		</div>
	</body>
</html>
