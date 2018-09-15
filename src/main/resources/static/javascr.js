$(window, document, undefined).ready(function() {

  $('.input').blur(function() {
    var $this = $(this);
    if ($this.val())
      $this.addClass('used');
    else
      $this.removeClass('used');
  });
  
  });


$('#tab1').on('click' , function(){
    $('#tab1').addClass('login-shadow');
   $('#tab2').removeClass('signup-shadow');
});

$('#tab2').on('click' , function() {
    $('#tab2').addClass('signup-shadow');
    $('#tab1').removeClass('login-shadow');
});

function connect() {
    var socket = new SockJS('/gs-guide-websocket-1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/reply', function (message) {

            message=JSON.parse(message.body);
            if(message==false)
            {
                mssg= document.getElementById("mssg");
                mssg.style.color="red";
                mssg.innerHTML="Invalid Username or password";
            }
            else{
                // mssg= document.getElementById("mssg")
                // mssg.style.color="green";
                // mssg.innerHTML="Login Successful!";
                document.location.href="/try.html";
            }
            console.log("message111"+message);
            console.log("message"+(JSON.parse(message.body)));

            console.log(JSON.parse(message.body).content);
        });
    });
}
connect();

var bttn=(document.getElementById("signin"));

bttn.addEventListener("click",function () {
    // var form=document.getElementsByTagName("form");
    var username=document.getElementById("username");
    var password=document.getElementById("password");

    stompClient.send("/app/login", {}, JSON.stringify({'username': username.value,'balance':1000,'password':password.value,'email':"aniket.mp"}));

});






