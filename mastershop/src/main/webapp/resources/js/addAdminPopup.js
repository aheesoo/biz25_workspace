function sualchk(chkfield){
		var checkOK = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var checkStr = chkfield;
		var allValid = true;
		var allNum = "";
		checkStr = checkStr.toUpperCase();
		for (var i = 0;  i < checkStr.length;  i++)
		{
		  ch = checkStr.charAt(i);
		  for (var j = 0;  j < checkOK.length;  j++)
			if (ch == checkOK.charAt(j))
				break;
		    	if (j == checkOK.length)
		    	{
		      		allValid = false;
		      		break;
		    	}
		}
	if (allValid == false) return false;
	if (allValid == true) return true;
}

function suchk(chkfield){
		var checkOK = "0123456789";
		var checkStr = chkfield;
		var allValid = true;
		var allNum = "";
		for (var i = 0;  i < checkStr.length;  i++)
		{
		  ch = checkStr.charAt(i);
		  for (var j = 0;  j < checkOK.length;  j++)
			if (ch == checkOK.charAt(j))
				break;
		    	if (j == checkOK.length)
		    	{
		      		allValid = false;
		      		break;
		    	}
		}
		
	if (allValid == false) return false;
	if (allValid == true) return true;
}

function gulsu(chkfield,textsu){
		var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		var checkStr = chkfield;
		var txtsu = eval(textsu);
		var allNum = 0;
		checkStr = checkStr.toUpperCase();
		for (var i = 0;  i < checkStr.length;  i++)
		{
		  ch = checkStr.charAt(i);
		  for (var j = 0;  j < checkOK.length;  j++)
			{
			if (ch == checkOK.charAt(j))
				{
				allNum = allNum + 1;
				break;
				}
		    	if (j+1 == checkOK.length)
		    		{
		      		allNum = allNum + 2;
		      		break;
		    		}
			}
		}
	if (allNum <= txtsu) return true;
	if (allNum > txtsu) return false;
}

function email(chkfield){
	var checkOK = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ@._-";
	var checkStr = chkfield;
	var allValid = true;
	var allNum = "";
	checkStr = checkStr.toUpperCase();
	for (var i = 0;  i < checkStr.length;  i++) {
		ch = checkStr.charAt(i);
		for (var j = 0;  j < checkOK.length;  j++)
			if (ch == checkOK.charAt(j))
			break;
			if (j == checkOK.length) {
				allValid = false;
				break;
			}
	}
		if (allValid == false) return false;
		if (allValid == true) return true;
}

function removeblank(s){
	var temp="";
	var cou=0;
	for(var i=0;i<s.length;++i){
		var c=s.charAt(i);
		if(c!=" ") temp +=c;
	}
	return temp;
}

//전화번호 구분자 '-' 체크
function mysplit(checkstr, substr) {
	var i, j, pos;
	j = pos = 0;
	for(i = 0; i < checkstr.length; i++) {
		ch = checkstr.charAt(i);
		if(ch == substr) {
			j = j + 1;
		}
	}
	
	var myarray = new makearray(j-1);
	j = 0;
	for(i = 0; i < checkstr.length; i++) {
		ch = checkstr.charAt(i);
		if(ch == substr) {
			j = j + 1;
			if(j == 1) {
				myarray[j-1] = checkstr.substring(0,1);
			} else {
				myarray[j-1] = checkstr.substring(pos+1,i);
			}
			pos = i;
		}
	}
	myarray[j] = checkstr.substring(pos+1,checkstr.length);
	return (myarray);	
}

function myubound(checkarray) {
	var i = 0;
	var j = 0;
	while(true) {
		if(checkarray[i] != null) {
			j = i;
		} else {
			break;
		}
		i = i + 1;
	}
	return (j);
}

function makearray(n) {
	this.length = n;
	for(var i = 1; i <= n; i++) {
		this[i] = 0;
		return this;
	}
}

function addToFavorite(favTitle,url)
{
	if ((navigator.appVersion.indexOf("MSIE") > 0) && (parseInt(navigator.appVersion) >= 4))
	{
		window.external.AddFavorite(url, unescape(favTitle));
	}
}

//해당 폼에 대한 널 값 확인.
function validateForm(arrObjDesc) 
{ 	
	for(var i=0; i < arrObjDesc.length; i++) 
	{
		for(var j=0; j < arrObjDesc[i].length ;j++) 
		{
			var objForm = document.getElementById(arrObjDesc[i][j][0]);

			if(validateData(objForm, arrObjDesc[i][j][1], arrObjDesc[i][j][2]) == false) 
			{
				if(objForm.type != "hidden" && objForm.disabled == false)
				{
					objForm.focus();
				}
				event.returnValue = false;
				return false; 
			} 
		} 
	} 
	
	return true;
}

function validateData(objValue, strValidateStr, strError) 
{
    var epos = strValidateStr.search("="); 
    var command  = ""; 
    var cmdvalue = ""; 
    
    if(epos >= 0) 
    { 
		command  = strValidateStr.substring(0,epos); 
		cmdvalue = strValidateStr.substr(epos+1); 
    } 
    else 
    { 
		command = strValidateStr;
    }
    
    switch(command) 
    { 
		case "req":             
        case "required": 
		{
		   var tp = objValue.value.replace(/\s/g,'');
           if(eval(tp.length) == 0) 
           { 
              alert(strError);
              return false; 
           } 
           break;             
        }
        
        case "maxlen": 
        case "maxlength": 
        { 
             if(eval(objValue.value.length) >  eval(cmdvalue)) 
             { 
				alert(strError);
				return false; 
             }
             break; 
        }
        
        case "minlen": 
        case "minlength": 
        { 
             if(eval(objValue.value.length) <  eval(cmdvalue) && eval(objValue.value.length) != eval(0)) 
             { 
				alert(strError);
				return false;                 
             }
             break; 
        }           
        
        case "equallen": 
        case "equallength": 
        { 
             if(eval(objValue.value.length) !=  eval(cmdvalue)) 
             { 
				alert(strError);
				return false;                 
             }
             break; 
        }    
        
        case "equalvalue": 
        { 
			if(objValue.value != document.all[cmdvalue].value ) 
			{ 
				alert(strError);
				return false;                 
			}
			break; 
        } 
        
        case "equalString": 
        { 
			if(objValue.value == cmdvalue+"" ) 
			{ 
				alert(strError);
				objValue.focus();
				return false;                 
			}
			break; 
        }                
        
        case "eng":
        case "english": 
        { 
              var charpos = objValue.value.search("[^A-Za-z]"); 
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
				alert(strError);
				return false; 
              } 
              break; 
        }
        
        case "num": 
        case "numeric": 
        { 
              var charpos = objValue.value.search("[^0-9\]"); 
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
				alert(strError);
				return false; 
              } 
              break;               
        }
        
        case "float": 
        { 
            var count = 0;
            var numbers = objValue.value.split('.');
            for( count = 0; count < numbers.length; count++)
            {
              var charpos = numbers[count].search("[^0-9\]"); 
              if(numbers[count].length > 0 &&  charpos >= 0) 
              { 
				alert(strError);
				return false; 
              } 
            }
            
              break;               
        }   
        case "NumComma":
        {
            var chars = ",0123456789";
            
            for (var inx = 0; inx < objValue.value.length; inx++) {
                if (chars.indexOf(objValue.value.charAt(inx)) == -1)
                return false;
            }
            return true;

            break;
        }
        
        case "NoZeroNum": 
        { 
            var objNumberI = objValue.value;
            objNumberI = objNumberI.replace('.', '');
            
            var charpos = objNumberI.search("[^0-9\]"); 
            if(objNumberI.length > 0 &&  charpos >= 0) 
            { 
                alert(strError);
                return false; 
            } 
              
            objNumberI = objNumberI.replace(/0/g, '');
			if(objNumberI == '') 
			{ 
				alert(strError);
				objValue.focus();
				return false;                 
			}
			break; 
        }
        
        case "date": 
        { 
            var _date;
            _date = objValue.value.replaceAll('/','');
            _date = objValue.value.replaceAll('-','');

            if( _date != "" && !validateDate(_date))
            {
                alert(strError);
                objValue.focus();
                return false;
            }
            break;
        }     
        
        case "han" :
		case "hangul":
		{
              var charpos = objValue.value.search("[^????"); 
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
                alert(strError);
                return false; 
              }
              break;
		}
        
        case "engnum": 
        case "engnumeric": 
        { 
              var charpos = objValue.value.search("[^A-Za-z0-9]"); 
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
                alert(strError);
                return false; 
              }
              break; 
        }
		
		case "hannum" :
		case "hangulnumeric":
		{
              var charpos = objValue.value.search("[^????-9]");
              alert(charpos);
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
                alert(strError);
                objValue.select();
				objValue.focus();
                return false; 
              } 			
				break;
		}
		
		case "hanalpha" :
		case "hangulalphabetic":
		{
              var charpos = objValue.value.search("[^?????Za-z]"); 
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
                alert(strError);
                return false; 
              } 			
				break;
		}
		
		case "hanengnum" :
		case "hangulenglishnumeric":
		{
              var charpos = objValue.value.search("[^?????Za-z0-9]"); 
              if(objValue.value.length > 0 &&  charpos >= 0) 
              { 
                alert(strError);
                return false; 
              } 			
				break;
		}
		// 확인
        case "regexp": 
        { 
		 	if(objValue.value.length > 0)
			{
	            if(!objValue.value.match(cmdvalue)) 
	            { 
	              alert(strError);
	              return false;                   
	            } 
			}
           break; 
        } 
        // 이메일 비교
        case "email": 
        { 
               if(!validateEmailv2(objValue.value)) 
               { 
                 alert(strError);
                 return false; 
               } 
           break; 
        } 
        // 적은값 비교
        case "lt": 
        case "lessthan": 
        { 
            if(isNaN(objValue.value)) 
            { 
              return false; 
            }
            if(eval(objValue.value) >=  eval(cmdvalue)) 
            { 
              alert(strError);
              return false;                 
             }             
            break; 
        } 
        // 큰값비교
        case "gt": 
        case "greaterthan": 
        { 
            if(isNaN(objValue.value)) 
            { 
              return false; 
            }
            if(eval(objValue.value) <=  eval(cmdvalue)) 
            { 
               alert(strError); 
               return false;                 
            }             
            break; 
        }      
        
        // 선택 하지 않음.
        case "dontselect": 
        { 
            if(objValue.selectedIndex == null) 
            { 
              return false;
            } 
            if(objValue.selectedIndex == eval(cmdvalue)) 
            { 
              alert(strError);
              return false;                                   
            } 
            break; 
        }
        // 이미지 화일 확인.
        case "CheckImgFile":
        {
			if( objValue.value.length > 0)
			{
				var index = objValue.value.lastIndexOf(".");
				var strExt = objValue.value.substr(index);
			
				if(strExt.toLowerCase() != ".jpg" && strExt.toLowerCase() != ".gif" && strExt.toLowerCase() != ".png")
				{
					alert(strError);
					return false;
				}
			}
			break;
        }
        // 체크박스
        case "checked":
        {
			if(objValue.checked == false)
			{
				alert(strError);
				return false;
			}
			break;
        }
        // 리스트 박스
        case "listbox":
        {
			if( objValue.all[0] == null)
			{
				alert(strError);
				return false;
			}
			break;
        }
        // IP주소
        case "ipaddress":
        {
			if (objValue.value != "")
			{
				var ipaddress = /^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])$/;
				var check = ipaddress.exec(objValue.value);

				if(!check)
				{
					alert(strError);
					return false;
				}
			}			
			break;
        }
        // 포트
        case "port":
		{
			var port = /^(6553[0-5]|655[0-2][0-9]|65[0-4][0-9][0-9]|6[0-4][0-9][0-9][0-9]|[0-5]?[0-9]?[0-9]?[0-9]?[0-9])$/;
			var check = port.exec(objValue.value);
			
			if(!check)
			{
				alert(strError);
				return false;
			}
			break;
		}
		// URL
        case "url":
        {
			var ser = "http://";
			var str = objValue.value.toLowerCase();
			
			if( str == "" )
			  return true;
			
			
			if( str.search( ser ) == -1 )
			{
				alert(strError);
				return false;
			}
			else
			{
				return true;
			}
			break;
        }
    }
    return true; 
}


function validateEmailv2(email)
{
    if(email.length <= 0)
	{
	  return true;
	}
    var splitted = email.match("^(.+)@(.+)$");
    if(splitted == null) return false;
    if(splitted[1] != null )
    {
      var regexp_user=/^\"?[\w-_\.]*\"?$/;
      if(splitted[1].match(regexp_user) == null)
		return false;
    }
    if(splitted[2] != null)
    {
      var regexp_domain=/^[\w-\.]*\.[A-Za-z]{2,4}$/;
      if(splitted[2].match(regexp_domain) == null) 
      {
	    var regexp_ip =/^\[\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}\]$/;
	    if(splitted[2].match(regexp_ip) == null) return false;
      }
      return true;
    }
	return false;
}


//'****************************************************************************************
//'* Description: 배열 랜덤 프로토타입 정의
//'****************************************************************************************            
Array.prototype.shuffle = function(){ 
  return this.concat().sort( 
	function(){return Math.random() - Math.random();} 
  );
};

//'****************************************************************************************
//'* Description: input 숫자만 허용
//'****************************************************************************************            
function onlyNumber(el){
	el.value = el.value.replace(/\D/g,'');
	el.blur();
	el.focus();
}

//'****************************************************************************************
//'* Description: 천단위 콤마 포멧
//'****************************************************************************************            
function number_format_value(get_number)
{
	get_number += '';
	x = get_number.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}
function number_format(el)
{
    var pattern = /[^.0-9-]/g;
	el.value = el.value.replace(pattern,'');

	el.value += '';
	x = el.value.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	el.value = x1 + x2;
}

function del_comma(number)
{
	return number.replace(/,/gi,'');
}


//'****************************************************************************************
//'* Description: 공백제거 프로토타입 정의
//'****************************************************************************************            
String.prototype.trim = function(){
    return this.replace(/(^[ \f\n\r\t]*)|([ \f\n\r\t]*$)/g, "");
};

function dynamic2Ajax(src){
	arr_src			= src.split("?");
	//arr_data	= arr_src[1].split("&");
	var submit_type = $.browser.mozilla==true ? "post" : "get";
	$.ajax({
		type		: submit_type
		,url		: arr_src[0]
		,data		: arr_src[1]
	 	,dataType	: 'script'	//xml, html, script, json 지정
		,timeout	: 3000				
		,success	: function(data) {
			//alert( $.trim(data) );
			//var dumy = new Function(data);
			//dumy();
			//data;
		}
		,error: function(xhr, status, error) {
			alert('잠시 후 다시 시도해 주세요.');
		}
	});//close $.ajax(
}

function only_phone(el) {
	var pattern = /[^-0-9]/g;
	el.value = el.value.replace(pattern,'');
} 

function onlyNumber(el) {
	el.value = el.value.replace(/\D/g,'');
}
