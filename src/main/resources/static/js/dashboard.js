var table = document.getElementById("Top10TableContentTable");
flag=0;
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // console.log('Connected: ' + frame);
        stompClient.send("/app/hello", {}, JSON.stringify({'name': "connect"}));
        stompClient.subscribe('/topic/mssgs', function (message) {
            message=JSON.parse(message.body);
            // if(!flag)
                for(i=0;i<10;i++)
                {

                        console.log("CLOSE"+message[i].close);
                    table.rows[i].cells[0].innerHTML=message[i].symbol;
                    table.rows[i].cells[1].innerHTML=message[i].latestPrice;
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
            // }
            flag=1;
            // console.clear();

            // console.log(JSON.parse(message.body).content);
        });
    });
}


connect();