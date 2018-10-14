var table = document.getElementById("MarketStats");

function SubmitSearchInput() {

    var socket = new SockJS('/gs-guide-websocket');

    stompClient = Stomp.over(socket);

    var symbol = document.getElementById("SearchInput").value;
    console.log(symbol);


    stompClient.connect({}, function (frame){
        stompClient.send("/app/graph", {}, symbol);
        stompClient.subscribe('/topic/mssgs', connection5);
        stompClient.subscribe("/user/queue/reply", connection4);
    });

    function connection5(message)
    {

        message=JSON.parse(message.body);
        var k=0;

        for(k=0;k<10;k++)
        {
            console.log(message[k].symbol)
            if(symbol.localeCompare((message[k].symbol)) ==0)
            {
                break;
            }
        }

        table.rows[2].cells[1].innerHTML=message[k].latestPrice ;

    }


    function connection4(message)
    {
        message=JSON.parse(message.body);

        var i=0;

        table.rows[0].cells[1].innerHTML=message[4].prevClose ;
        table.rows[1].cells[1].innerHTML=message[4].openPrice ;



        function formatDate(date) {
            var dd = date.getDate();
            var mm = date.getMonth() + 1;
            var yyyy = date.getFullYear();
            if (dd < 10) {
                dd = '0' + dd
            }
            if (mm < 10) {
                mm = '0' + mm
            }
            date = mm + '/' + dd + '/' + yyyy;
            return date;
        }


        function Last5Days() {
            var result = [];
            var flag = 0;
            for (var i = 0; i < 5; i++) {
                var d = new Date();
                d.setDate(d.getDate() - i - flag);
                if(d.getDay() == 0) {
                    d.setDate(d.getDate() - 1);
                    flag = 1;
                }
                if(d.getDay() == 6) {
                    d.setDate(d.getDate() - 1);
                    flag = 2;
                }
                result.push(formatDate(d))
            }
            console.log(result)
            return (result);
        }

        var arr = Last5Days();

        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            theme: "light2",
            title: {
                text: "Weekly Analysis"
            },
            axisY: {
                includeZero: false
            },
            data: [{
                type: "line",
                lineColor: "#50C878",
                dataPoints: [
                    {
                        x: new Date(arr[4]), y: message[0].prevClose
                    },
                    {
                        x: new Date(arr[3]), y: message[1].prevClose
                    },
                    {
                        x: new Date(arr[2]), y: message[2].prevClose
                    },
                    {
                        x: new Date(arr[1]), y: message[3].prevClose
                    },
                    {
                        x: new Date(arr[0]), y: message[4].prevClose
                    }
                ]
            }]
        });
        chart.render();



    }

}

window.onload = function () {


}



