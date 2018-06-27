var pprovince=new Array("河南","北京","广东");
			var ccity=new Array(3);
			var ccontry=new Array(3); 
			ccity[0]=new Array("郑州","驻马店","开封");
			ccity[1]=new Array("朝阳","北京市","海淀");
			ccity[2]=["广州","东皖","佛山"];
			
			ccontry[0]=[ ["金水区","二七区","中原区"], ["新蔡县","上蔡县","正阳县"],["龙亭区","顺水区","鼓楼区"]  ];
			
			ccontry[1]=[["朝阳南","朝阳北","朝阳西"], ["北京市东","北京市西","北京市南"],["海淀南","海淀北","海淀西"]];
			
	
			
			ccontry[2]=[["天河区","白云区","海珠区"],["虎门镇","东坑镇","常平镇"],["禅城","南海","顺德"]];
			
			//从数组中的给定值获取其下标
			
			function getIndexOf( arr,str)
			{
				for(var i=0;i<arr.length;i++)
				{
					if(arr[i]==str)
					{
						return i;
						
					}
				}
				return -1;
			}
			
			function provinceChange()
			{
				var province=document.getElementById("province");
				var city=document.getElementById("city");
				var country=document.getElementById("country");
				city.innerHTML="<option>---请选择---</option>";
						country.innerHTML="<option>---请选择---</option>";	
						
				var index=	getIndexOf(pprovince,province.value)
				for(var i=0;i<ccity[index].length;i++)
				{
					var op=document.createElement("option");
					op.value=ccity[index][i];
					op.innerHTML=ccity[index][i];
					city.appendChild(op);
				}
			}
			
			function cityChange(){
				var province=document.getElementById("province");
				var city=document.getElementById("city");
				var country=document.getElementById("country");
				country.innerHTML="<option>---请选择---</option>";
				
				var p_index=getIndexOf(pprovince,province.value);
				var c_index=getIndexOf(ccity[p_index],city.value);
				
				for(var i=0;i<ccontry[p_index][c_index].length;i++)
				{
					var op=document.createElement("option");
					op.value=ccontry[p_index][c_index][i];
					op.innerHTML=ccontry[p_index][c_index][i];
					country.appendChild(op);
				}
				
				
				
			}