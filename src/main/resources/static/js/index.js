// var socket=io();

// socket.on('connect',function(){
//     console.log("Connected to server");
// });

// socket.on('disconnect',function(){
// console.log("Disconnected");
// });

function connect() {
    var socket = new SockJS('/gs-guide-websocket-1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/client', function (message) {

            message=JSON.parse(message.body);
            if(message==false)
            {
                 mssg= document.getElementById("mssg");
                mssg.style.color="red";
                mssg.innerHTML="Invalid Username or password";
            }
            else{
                 mssg= document.getElementById("mssg")
                mssg.style.color="green";
                mssg.innerHTML="Login Successful!";
                document.location.href="/try.html";
            }
            console.log("message111"+message);
            console.log("message"+(JSON.parse(message.body)));

            console.log(JSON.parse(message.body).content);
        });
    });
}
connect();
var bttn=(document.getElementById("signup"));

bttn.addEventListener("click",function () {
    // var form=document.getElementsByTagName("form");
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    stompClient.send("/app/login", {}, JSON.stringify({'username': username.value,'balance':1000,'password':password.value,'email':"aniket.mp"}));

});
