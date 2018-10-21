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
                table.rows[i + 1].cells[0].innerHTML = message[i].transid;
                table.rows[i + 1].cells[1].innerHTML = message[i].timestamp;
                table.rows[i + 1].cells[2].innerHTML = message[i].symbol;
                table.rows[i + 1].cells[3].innerHTML = message[i].quantity;
                table.rows[i + 1].cells[4].innerHTML = message[i].price.toFixed(2);

            }
                // console.log('Connected: ' + frame);

            // function Last5Days() {
            //     var result = [];
            //     var flag = 0;
            //     for (var i = 0; i < 5; i++) {
            //         var d = new Date();
            //         d.setDate(d.getDate() - i - flag);
            //
            //         result.push(formatDate(d))
            //     }
            //     console.log(result)
            //     return (result);
            // }
            //
            // var count = 0;
            // var array1 = Last5Days;
            // var array2 = [];
            //
            // for (var j=message.length-1 ;   ; j--)
            // {
            //
            // }


        });
    });
}
window.onload = function () {

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        backgroundColor: "rgba(0,0,0,0)",
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        title:{
            text: "Top Oil Reserves"
        },
        axisY: {
            title: "Reserves(MMbbl)"
        },
        data: [{
            type: "column",
            showInLegend: true,
            legendMarkerColor: "grey",
            legendText: "MMbbl = one million barrels",
            dataPoints: [
                { y: 300878, label: "Venezuela" },
                { y: 266455,  label: "Saudi" },
                { y: 169709,  label: "Canada" },
                { y: 158400,  label: "Iran" },
                { y: 142503,  label: "Iraq" },
                { y: 101500, label: "Kuwait" },
                { y: 97800,  label: "UAE" },
                { y: 80000,  label: "Russia" }
            ]
        }]
    });
    chart.render();

}
connect();