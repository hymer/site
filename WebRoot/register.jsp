<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新用户注册</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/common-tools.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery.validate.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/messages_cn.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css" media="all" />
		<script type="text/javascript">
			$(function() {
				$("#registerForm").validate({
					rules : {
						userName : {
							required : true,
							minlength : 5,
							maxlength : 18
						},
						password1 : {
							required : true,
							minlength : 6,
							maxlength : 18
						},
						password2 : {
							required : true,
							equalTo : '#f_password1'
						},
						realName : {
							required : true
						},
						mobile : {
							required : true,
							range : [10000000000, 19999999999],
							digits : true
						},
						email : {
							required : true,
							email : true
						},
						companyName : {
							required : true,
							minlength : 2,
							maxlength : 200
						},
						linkman : {
							required : true,
							minlength : 2,
							maxlength : 50
						},
						lealPerson : {
							required : true,
							minlength : 2,
							maxlength : 200
						},
						registeredNo : {
							required : true,
							minlength : 2,
							maxlength : 50
						},
						companyAddress : {
							required : true,
							minlength : 5,
							maxlength : 200
						},
						verifyCode : {
							required : true
						},
						postCode : {
							required : true,
							minlength : 6,
							maxlength : 20
						}
					},
					messages : {
						mobile : {
							range : "请输入正确的手机号码",
							digits : "请输入正确的手机号码"
						}
					}
				});
			});

		</script>
		<style media="screen" type="text/css">
			label {
				width: 90px;
				display: inline-block;
				text-align: right;
			}
			label.min {
				width: 25px;
				display: inline-block;
				text-align: left;
			}
			label.max {
				width: 380px;
				display: inline-block;
				text-align: left;
			}
		</style>
	</head>
	<body>
		<div id="page">
			<%@ include file="top.jsp"%>
			<div id="body">
				<!-- Main -->
				<div id="main2">
					<form id="registerForm" action="register.html" method="post">
						<fieldset>
							<legend>
								新用户注册
							</legend>
							<p>
								<label for="f_userName">用户名<font color="red">*</font>：</label>
								<input id="f_userName" name="userName" />
								<label class="max">5~18位字母和数字</label>
							</p>
							<p>
								<label for="f_password1">密码<font color="red">*</font>：</label>
								<input type="password" id="f_password1" name="password1" />
								<label class="max">密码由6-18个字符组成，不要使用纯字母或纯数字</label>
							</p>
							<p>
								<label for="f_password2">重复密码<font color="red">*</font>：</label>
								<input type="password" id="f_password2" name="password2" />
								<label class="max">请重复上面的密码</label>
							</p>
							<p>
								<label for="f_realName">真实姓名<font color="red">*</font>：</label>
								<input id="f_realName" name="realName" />
								<input type="radio" name="gender" value="M" checked="checked" id="f_gender1"/>
								<label class="min" for="f_gender1">先生</label>
								<input type="radio" name="gender" value="F" id="f_gender2"/>
								<label class="min" for="f_gender2">女士</label>
								<label class="max">请正确填写您的姓名(不带空格)</label>
							</p>
							<p>
								<label for="f_position">职位：</label>
								<input id="f_position" name="position" />
							</p>
							<p>
								<label for="f_telephone">固定电话：</label>
								<input id="f_telephone" name="telephone" />
							</p>
							<p>
								<label for="f_fax">传真：</label>
								<input id="f_fax" name="fax" />
							</p>
							<p>
								<label for="f_mobile">手机<font color="red">*</font>：</label>
								<input id="f_mobile" name="mobile" />
							</p>
							<p>
								<label for="f_email">E-mail<font color="red">*</font>：</label>
								<input id="f_email" name="email" />
							</p>
							<p>
								<label>用户类型：</label>
								<input type="radio" name="roleName" value="company" id="f_roleName1" />
								<label style="width: 50px;" for="f_roleName1">企业用户</label>
								<input type="radio" name="roleName" value="personal" id="f_roleName2" checked="checked" />
								<label style="width: 50px;" for="f_roleName2">个人用户</label>
							</p>
							<div id="companyTypeDiv" style="display: none;">
								<hr />
								<p>
									<label for="f_companyName">企业全称<font color="red">*</font>：</label>
									<input id="f_companyName" name="companyName" />
									<label class="max">请填写工商局注册全称，无商号个体经营者请填写执照上的姓名</label>
								</p>
								<p>
									<label for="f_linkman">联系人<font color="red">*</font>：</label>
									<input id="f_linkman" name="linkman" />
								</p>
								<p>
									<label for="f_area">行业<font color="red">*</font>：</label>
									<select name="area" id="f_area">
										<option value="生活">生活</option>
										<option value="工业">工业</option>
										<option value="农林">农林</option>
										<option value="建筑">建筑</option>
										<option value="贸易">贸易</option>
										<option value="运输">运输</option>
										<option value="网络">网络</option>
										<option value="广告">广告</option>
										<option value="娱乐">娱乐</option>
										<option value="医疗">医疗</option>
										<option value="化妆品">化妆品</option>
										<option value="运动">运动</option>
										<option value="服装">服装</option>
										<option value="纺织">纺织</option>
										<option value="工商">工商</option>
										<option value="安全">安全</option>
										<option value="能源">能源</option>
										<option value="车辆">车辆</option>
										<option value="金融">金融</option>
										<option value="加工">加工</option>
										<option value="艺术">艺术</option>
										<option value="地产">地产</option>
										<option value="邮政">邮政</option>
										<option value="电子">电子</option>
										<option value="体育">体育</option>
										<option value="商务">商务</option>
										<option value="培训">培训</option>
										<option value="机械">机械</option>
										<option value="烟酒">烟酒</option>
										<option value="轻工">轻工</option>
										<option value="旅游">旅游</option>
										<option value="航天">航天</option>
										<option value="器材">器材</option>
										<option value="化工">化工</option>
										<option value="餐饮">餐饮</option>
										<option value="其它">其它</option>
									</select>
								</p>
								<p>
									<label for="f_orgNature">机构性质<font color="red">*</font>：</label>
									<select name="orgNature" id="f_orgNature">
										<option value="民营企业">民营企业</option>
										<option value="国有企业">国有企业</option>
										<option value="集体企业">集体企业</option>
										<option value="乡镇企业">乡镇企业</option>
										<option value="外商独资">外商独资</option>
										<option value="中外合资">中外合资</option>
										<option value="股份制企业">股份制企业</option>
										<option value="行政机关">行政机关</option>
										<option value="社会团体">社会团体</option>
										<option value="事业单位">事业单位</option>
										<option value="其他">其他</option>
									</select>
								</p>
								<p>
									<label for="f_lealPerson">法人代表<font color="red">*</font>：</label>
									<input id="f_lealPerson" name="lealPerson" />
									<label class="max">请填写工商执照上所标注的法定代表人姓名</label>
								</p>
								<p>
									<label for="f_registeredCapital">注册资金：</label>
									<input id="f_registeredCapital" name="registeredCapital" />
									<label class="max">（单位：万元）</label>
								</p>
								<p>
									<label for="f_registeredNo">工商注册号<font color="red">*</font>：</label>
									<input id="f_registeredNo" name="registeredNo" />
									<label class="max">请填写工商执照上所标注工商注册号</label>
								</p>
								<p>
									<label for="f_totalStaffs">企业人数：</label>
									<input id="f_totalStaffs" name="totalStaffs" />
								</p>
								<p>
									<label for="f_companyAddress">公司地址<font color="red">*</font>：</label>
									<input id="f_companyAddress" name="companyAddress" />
									<label class="max">请详细填写贵公司经营地址，以便我方进行工商诚信核查</label>
								</p>
								<p>
									<label for="f_postCode">邮政编码<font color="red">*</font>：</label>
									<input id="f_postCode" name="postCode" />
								</p>
							</div>
							<p>
								<label>验证码：</label>
								<img id="verifyImage" src="verify.jpg"/>
								<input type="text" name="verifyCode" size=6/>
								看不清？点击图片<a href="javascript:refreshVerifyCode();">刷新</a>
								<script type="text/javascript">
									$("#verifyImage").click(function() {
										refreshVerifyCode();
									});
									function refreshVerifyCode() {
										$("#verifyImage").attr('src', 'verify.jpg?' + Math.floor(Math.random() * 100));
									}
								</script>
							</p>
							<p>
								<label></label>
								<input type="submit" value="提交注册信息" />
								<input type="reset" value="重置" />
							</p>
						</fieldset>
					</form>
				</div>
				<script type="text/javascript">
					$("input[name='roleName']").change(function() {
						var that = $("#f_roleName1");
						var checkedValue = that.attr("checked");
						if(checkedValue) {
							$("#companyTypeDiv").show("slow");
						} else {
							$("#companyTypeDiv").hide();
						}
					});

				</script>
			</div>
		</div>
		<%@ include file="foot.jsp"%>
	</body>
</html>
