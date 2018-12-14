function changeCode(obj) {
	obj.src = "pages/image.jsp?temp=" + Math.random();
}

function preview(file) {
	var prevDiv = document.getElementById('preview');
	if (file.files && file.files[0]) {
		var reader = new FileReader();
		reader.onload = function(evt) {
			prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
		}
		reader.readAsDataURL(file.files[0]);
	} else {
		prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\''
				+ file.value + '\'"></div>';
	}
}
function openPage(url){
	window.open(url,"查看详细信息","width=600;height=400;scollable=yes");
}
function closePage(){
	window.close();
}

function checkboxSelect(obj,eleName){//全选或全不选;
//	console.log(eleName);
//	console.log(obj);
	var item = document.all(eleName);//取得多个元素对象,适用于单选"radio"和复选"checkbox";
//	console.log(item);
	if(item.length == undefined) {//表示item只有一个元素,而不是数组;
		document.getElementById(eleName).checked = obj.checked;//将checked标记赋给此元素;
	}else{//表示item是数组;
		for(var i = 0 ; i < item.length ; i ++){
			item[i].checked = obj.checked;//将checked标记赋给所有多选框中元素;
		}
	}
}

function deleteAll(url,paramName,eleName){//url=删除操作的路径;paramName=向url页面传递的参数名称;eleName=本页面取得数据的ID名称;
	alert("url = " +url);
	var data = "";//data保存所有要删除的数据编号;有可能是一个数据或一个数组;
	var item = document.all(eleName);//取得多个元素对象适用于单选"radio"和复选"checkbox";
	var count = 0 ;//count保存要删除的数据个数;
	if(item.length == undefined) {//表示item只有一个元素,而不是数组;
		if(document.getElementById(eleName).checked){
			data += document.getElementById(eleName).value + "|";
			count ++;
		}
	}else{//item是数组;
		for(var i = 0 ; i < item.length ; i ++){
			if(item[i].checked){
				count ++;
				data += item[i].value + "|";//data=[11|99|10|20|]
			}
		}
	}
	if (count > 0){
		if (window.confirm("确定要删除这些数据吗???")){
		window.location = url + "&" + paramName + "=" +data ;//http://localhost:80/EmpProject/pages/back/admin/dept/dept_delete.jsp?dno=11|99|10|20|
//		console.log("count = " + count + "; data = " + data);
//		console.log("url = " + url + "?" + paramName + "=" +data);
		}
	}else {
		alert("您还未选择任何要删除的数据!");	
	}
}


function changeColor(obj,color){
	obj.bgColor = color;
}
function validateMid(){
	return validateEmpty("mid");
}
function validatePassword(){
	return validateEmpty("password");
}
function validate(){
	return validateMid() && validatePassword();
}
function validateEmpty(elename){//验证输入是否为空;
	var obj = document.getElementById(elename);
	var msg = document.getElementById(elename + "Msg");
	//if(elementName == "pwd"){
	//	var spanMsg = document.getElementById("pwdMsg");
	//}else{
	//	var spanMsg = document.getElementById("pwdConfMsg");
	//}
	try{
		if(obj.value != ""){
			obj.calssName = "right";
			if(msg != null){
				msg.innerHTML = "<font color='green'>输入有数据!</font>";
			}
			return true;
		}else{
			obj.calssName = "wrong";
			if(msg != null){
				msg.innerHTML = "<font color='red'>输入不能为空!</font>";
			}
			return false;
		}
	}catch (Exception){
		return false;
	}
}



function validateRepeat(srcElement,desElement){//验证输入2次密码是否一致;
	var src = document.getElementById(srcElement);
	var des = document.getElementById(desElement);
	var msgElement = document.getElementById(desElement + "Msg");
	if(src.value == des.value){
		msgElement.innerHTML += "<font color = 'green'> 密码确认正确!</font>";
		return true;
	}else{
		msgElement.innerHTML += "<font color = 'red'> 密码确认错误!</font>";
		return false;
	}
}
function validatePwd(){
	return validatePwdEmpty("pwd");
	return validateRepeat("pwd" , "pwdConf");	
}
function validatePwdConf(){
	if(validatePwdEmpty("pwdConf")){
		return validateRepeat("pwd","pwdConf");
	}
	return false;				
}
function validateEmail(){//验证输入邮箱格式是否正确;
	var email = document.getElementById("email");
	var msg = document.getElementById("emailMsg");
	if(/^\w+@+\w+\.\w+$/.test(email.value)){
		//alert("合法邮箱!");
		email.className = "right";
		msg.innerHTML = "<font color='green'>合法邮箱!</font>";
		return true;
	}else{
		//alert("非法邮箱!");
		email.className = "wrong";
		msg.innerHTML = "<font color='red'>非法邮箱!</font>";
		return false;
	}
	//			"/^正则表达式$/.test(检测的数据)"
	//alert(email.value);
}
function showSex(){
	var sex = document.all("sex");//取得多个元素对象适用于单选"radio"和复选"select";
	for(var i = 0 ; i < sex.length ; i ++){
		//alert(sex[i].value + " : " +sex[i].checked );
		if(sex[i].checked){
			alert("选中的是 : " + sex[i].value);
		}
	}
}
function showInst(){
	var inst = document.all("inst");//取得多个元素对象适用于单选"radio"和复选"checkbox";
	var str = "选中的是 ： ";
	for(var i = 0 ; i < inst.length ; i ++){
		//alert(sex[i].value + " : " +sex[i].checked );
		if(inst[i].checked){
			str += inst[i].value + "、";
		}
	}
	alert(str);
}

function validateNote(){//验证文本框输入长度是否正确;
	var note = document.getElementById("note");
	var msg = document.getElementById("noteMsg");
	var length = note.value.length;
	msg.innerHTML = "输入数据长度 : " + (length + 1);
	if(length < 10){
		msg.innerHTML += " <font color='green'>输入合法</font>" + " ; 还可以输入 : " + (10-length) + " 个 字符;";
		document.getElementById("sub").disabled = false;
	}else{
		msg.innerHTML += " <font color='red'>输入非法</font>";
		document.getElementById("sub").disabled = true;
	}
}
function validateRegex(elename,regex){//验证是否符合正则表达式;
	var obj = document.getElementById(elename);
	var msg = document.getElementById(elename + "Msg");
	try{
		if(regex.test(obj.value)){
			obj.calssName = "right";
			if(msg != null){
				msg.innerHTML = "<font color='green'>正则验证正确!</font>";
			}
			return true;
		}else{
			obj.calssName = "wrong";
			if(msg != null){
				msg.innerHTML = "<font color='red'>正则验证出错!</font>";
			}
			return false;
		}
	}catch (Exception){
	return false;
	}
}























/*
window.onload = function setClock(){//显示当前时间
	var date = new Date();
	var div = document.getElementById("div2");
	div.innerHTML = "<h5>当前时间 : " + date +"</h5>";
	setTimeout(setClock,1000);//设置1秒刷新1次;
}
*/

/*
	JavaScript-说明:
	"&nbsp;"=空格;<br />=换行;<hr />=划一条横线;
	"javascript:void(0);"=JavaScript伪协议;
	"函数名.prototype"=prototype对原生函数扩展
	
	JavaScript-变量:
	变量没有类型,统一使用[var]声明;数据类型由变量值决定;变量名区分大小写;例=var a = 1;
	变量值有6种类型:undefined、null、boolean、String、number、object。
	数据类型自动转换;[==]会自动转换;[===]不会自动转换;
		布尔型=字符串=数字;例=[true="true"=1];
		数字=字符串;例=[123="123"];
		null=布尔型=数字=字符串;例=[null="false"=0="null"];
		undefined=布尔型=数字=字符串;例=[undefined="false"=0="undefined"];
		例=
		var a = "ture";var b = true;var c = 1;这里a==b==c都为true;但a===b===c都为false
	
	JavaScript-重要函数:
		document.write();输出HTML代码在浏览器页面上;
		setTimeout(函数,时间单位毫秒)=指定的毫秒数后调用函数。
	
	JavaScript-内部函数:
		eval(String str);str可以是JavaScript代码,实现动态改变页面;
		parseInt(String str)/parseFloat(String str);
		escape()/unEscape();
		isNaN();
		isFinite();
		parseInt();
		parseFloat();

	JavaScript-标签-<script>标签="//"=单行注释
		用法1=写在<head>、<body>标签中;
		用法2=编写*.js文件;在.html文件的<head>标签中通过<script>标签引入.js文件;
		例=
		步骤1=编写*.js文件;
		步骤2=在.html文件的<head>标签中设置<script src="*.js" type="" />;

	JavaScript-事件;命名形式="onXXXX";例=页面加载事件=onload---☆☆☆;页面卸载事件=onunload;
	JavaScript-ID、动态设置=addEventListener(事件类型,处理函数名称,触发时机);
		配合使用document.getElementById("myid")取得"myid"元素的对象;
		步骤1=为标签设置id,例<hr id="hr1">
*/