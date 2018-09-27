var table = document.getElementById("Top10TableContentTable");
var ticker_table = document.getElementById("TickersScrollable");
flag=0;


var selectedRow=0;


var socket = new SockJS('/gs-guide-websocket');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/mssgs', connection1);
        stompClient.subscribe('/user/queue/reply', connection2);
        stompClient.subscribe('/user/queue/register', connection3);
        stompClient.send("/app/initial", {}, JSON.stringify({'name': "connect"}));

    });


    function connection1(message) {
           message=JSON.parse(message.body);
        var k=0;
           //Scrolling-tickers

        function change(open,prev) {
            var diff = open - prev;
            return diff;
        }

        // function color(diff) {
        //
        //     if(diff < 0)
        //         document.getElementsByClassName("TickerBlock").style.color = "#cc0000";
        //     else
        //         document.getElementsByClassName("TickerBlock").style.color = "#008000";
        //
        // }


        for(j=0;j<2;j++)
        {
            // color(change( message[k].open , message[k].previousClose ));
            ticker_table.rows[j].cells[0].innerHTML=message[k].symbol + "\n" +  "\n" + message[k++].open ;
            // color(change( message[k].open , message[k].previousClose ));
            ticker_table.rows[j].cells[1].innerHTML=message[k].symbol + "\n" +  "\n" + message[k++].open;
            // color(change( message[k].open , message[k].previousClose ));
            ticker_table.rows[j].cells[2].innerHTML=message[k].symbol + "\n" +  "\n" + message[k++].open;
            //color(change( message[k].open , message[k].previousClose ));
            ticker_table.rows[j].cells[3].innerHTML=message[k].symbol + "\n" +  "\n" + message[k++].open;
            // color(change( message[k].open , message[k].previousClose ));
            ticker_table.rows[j].cells[4].innerHTML=message[k].symbol + "\n" +  "\n" + message[k++].open;
        }



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
                table.rows[i].cells[6].innerHTML="<b  class=\"w3-button w3-black\">BUY</b>";
                if(localStorage.getItem("user")!=null)
                table.rows[i].cells[6].addEventListener("click",function (e) {
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
                    table.rows[i].cells[6].innerHTML="BUY";
            }
    }

function connection2(message) {

    message=JSON.parse(message.body);
    if(message==false)
    {


    }
    else{
        document.location.href="/try.html";
    }
    console.log("message111"+message);
    console.log("message"+(JSON.parse(message.body)));

    console.log(JSON.parse(message.body).content);
}

function connection3(message) {

    message=JSON.parse(message.body);
    if(message==false)
    {

    }
    else{
        document.location.href="/Dashboard.html";
    }

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
    var username=document.getElementById("username");
    var password=document.getElementById("password");
    localStorage.setItem("user",username.value);

    console.log("CLICK");
    stompClient.send("/app/login", {}, JSON.stringify({'username': username.value,'balance':1000,'password':password.value}));

});


var bttn2=document.getElementById("confirmsignup");

bttn2.addEventListener("click",function () {
    var name=document.getElementById("name");
    var password=document.getElementById("setpassword");
    var email=document.getElementById("email");
    stompClient.send("/app/signin", {}, JSON.stringify({'username': email.value,'password':password.value,'balance':0}));
});

    table.rows[1].cells[6].addEventListener("click",function () {

       var value=document.getElementById("currvalue");
       value.innerHTML="IT WORKS";
    });
document.getElementById("purchase").addEventListener("click",function (ev) {

    stompClient.send("/app/buy", {}, JSON.stringify({"username":localStorage.getItem("user"),
        "stock":document.getElementById("buystockid").value,
        "quantity":document.getElementById("buyquantity").value,
        "pricePerShare":document.getElementById("currvalue").value,
        "price":document.getElementById("buytotal").value}));
});