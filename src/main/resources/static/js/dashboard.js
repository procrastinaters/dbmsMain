var table = document.getElementById("Top10TableContentTable");
flag=0;
var socket = new SockJS('/gs-guide-websocket');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // console.log('Connected: ' + frame);
        stompClient.send("/app/hello", {}, JSON.stringify({'name': "connect"}));
        stompClient.subscribe('/topic/mssgs', connection1);
        stompClient.subscribe('/user/queue/reply', connection2);
    });


    function connection1(message) {
           message=JSON.parse(message.body);
            // if(!flag)
            for(i=0;i<10;i++)
            {

                console.log("CLOSE"+message[i].close);
                table.rows[i].cells[0].innerHTML=message[i].symbol;
                table.rows[i].cells[1].innerHTML=message[i].latestPrice;
                table.color="red";
                table.rows[i].cells[2].innerHTML=message[i].open;
                table.rows[i].cells[3].innerHTML=message[i].close;
                table.rows[i].cells[4].innerHTML=message[i].previousClose;
                table.rows[i].cells[5].innerHTML=message[i].changePercent;
                // cell0.innerHTML=message[i].symbol;
                // cell3.innerHTML=message[i].latestPrice;
                // cell4.innerHTML=message[i].close;
            }
            // else{
            // table.rows[1].cells[3].innerHTML=message[0].open;
            // }$(window, document, undefined).ready(function() {
    }

function connection2(message) {

    message=JSON.parse(message.body);
    if(message==false)
    {
        // mssg= document.getElementById("mssg");
        // mssg.style.color="red";
        // mssg.innerHTML="Invalid Username or password";
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
}

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
var bttn=(document.getElementById("signin"));

bttn.addEventListener("click",function () {
    // var form=document.getElementsByTagName("form");
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    localStorage.setItem("user",username.value);

    console.log("CLICK");
    stompClient.send("/app/login", {}, JSON.stringify({'username': username.value,'balance':1000,'password':password.value,'email':"aniket.mp"}));

});
