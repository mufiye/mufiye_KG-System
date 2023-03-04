//定义时间变量存储服务器时间
var date_time="";
//获取当前浏览器窗口宽度
var pageWidth = parent.document.documentElement.clientWidth;

//获取地址栏参数的值，支持中文
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return decodeURI(decodeURI(r[2]));
	return null;
}

//根据屏幕分辨率自适应表格列数
//浏览器窗口宽度在1400像素以下时，data-width='1400'的元素将移除
//浏览器窗口宽度在1100像素以下的，data-width='1400'和'1100'的元素都会移除
function adapt() {
	if(pageWidth<1400){
		$("[data-width='1400']").remove();
		if(pageWidth<1100){
			$("[data-width='1100']").remove();
		}
	}
}

//--------------------以下方法主要供Handlebars插件注册自定义方法时调用，也可直接使用(第一个方法除外)--------------------

//用于对指定的列执行不同的操作，本项目用于对指定序号的列添加data-width属性，与adapt()方法配合使用，根据浏览器窗口宽度自动移除相应元素
//v1一般为@index表示当前遍历的列序号，v2为形如“1,2,3”的字符串
//若遍历到第1,2,3项则执行if否则执行else
function equal(v1,v2,options){
	var temp = v2.split(",");
	for(var i=0; i<temp.length; i++){
		if(v1==temp[i])
			return options.fn(this);
	}
	return options.inverse(this);
}

//value长度小于等于len个字节时，返回value
//value长度大于len个字节时，返回前len个 字符+“...”
function formatLen(value,len){
	if(value==null){
		return value;
	}else{
		var val = String(value);
		return CutStr(val,len);
	}
}



//浏览器窗口宽度1100像素以下时，使value不超过a个字节
//浏览器窗口宽度1100-1400像素时，使value不超过b个字节
//浏览器窗口宽度1400-1700像素时，使value不超过c个字节
//浏览器窗口宽度1700-2000像素时，使value不超过d个字节
function adaptLen(value,a,b,c,d){
	if(value == undefined){
		return '';
	}
	var val = String(value);
	if(pageWidth<1100){
		return CutStr(val,a);
	}else if(pageWidth<1400){
		return CutStr(val,b);
	}else if(pageWidth<1700){
		return CutStr(val,c);
	}else if(pageWidth<2000){
		return CutStr(val,d);
	}
}

//--------------------以上方法主要供Handlebars插件注册自定义方法时调用，也可直接使用(第一个方法除外)--------------------

//控制str长度不超过len的具体方法
function CutStr(str, len) {
	if(len==0)
		return str;
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4  
            str_length++;
        }
        if (str_length > len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
        str_cut = str_cut.concat(a);
    }
    //如果给定字符串小于指定长度，则返回源字符串；  
    if (str_length <= len) {
        return str;
    }
}

//实现ie8不支持的function
function initFc(){
	if ( !Array.prototype.forEach ) {
	    Array.prototype.forEach = function forEach( callback, thisArg ) {
	        var T, k;
	        if ( this == null ) {
	            throw new TypeError( "this is null or not defined" );
	        }
	        var O = Object(this);
	        var len = O.length >>> 0;
	        if ( typeof callback !== "function" ) {
	            throw new TypeError( callback + " is not a function" );
	        }
	        if ( arguments.length > 1 ) {
	            T = thisArg;
	        }
	        k = 0;
	        while( k < len ) {
	            var kValue;
	            if ( k in O ) {
	                kValue = O[k];
	                callback.call( T, kValue, k, O );
	            }
	            k++;
	        }
	    };
	}
	if (!Array.prototype.indexOf)
	{
	  Array.prototype.indexOf = function(elt /*, from*/)
	  {
	    var len = this.length >>> 0;
	    var from = Number(arguments[1]) || 0;
	    from = (from < 0)
	         ? Math.ceil(from)
	         : Math.floor(from);
	    if (from < 0)
	      from += len;
	    for (; from < len; from++)
	    {
	      if (from in this &&
	          this[from] === elt)
	        return from;
	    }
	    return -1;
	  };
	}
}
//日期格式化
Date.prototype.Format = function(format){ 
	var o = { 
	"M+" : this.getMonth()+1, //month 

	"d+" : this.getDate(), //day 

	"h+" : this.getHours(), //hour 

	"m+" : this.getMinutes(), //minute 

	"s+" : this.getSeconds(), //second 

	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 

	"S" : this.getMilliseconds() //millisecond 
	}
	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	}
	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	 } 
	} 
	return format; 
}
