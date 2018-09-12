//
// var stompClient = null;
//
// function setConnected(connected) {
//     $("#connect").prop("disabled", connected);
//     $("#disconnect").prop("disabled", !connected);
//     if (connected) {
//         $("#conversation").show();
//     }
//     else {
//         $("#conversation").hide();
//     }
//     $("#greetings").html("");
// }
//
// function connect() {
//     var socket = new SockJS('/gs-guide-websocket');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         setConnected(true);
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('/mssgs', function (greeting) {
//             console.log(JSON.parse(greeting.body).content);
//         });
//     });
// }
//
// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     setConnected(false);
//     console.log("Disconnected");
// }
//
//
//
// $(function () {
//     $("form").on('submit', function (e) {
//         e.preventDefault();
//     });
//     $( "#connect" ).click(function() { connect(); });
//     $( "#disconnect" ).click(function() { disconnect(); });
// });
//
var table = document.getElementById("mytable");
 flag=0;
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // console.log('Connected: ' + frame);
        stompClient.send("/app/hello", {}, JSON.stringify({'name': "connect"}));
        stompClient.subscribe('/topic/mssgs', function (message) {
            message=JSON.parse(message.body);
            if(!flag)
            for(i=0;i<10;i++)
            {
                var row = table.insertRow(i+1);
                var cell0=row.insertCell(0);
                var cell1=row.insertCell(1);
                var cell2=row.insertCell(2);
                var cell3=row.insertCell(3);
                var cell4=row.insertCell(4);
                var cell5=row.insertCell(5);
                var cell6=row.insertCell(6);
                var cell7=row.insertCell(7);
                var cell8=row.insertCell(8);
                var cell9=row.insertCell(9);
                var cell10=row.insertCell(10);

                cell0.innerHTML=message[i].symbol;
                cell3.innerHTML=message[i].latestPrice;
                cell4.innerHTML=message[i].close;
            }
            else{
                table.rows[1].cells[3].innerHTML=message[0].open;
            }
            flag=1;
            console.clear();

            // console.log(JSON.parse(message.body).content);
        });
    });
}


connect();
// var socket=io();


// socket.on('tabledata',function(result){
//     console.log(result[0].name);


// });