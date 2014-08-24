(function ($) {
var zjAddressDatas = [
            {"省直" : []},
            {"部属" : []},
			{"杭州市" : [ "上城区", "下城区", "江干区", "拱墅区", "西湖区", "滨江区", "萧山区","余杭区", "桐庐县", "淳安县", "建德市", "富阳市", "临安市","经济技术开发区","钱江经济开发区","未来科技城","青山湖科技城"]},
			{"宁波市" : [ "海曙区", "江东区", "江北区", "北仑区", "镇海区", "鄞州区", "象山县","宁海县", "余姚市", "慈溪市", "奉化市","高新区","大榭开发区","宁波保税区","杭州湾新区","东钱湖旅游度假区" ]},
			{"温州市" : [ "鹿城区", "瓯海区", "龙湾区", "洞头县", "永嘉县", "平阳县", "苍南县","文成县", "泰顺县", "瑞安市", "乐清市" ,"经济开发区","高新区"]},
			{"嘉兴市" : [ "南湖区", "秀洲区", "嘉善县", "海盐县", "海宁市", "平湖市", "桐乡市" ]},
			{"湖州市" : [ "吴兴区", "南浔区", "德清县", "长兴县", "安吉县","经济开发区"]},
			{"绍兴市" : [ "越城区", "绍兴县", "新昌县", "诸暨市", "上虞市", "嵊州市","经济开发区", "袍江工业区","镜湖新区","滨海新城"]},
			{"金华市" : [ "婺城区", "金东区", "武义县", "浦江县", "磐安县", "兰溪市", "义乌市","东阳市", "永康市" ]},
			{"衢州市" : [ "柯城区", "衢江区", "常山县", "开化县", "龙游县", "江山市" ]},
			{"舟山市" : [ "定海区", "普陀区", "岱山县", "嵊泗县" ]},
			{"台州市" : [ "椒江区", "黄岩区", "路桥区", "玉环县", "三门县", "天台县", "仙居县","温岭市", "临海市","经济开发区" ]},
			{"丽水市" : [ "莲都区", "青田县", "缙云县", "遂昌县", "松阳县", "云和县", "庆元县","景宁畲族自治县", "龙泉市","经济开发区" ]} ]

var addr = {
		//取得浙江省所有市数据
		getCityContent: function () {
		    var cities = [];
		    var length = zjAddressDatas.length;  
		    var tdTpl = "<option>{0}</option>";
	        for (p in zjAddressDatas) {
	        	for (q in zjAddressDatas[p]) {
	        		cities.push(q);
	        	}
	        }
	        
	        return cities;
		},

		//取得区县数据
		getDistrictContent: function (cityValue) {
			var districts = [];
			//var city = opt.city.val();
			var length = zjAddressDatas.length;
			for (p in zjAddressDatas) {
				for (q in zjAddressDatas[p]) {
		            if (q == cityValue) {
		            	districts = zjAddressDatas[p][q];
		                break;
		            }
	        	}
	        }
			return districts;
		},
		
		setSelectItems: function(obj,data){
			obj.html("");
			obj.append('<option value="">　　　</option>');
			for(var i=0;i<data.length;i++){
				obj.append('<option value="' + data[i] + '">' + data[i] + '</option>');
			}
		}
	};

$.fn.zjAddressSelector = function (options) {
    return $(this).each(function () {
    	var  opt = $.extend({}, $.fn.zjAddressSelector.defaults, options),
        	$this = $(this);
    	
    	if($this.find(opt.city).length >0){
    		opt.city = $this.find(opt.city);
    		addr.setSelectItems(opt.city, addr.getCityContent());
    	}
    	opt.district = $this.find(opt.district);
    	
    	if(opt.originalAddress && $(opt.originalAddress).length == 1 && $.trim($(opt.originalAddress).val())!=""){
    		var city_datas = $(opt.originalAddress).val().split('/');
    		if(city_datas.length == 2){
    			if(typeof(opt.city) != "string")
    				opt.city.val(city_datas[0]);
    			addr.setSelectItems(opt.district, addr.getDistrictContent(city_datas[0]));
    			opt.district.val(city_datas[1]);
    		}else if(city_datas.length == 1){
    			if(typeof(opt.city) != "string")
    				opt.city.val(city_datas[0]);
    			addr.setSelectItems(opt.district, addr.getDistrictContent(city_datas[0]));
    		}
    		
    		if(typeof(opt.city) != "string"){
    			if(opt.city.val() == "省直" || opt.city.val() == "部属"){
    				opt.district.val("");
    				opt.district.hide();
    			}else{
    				opt.district.show();
    			}	
    		}
    	}else{
    		opt.district.append('<option value="">　　　</option>');
    	}

    	if(typeof(opt.city) != "string"){
    		opt.city.change(function(){
        		if($(this).val() !=""){
        			if(typeof(opt.city) != "string"){
	        			if($(this).val() == "省直" || $(this).val() == "部属"){
	        				opt.district.val("");
	        				opt.district.hide();
	        			}else{
	        				opt.district.show();
	        			}
        			}
        			addr.setSelectItems(opt.district, addr.getDistrictContent($(this).val()));
        		}
        	});
    	}
    });
};

$.fn.zjAddressSelector.defaults = {
	originalAddress:null,
    city: ".city",
    district: ".district"
};
})(jQuery);