
function  getXMLHttp(){
	
	if(window.XMLHttpRequest)
	{
		return new XMLHttpRequest();
	}else{
		return ActiveXObject("Mircosoft.XMLHTTP");
	}
}