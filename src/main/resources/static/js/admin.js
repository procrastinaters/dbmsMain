function connect() {
    var socket = new SockJS('/gs-guide-websocket-1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.send("/app/Admin", {}, JSON.stringify({'username': localStorage.getItem("user")}));
        stompClient.subscribe('/queue/reply/admin', function (message) {
            message = JSON.parse(message.body);
            var table=document.getElementById("bought_table")
            for(i=0;i<message.length;i++) {
                var row = table.insertRow(i + 1);
                row.insertCell(0);
                row.insertCell(1);
                row.insertCell(2);
                row.insertCell(3);
                row.insertCell(4);

                table.rows[i+1].cells[0].innerHTML=message[i].username;
                table.rows[i+1].cells[1].innerHTML=message[i].boughtNo;
                table.rows[i+1].cells[2].innerHTML=message[i].soldNo;
                table.rows[i+1].cells[3].innerHTML=message[i].balance;
                table.rows[i+1].cells[4].innerHTML="<b  class=\"w3-button \" style='background-color: #50C878;color:white;border-radius: 5px;box-shadow: 2px 3px 2px #111;'>SEND</b>";
            }
        });
    });
}

connect();
