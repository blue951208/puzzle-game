/**
 * 
 */$(document).ready(function(){
	 
	 	let getRandom = function(num){
	 		let my_num = Math.floor(Math.random()*num);
	 		return my_num;
	 	}
	 	
	 	let hideCode = function(){
	 		let numRand = getRandom(4);
	 		numRand = 0;
	 		$(".guess_box").each(function(index,value){
	 			if(numRand === index){
	 				$(this).append("<span id='has_discount'></span>");
	 				//함수 종료
	 				return false;
	 			}
	 		});
	 	}
	 	
	 	hideCode();
	 	
	 	let checkForCode = function(){
	 		let discount = "";
	 		if($.contains(this,document.getElementById("has_discount")))
	 			//id가 has_discount인 것을 찾는다.
	 			{
	 			let timeStamp = new Date().getTime();
	 			let my_num = getRandom(100);
	 			discount = "<p>Your Code : CODE" + timeStamp + my_num +"</p>";
	 			}else{
	 				discount = "<p>sorry, no discount this time</p>";
	 			}
	 		$(this).append(discount);
	 		$(".guess_box").each(function(){
	 			if($.contains(this,document.getElementById("has_discount"))){
	 				$(this).addClass("discount");
	 			}else{
	 				$(this).addClass("no_discount");
	 			}
	 			$(this).unbind('click');
	 		});
	 	}
	 	$(".guess_box").click(checkForCode);
 });
		/*$(".guess_Box").click(function(){
			//$(".guess_Box p").remove();//guess_Box태그안에 p태그
		 //random	
		 let discount = Math.floor(Math.random()*5)+5;
			let discount_msg = "<p>youre Discount is "+discount+"%</p>";
			$(this).append(discount_msg);
			//클릭기능 상실
			$(".guess_box").each(function(){
				$(this).unbind("click");
			});
			//$("guess_box").unbind
		});*/
	 	