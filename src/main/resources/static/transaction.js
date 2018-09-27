var table=document.getElementById("bought_table")

function connect() {
    var socket = new SockJS('/gs-guide-websocket-1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.send("/app/transaction", {}, JSON.stringify({'username':localStorage.getItem("user")}));
        stompClient.subscribe('/user/queue/reply', function (message) {
            message=JSON.parse(message.body);

            for(i=0;i<message.length;i++) {
                var row = table.insertRow(i + 1);
                row.insertCell(0);
                row.insertCell(1);
                row.insertCell(2);
                row.insertCell(3);
                row.insertCell(4);
                row.insertCell(5);
                table.rows[i + 1].cells[0].innerHTML = message[i].transid;
                table.rows[i + 1].cells[2].innerHTML = message[i].symbol;
                table.rows[i + 1].cells[3].innerHTML = message[i].quantity;
                table.rows[i + 1].cells[4].innerHTML = message[i].price;
                table.rows[i + 1].cells[5].innerHTML = message[i].pl;
            }
                // console.log('Connected: ' + frame);
        });
    });
}

connect();