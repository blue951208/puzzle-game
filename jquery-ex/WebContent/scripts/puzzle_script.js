/**
 * 
 */
	$(document).ready(function(){
		let model = [
			"heart11.JPG","heart11.JPG",
			"heart6.JPG","heart12.JPG",
			"heart5.JPG","heart13.JPG",
			"heart4.JPG","heart14.JPG",
			"heart3.JPG","heart2.JPG",
			"heart2.JPG","heart3.JPG",
			"heart12.JPG","heart4.JPG",
			"heart13.JPG","heart5.JPG",
			"heart14.JPG","heart6.JPG",
			"spade11.JPG","spade11.JPG",
			"spade12.JPG","spade12.JPG",
			"spade13.JPG","spade13.JPG",
			"spade14.JPG","spade14.JPG",
			"spade2.JPG","spade2.JPG",
			"spade3.JPG","spade3.JPG",
			"spade4.JPG","spade4.JPG",
			"spade5.JPG","spade5.JPG",
			"spade6.JPG","spade6.JPG",
		]

		//게임 변수 설정
		let loginState = null;
		let total = 0; //전체 클릭횟수
		let state = 0; //0 or 1 or 2),(클릭안했을때,1개 선택, 2개 선택
		let success = 0; //몇개의 그림을 맞추었는가.
		let onepic =null; 
		let twopic =null;
		let fail=0;
		let combo=0;
		let bestCombo=0;
		let timerNum = 0;
		let score = 10;//1개 맞출때마다 추가점수
		let total_score = 0;//전체 점수
		let hintList = new Array();
		let notHintList = new Array();
		
////////////function
		//사라지게 하는 function
		let invisible = function(){
									$(".pic").each(function(){//점점 투명하게
										$(this).animate({opacity:0.01},10000);//5초 후에 
									});
								}

		//shffle 기능
		let shuffle = function(){
			for(let i=0;i<100000;i++){
				let ran = Math.floor(Math.random()*36);
				let tmp = model[0];
				model[0] = model[ran];
				model[ran] = tmp
				}
		}
		
		//그림을 보여주는 기능
		let show =function(){
			$(".board").append("<tr>");
			$(model).each(function(index,item){//jquery.model을 반복 돌림
			//oncontextmenu="return false":우클릭방지
			//ondragstart="return false":드래그 방지
	
				if(index%6==0){
					$(".board").append("</tr>");		
					$(".board").append("<tr>");
					$(".board").append("<div class='card'>");
					$(".board").append("<td oncontentmenu='return false' ondragstart='return false'><input class='pic' type= 'image' src='/jquery-ex/images/"+item+"'>"+index+"</td>");
					
					$(".board").append("</div>");	
				}else{
					$(".board").append("<div class='card'>");
					$(".board").append("<td oncontentmenu='return false' ondragstart='return false'><input class='pic' type= 'image' src='/jquery-ex/images/"+item+"'>"+index+"</td>");
					$(".board").append("</div>");
				}
			});///////////show function

			$(".board").append("</tr>");
			
			
		}
		
				//servlet으로 sessionInfo값을 ajax 동기요청
		$.ajax({
			url:"/jquery-ex/GetSessionInfo", 
			method:"post",
			  async: false,//동기 요청
			  success: function(json){
				  loginState = json;
				  $("#loginState").text(loginState);
			 		 }
	  			});
				//session 에 값이 없을 경우 로그인 페이지로 	
			if(loginState == null){
				location.href = "/jquery-ex/login.html";
				return false;//function 종료 
				}
	////////////////////////////////////	
		//logout 만들기
		$("#logout").click(function(){
			$.ajax({
				url:"/jquery-ex/Logout",
				method:"post",
				success:function(){
					location.href = "/jquery-ex/index.html";
						return false;//function 종료 
				}
			});
		});
	////////////////////////////////////	
		//상단에 로그인 표시
		$("#loginState").text(loginState);
		
		
	
			
		/* 
		//model 셔플,순서섞기
		shuffle();		
		//show,그림 보여주기
		show();
		//그림 안보이게 하기
		invisible();
		*/
		
		$("#timeMode").click(function(){
			$("#board").empty();
			
			
			let timer = setInterval(function(){
				timerNum--;
				console.log("time"+timerNum);
				$("#timer").text(timerNum);
					if(success == model.length/2 || timerNum<1){
						timerNum == 0; 
						clearInterval(timer);
						clearInterval(timerNum);
					}
				},1000);
			
			$.ajax({
				url:"/jquery-ex/BestTime",
				method:"post",
				success:function(json){
					timerNum = json;
					}
				});
			
			//model 셔플,순서섞기
			shuffle();		
			//show,그림 보여주기
			backshow();
			show();
			//그림 안보이게 하기
			invisible();
				$("#master").click(function(){
					show()
				});
				
					$(".pic").click(function(){
						
						total++; //전체 클릭수
						state++; //첫번째 클릭인지 ,두번째 클릭인지 파악
						$(this).attr("disabled",true);//더블클릭 방지
						
						//console.log($(this).attr("src"));
						
						//첫번째 클릭일때
						if(1==state){
							$(this).animate({opacity:1},1);
							onepic = $(this);
							console.log("state: 1");
						}else if(2==state){
							console.log("state: 2");
							twopic = $(this);
							$(this).animate({opacity:1},1);
							if($(onepic).attr("src")==$(twopic).attr("src")){
								//선택 성공시
								$(this).animate({opacity:1},1);
								success++;
								console.log("success : "+success);
								 hintList = $(this);
								
								//힌트 버튼 출현
								if(success/2==1 && success!=0){
									$("#hint").show();
									//Hint
									$("#hint").click(function(){
										$(".board").empty();
										show().delay(function(){
											$(hintList).each(function(){//점점 투명하게
												$(this).animate({opacity:0.01});//5초 후에 
											});;
										},3000);		
										
									});
								}else{
									$("#hint").hide();
								}
								
								//combo 조건
								combo++;
								$("#combo").text(combo);
								if(bestCombo<combo){
									bestCombo++;
									
								}
								$("#bestCombo").text(bestCombo);
								
								
								if(success == model.length/2 && timeNum==0){
									alert("게임종료 시간("+timerNum+"), 횟수("+total+")");	
									//$(".board").append("<td oncontentmenu='return false' ondragstart='return false'><input class='pic' type= 'image' src='/jquery-ex/images/"+item+"'>"+index+"</td>");
									
									$.ajax({ 
										url:"/jquery-ex/Addreport",
										method:"post",
										data:{
											"timer":timerNum, 
											"count":total, 
											"memberId":loginState,
											"bestCombo":bestCombo
											},
										success:function(){
											clearInterval(timer);
											console.log("Addreport success");
											}
										});	
								}//다
							}///성공할때
							else{//선택 실패시
								fail++;
								combo=0;
								$("#combo").text(combo);
								console.log("fail : "+fail);
										if(success>15 || fail>2){//마지막 3개에서 틀리거나 또는 10번 이상 틀릴경우 초기화
											success=0;
											fail=0;
											$(".board").empty();
											show().delay(3000).invisible();
											//$(this).delay(3000).shuffle();
											//$(this)
											$(".pic").attr("disabled",false);
										}//if 실패조건
								$(onepic).animate({opacity:0.01},1000);
								$(twopic).animate({opacity:0.01},1000);
								$(onepic).attr("disabled",false);
								$(twopic).attr("disabled",false);
								}///else
							state=0;
							}
					
						});/////click
		})
		////////////////////////////////////////////////////////////////////////////////
		//game Start 버튼 클릭시 짝 맞추기 게임 시작
		$("#mode").click(function(){
			$("#board").empty();
			//model 셔플,순서섞기
			shuffle();		
			//show,그림 보여주기
			show();
			//그림 안보이게 하기
			invisible();
			setInterval(function time(){
				timerNum++;
				$("#timer").text(timerNum);
					if(success == model.length/18){
					return false;
					}
				},1000);

			//click할 경우 function
		$(".pic").click(function(){
			
			total++; //전체 클릭수
			state++; //첫번째 클릭인지 ,두번째 클릭인지 파악
			$(this).attr("disabled",true);//더블클릭 방지
			
			//console.log($(this).attr("src"));
			
			//첫번째 클릭일때
			if(1==state){
				$(this).animate({opacity:1},1);
				onepic = $(this);
				console.log("state: 1");
			}else if(2==state){
				console.log("state: 2");
				twopic = $(this);
				$(this).animate({opacity:1},1);
				if($(onepic).attr("src")==$(twopic).attr("src")){
					//선택 성공시
					success++;//성공 수 증가
					console.log("success : "+success);
					
					 hintList.push($(onepic));
					 hintList.push($(twopic));
					 console.log(hintList);
					
					 $(notHintList).putsh$(".pic").not(hintList);
					 console.log(notHintList);
					//힌트 버튼 출현
					if(success/2==1 && success!=0){
						$("#hint").show();
						//Hint
						$("#hint").click(function(){
							$(hintList).each(function(){
								$(this).animate({opacity:1}).delay(3000).animate({opacity:0.01});
							});
						});
					}else{
						$("#hint").hide();
					}
					
					combo++;//combo 수 증가
					$("#combo").text(combo);
					if(bestCombo<combo){//BestCombo 값 설정 
						bestCombo++;
					}
					
					//점수 설정 + combo에 따라 점수에 곱하기
					if(combo>0){
						score = score*combo;
					}
					total_score = total_score + score;
					$("#score").text(total_score);
					
					$("#bestCombo").text(bestCombo);
					$(this).animate({opacity:1},1);//사진 보여주기
					
					
					if(success == model.length/2){
						alert("게임종료 시간("+timerNum+"), 횟수("+total+")");	
						//$(".board").append("<td oncontentmenu='return false' ondragstart='return false'><input class='pic' type= 'image' src='/jquery-ex/images/"+item+"'>"+index+"</td>");
						
						$.ajax({ 
							url:"/jquery-ex/Addreport",
							method:"post",
							data:{
								"timer":timerNum, 
								"count":total, 
								"memberId":loginState,
								"bestCombo":bestCombo
								},
							success:function(){
								clearInterval(timer);
								console.log("Addreport success");
								}
							});	
					}//다
				}///성공할때
				else{//선택 실패시
					fail++;
					combo=0;
					$("#combo").text(combo);
					console.log("fail : "+fail);
							if(success>15 || fail>2){//마지막 3개에서 틀리거나 또는 10번 이상 틀릴경우 초기화
								success=0;
								fail=0;
								$(".board").empty();
								show().delay(3000).invisible();
								//$(this).delay(3000).shuffle();
								//$(this)
								$(".pic").attr("disabled",false);
							}//if 실패조건
					$(onepic).animate({opacity:0.01},1000);
					$(twopic).animate({opacity:0.01},1000);
					$(onepic).attr("disabled",false);
					$(twopic).attr("disabled",false);
					}///else
				state=0;
				}
		
			});/////click
		});
		
		
		
		
	});