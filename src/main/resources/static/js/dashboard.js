
$('#tab1').on('click' , function(){
    $('#tab1').addClass('login-shadow');
    $('#tab2').removeClass('signup-shadow');
});

$('#tab2').on('click' , function() {
    $('#tab2').addClass('signup-shadow');
    $('#tab1').removeClass('login-shadow');
});
var table = document.getElementById("Top10TableContentTable");
var ticker_table = document.getElementById("TickersScrollable");

var flag1=0,flag=0;
var prev=[];

var selectedRow=0;

if(localStorage.getItem("user")==null){

    var links = document.getElementsByTagName("a");
    for (var i = 0; i < 7; i++) {
        var href = links[i].getAttribute("rel");
        links[i].removeAttribute("rel");
        links[i].href = "/"
    }
}

document.getElementById("purchase").addEventListener("click",function (ev) {

    stompClient.send("/app/buy", {}, JSON.stringify({"username":localStorage.getItem("user"),
        "stock":document.getElementById("buystockid").value,
        "quantity":document.getElementById("buyquantity").value,
        "pricePerShare":document.getElementById("currvalue").value,
        "price":document.getElementById("buytotal").value}));
});
$(window, document, undefined).ready(function() {

    $('.input').blur(function() {
        var $this = $(this);
        if ($this.val())
            $this.addClass('used');
        else
            $this.removeClass('used');
    });

});


var bttn=(document.getElementById("signin"));

bttn.addEventListener("click",function () {
    var username=document.getElementById("username");
    var password=document.getElementById("password");

    console.log("CLICK");
    stompClient.send("/app/login", {}, JSON.stringify({'username': username.value,'balance':1000,'password':password.value}));

});


var bttn2=document.getElementById("confirmsignup");

bttn2.addEventListener("click",function () {
    var password=document.getElementById("setpassword");
    var repassword=document.getElementById("repassword");
    var email=document.getElementById("email");

    if( validateEmail(email.value))
    {
    if(password.value==repassword.value)
    stompClient.send("/app/signin", {}, JSON.stringify({'username': email.value,'password':password.value,'balance':0}));
    else
    {
        console.log("invalid password");
    }
    }
    else{
        console.log("invalid email");
    }
});

document.getElementById("refresh").addEventListener("click",function (ev) {

   document.location.href="/";
});

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
var signbtn=document.getElementById("sign");
if(localStorage.getItem("user")==null){
    signbtn.innerHTML="Sign In";
}
else{
    signbtn.innerHTML="Sign Out";
    signbtn.addEventListener("click",function (ev) {

        localStorage.clear();
        document.location.href="/";

    });
}

var socket = new SockJS('/gs-guide-websocket');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/mssgs', connection1);
        stompClient.subscribe('/user/queue/reply', connection2);
        stompClient.subscribe('/user/queue/register', connection3);
        stompClient.subscribe('/user/queue/buy', connection4);
        stompClient.send("/app/initial", {}, JSON.stringify({'name': "connect"}));

    });


function connection1(message) {
    message=JSON.parse(message.body);
    for(i=0;i<10;i++)
    {

        if(flag1==0){
            table.rows[i+1].cells[2].innerHTML=message[i].open;
            // table.rows[i+1].cells[3].innerHTML=message[i].close;
            prev[i]=table.rows[i+1].cells[1].innerHTML=message[i].previousClose;
        }

        table.rows[i+1].cells[0].innerHTML=message[i].symbol;
        table.rows[i+1].cells[3].innerHTML=message[i].latestPrice.toFixed(2);
        table.color="red";
        table.rows[i+1].cells[4].innerHTML=(message[i].latestPrice-prev[i]).toFixed(2);
        table.rows[i+1].cells[5].innerHTML="<b  class=\"w3-button \" style='background-color: #50C878;color:white;border-radius: 5px;box-shadow: 2px 3px 2px #111;'>BUY</b>";

        if(localStorage.getItem("user")!=null)
            table.rows[i+1].cells[5].addEventListener("click",function (e) {

                document.getElementById('id01').style.display='block';
                selectedRow=e.target.closest('tr').rowIndex;
                document.getElementById("buystockid").value=table.rows[selectedRow].cells[0].innerHTML;
                document.getElementById("buyaccount").value=localStorage.getItem("user");
                document.getElementById('currvalue').value=table.rows[selectedRow].cells[2].innerHTML;
                document.getElementById("buyquantity").value=1;
                document.getElementById("buytotal").value=document.getElementById("buyquantity").value*document.getElementById('currvalue').value;
            });
        document.getElementById("buytotal").value=document.getElementById("buyquantity").value*document.getElementById('currvalue').value;
        document.getElementById('currvalue').value=table.rows[selectedRow].cells[2].innerHTML;
        if(localStorage.getItem("user")==null)
            table.rows[i+1].cells[5].innerHTML="BUY";
    }
    flag1=1;

    function change(open,prev) {
        var diff = open - prev;
        return diff;
    }


    var k=0;
    for(j=0;j<3;j++)
    {
        ticker_table.rows[j].cells[0].innerHTML=message[k%10].symbol + "\n" +  "\n" + message[(k++)%10].open ;
        ticker_table.rows[j].cells[1].innerHTML=message[k%10].symbol + "\n" +  "\n" + message[(k++)%10].open;
        ticker_table.rows[j].cells[2].innerHTML=message[k%10].symbol + "\n" +  "\n" + message[(k++)%10].open;
        ticker_table.rows[j].cells[3].innerHTML=message[k%10].symbol + "\n" +  "\n" + message[(k++)%10].open;
        ticker_table.rows[j].cells[4].innerHTML=message[k%10].symbol + "\n" +  "\n" + message[(k++)%10].open;
    }}

function connection2(message) {

    message=JSON.parse(message.body);
    if(message==true)
    {
        localStorage.setItem("user",username.value);
        document.location.href="/";
    }
    else{
    }


}

function connection3(message) {

    message=JSON.parse(message.body);
    if(message==false)
    {

    }
    else{
        document.location.href="/";
    }

}
function connection4(message) {

    message=JSON.parse(message.body);
    if(message==true)
    {
        document.getElementById('id01').style.display='none'

        document.getElementById('success').style.display='block'
        // document.location.href="/";

    }
    else{

        document.getElementById('id01').style.display='none'

        document.getElementById('failure').style.display='block'
    }


}
