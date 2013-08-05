/**将数字转换成大写人民币**/
function transferChinese(num) {//转成人民币大写金额形式
	var str1 = '零壹贰叁肆伍陆柒捌玖';
	//0-9所对应的汉字
	var str2 = '万仟佰拾亿仟佰拾万仟佰拾元角分';
	//数字位所对应的汉字
	var str3;
	//从原num值中取出的值
	var str4;
	//数字的字符串形式
	var str5 = '';
	//人民币大写金额形式
	var i;
	//循环变量
	var j;
	//num的值乘以100的字符串长度
	var ch1;
	//数字的汉语读法
	var ch2;
	//数字位的汉字读法
	var nzero = 0;
	//用来计算连续的零值是几个

	num = Math.abs(num).toFixed(2);
	//将num取绝对值并四舍五入取2位小数
	str4 = (num * 100).toFixed(0).toString();
	//将num乘100并转换成字符串形式
	j = str4.length;
	//找出最高位
	if(j > 15) {
		return '溢出';
	}
	str2 = str2.substr(15 - j);
	//取出对应位数的str2的值。如：200.55,j为5所以str2=佰拾元角分

	//循环取出每一位需要转换的值
	for( i = 0; i < j; i++) {
		str3 = str4.substr(i, 1);
		//取出需转换的某一位的值
		if(i != (j - 3) && i != (j - 7) && i != (j - 11) && i != (j - 15)) {//当所取位数不为元、万、亿、万亿上的数字时
			if(str3 == '0') {
				ch1 = '';
				ch2 = '';
				nzero = nzero + 1;
			} else {
				if(str3 != '0' && nzero != 0) {
					ch1 = '零' + str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				}
			}
		} else {//该位是万亿，亿，万，元位等关键位
			if(str3 != '0' && nzero != 0) {
				ch1 = "零" + str1.substr(str3 * 1, 1);
				ch2 = str2.substr(i, 1);
				nzero = 0;
			} else {
				if(str3 != '0' && nzero == 0) {
					ch1 = str1.substr(str3 * 1, 1);
					ch2 = str2.substr(i, 1);
					nzero = 0;
				} else {
					if(str3 == '0' && nzero >= 3) {
						ch1 = '';
						ch2 = '';
						nzero = nzero + 1;
					} else {
						if(j >= 11) {
							ch1 = '';
							nzero = nzero + 1;
						} else {
							ch1 = '';
							ch2 = str2.substr(i, 1);
							nzero = nzero + 1;
						}
					}
				}
			}
		}
		if(i == (j - 11) || i == (j - 3)) {//如果该位是亿位或元位，则必须写上
			ch2 = str2.substr(i, 1);
		}
		str5 = str5 + ch1 + ch2;

		if(i == j - 1 && str3 == '0') {//最后一位（分）为0时，加上“整”
			str5 = str5 + '整';
		}
	}
	if(num == 0) {
		str5 = '零元整';
	}
	return str5;
}

function setTotalPrice(_that) {
	var tr = $(_that).parent().parent();
	var inputs = tr.find("input[type='text']");
	var count = $(inputs[4]).val();
	var price = $(inputs[5]).val();
	try {
		var tmp = count * price;
		if(tmp > 0) {
			$(inputs[6]).val(tmp);
		}
	} catch(e) {
		
	}
	setAmount();
}

function setAmount() {
	var trs = $("#itemTable tbody").find("tr");
	var amount = 0;
	for(var i = 0; i < trs.length; i++) {
		var r = $(trs[i]);
		var ips = r.find("input[type='text']");
		if(ips.length > 5) {
			var count = parseFloat($(ips[4]).val());
			var price = parseFloat($(ips[5]).val());
			var tmp = count * price;
			if(tmp >= 0) {
				$(ips[6]).val(tmp);
				amount = amount + tmp;
			}
		}
	}
	if(amount == NaN) {
		amount = 0;
	}
	$("#f_amount").val(amount);
	var font1 = $("#amountMaxLabel");
	font1.html(transferChinese(amount));
	var font2 = $("#amountMinLabel");
	font2.html("￥" + amount);
}

function addItem() {
	var tbody = $("#itemTable tbody");
	var tbodytrs = tbody.find("tr");
	var itemCounts = tbodytrs.length;
	if (itemCounts == 1) {
		var blankTR = $(tbodytrs[0]);
		if (blankTR.find("td").length == 0) {
			blankTR.remove();
			itemCounts = 0;
		}
	}
	var tr = $("<tr id='item_tr_" + itemCounts + "'></tr>");
	var td0_0 = $("<td>" + (itemCounts+1) + "</td>");
	var td0 = $("<td><input type='hidden' name='item_id" + itemCounts + "' /><input type='text' style='width:100%;' name='item_code" + itemCounts + "'/></td>");
	var td1 = $("<td><input type='text' style='width:100%;' name='item_name" + itemCounts + "'/></td>");
	var td2 = $("<td><input type='text' style='width:100%;' name='item_standard" + itemCounts + "'/></td>");
	var td3 = $("<td><input type='text' style='width:100%;' name='item_unit" + itemCounts + "'/></td>");
	var td4 = $("<td></td>");
	var td5 = $("<td></td>");

	var ip3 = $("<input type='text' style='width:100%;' name='item_count" + itemCounts + "'/>")
	ip3.blur(function() {
		setTotalPrice(this);
	});
	var ip4 = $("<input type='text' style='width:100%;' name='item_price" + itemCounts + "'/>")
	ip4.blur(function() {
		setTotalPrice(this);
	});
	td4.append(ip3);
	td5.append(ip4);
	var td6 = $("<td><input type='text' style='width:100%;' id='f_totalPrice' name='item_total_price" + itemCounts + "' readonly='readonly'/></td>");
	var td7 = $("<td><input type='text' style='width:100%;' name='item_remark" + itemCounts + "'/></td>");
	var td8 = $("<td><a href='javascript:selectItem(" + itemCounts + ");'>选择</a>&nbsp;&nbsp;<a href='javascript:removeItem(\"item_tr_" + itemCounts + "\");'>删除</a></td>");
	tr.append(td0_0);
	tr.append(td0);
	tr.append(td1);
	tr.append(td2);
	tr.append(td3);
	tr.append(td4);
	tr.append(td5);
	tr.append(td6);
	tr.append(td7);
	tr.append(td8);
	tbody.append(tr);
}
var current_item_tr = null;
function selectItem(currIndex) {
	current_item_tr = currIndex;
	$('#itemSelectTableDiv').dialog("open");
}
function removeItem(trId) {
	$("#" + trId).remove();
	setAmount();
}