

function connect() {
    var socket = new SockJS('/gs-guide-websocket-1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.send("/app/Profile", {}, JSON.stringify({'username':localStorage.getItem("user")}));
        stompClient.subscribe('/user/queue/reply/Profile', function (message) {
            message=JSON.parse(message.body);
            document.getElementById("first_name").value=message.name;
            // sex.value=message.sex;
            document.getElementById("dob").value=message.dob;
            document.getElementById("mobile").value=message.contactNo;
            document.getElementById("email").value=message.email;
            var select=document.getElementById("sex");
            if(message.sex=="M") {
                document.getElementById("sex").selectedIndex = "0";
            }else{
                    document.getElementById("sex").selectedIndex="1";
                }


        });
            stompClient.subscribe('/user/queue/reply', function (message) {

            message=JSON.parse(message.body);
            if(message==false)
            {

            }
            else{

            }
            console.log("message111"+message);
            console.log("message"+(JSON.parse(message.body)));

            console.log(JSON.parse(message.body).content);
        });
    });
}
connect();

document.getElementById("email").innerHTML=localStorage.getItem("user");

var bttn=(document.getElementById("save"));

bttn.addEventListener("click",function () {
    // var form=document.getElementsByTagName("form");
    var name=document.getElementById("first_name").value;
    var sex=document.getElementById("sex").options[document.getElementById("sex").selectedIndex].value;
    var dob=document.getElementById("dob").value;
    var mob=document.getElementById("mobile").value;

    if(sex=="Male")
        sex='M';
    else
        sex='F';
    stompClient.send("/app/completeProfile", {}, JSON.stringify({'email': localStorage.getItem("user"),'name':name,'sex':sex,'contactNo':mob,'dob':dob}));

});
