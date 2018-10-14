// document.getElementsByClassName("tablink")[0].click();
var selectedRow=0;
var table= document.getElementById("bought_table");
document.getElementById("refresh").addEventListener("click",function (ev) {

    document.location.href="/Portfolio.html";
});
function openCity(evt, cityName) {
  var i, x, tablinks;
  x = document.getElementsByClassName("city");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
    tablinks[i].classList.remove("w3-light-grey");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.classList.add("w3-light-grey");
}

document.getElementById("sign").addEventListener("click",function (ev) {

    localStorage.clear();
    document.location.href="/";

});

function connect() {
    var socket = new SockJS('/gs-guide-websocket-1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.send("/app/boughtStocks", {}, JSON.stringify({'username':localStorage.getItem("user")}));

        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/reply', function (message) {
            message=JSON.parse(message.body);

            for(i=0;i<message.length;i++) {
                // if (message[i].quantity != 0) {
                    var row = table.insertRow(i + 1);
                    row.insertCell(0);
                    row.insertCell(1);
                    row.insertCell(2);
                    row.insertCell(3);
                    row.insertCell(4);
                    row.insertCell(5);
                    row.insertCell(6);
                    row.insertCell(7);
                    row.insertCell(8);
                    table.rows[i + 1].cells[0].innerHTML = message[i].stock;
                    table.rows[i + 1].cells[1].innerHTML = message[i].pricePerShare;
                    table.rows[i + 1].cells[2].innerHTML = message[i].quantity;
                    table.rows[i + 1].cells[4].innerHTML = message[i].pl;
                    table.rows[i + 1].cells[5].innerHTML = message[i].pl/100;
                    table.rows[i + 1].cells[8].innerHTML = "<b  class=\"w3-button \" style='background-color: #50C878;color:white;border-radius: 5px;box-shadow: 2px 3px 2px #111;' onclick=\"document.getElementById('id01').style.display='block'\">SELL</b>";
                    table.rows[i + 1].cells[8].addEventListener("click", function (e) {
                        // document.getElementById('id01').style.display='block';
                        // selectedRow=e.target.closest('tr').rowIndex;
                        selectedRow = e.target.closest('tr').rowIndex;
                        document.getElementById("sell_stockid").value = table.rows[selectedRow].cells[0].innerHTML;
                        document.getElementById("sell_account").value = localStorage.getItem("user");
                        document.getElementById("sell_value").value = table.rows[selectedRow].cells[3].innerHTML;
                        document.getElementById("sell_quantity").value = table.rows[selectedRow].cells[2].innerHTML;
                        document.getElementById("sell_total").value = table.rows[selectedRow].cells[2].innerHTML * table.rows[selectedRow].cells[3].innerHTML;
                        document.getElementById("sell_prevvalue").value = table.rows[selectedRow].cells[1].innerHTML;
                        document.getElementById("gain").value = table.rows[selectedRow].cells[3].innerHTML * table.rows[selectedRow].cells[2].innerHTML - table.rows[selectedRow].cells[1].innerHTML * table.rows[selectedRow].cells[2].innerHTML;
                        // document.getElementById("buyaccount").value=localStorage.getItem("user");
                        // document.getElementById('currvalue').value=table.rows[selectedRow].cells[2].innerHTML;
                        // document.getElementById("buyquantity").value=1;
                        // document.getElementById("buytotal").value=document.getElementById("buyquantity").value*document.getElementById('currvalue').value;
                    });
                }
            // }
        });
        stompClient.send("/app/initial", {}, JSON.stringify({'username':localStorage.getItem("user")}));
        stompClient.subscribe('/topic/mssgs', function (message) {
            message = JSON.parse(message.body);
            for(i=1;i<table.rows.length;i++){

                for(j=0;j<message.length;j++){
                    if(message[j].symbol==table.rows[i].cells[0].innerHTML)
                    {
                        document.getElementById("gain").value=(table.rows[selectedRow].cells[3].innerHTML*table.rows[selectedRow].cells[2].innerHTML-table.rows[selectedRow].cells[1].innerHTML*table.rows[selectedRow].cells[2].innerHTML).toFixed(2 );
                        document.getElementById("sell_value").value=table.rows[selectedRow].cells[3].innerHTML;
                        document.getElementById("sell_total").value=(document.getElementById("sell_quantity").value*table.rows[selectedRow].cells[3].innerHTML).toFixed(2);
                        table.rows[i].cells[3].innerHTML=message[j].open;
                        table.rows[i].cells[6].innerHTML=(message[j].open*table.rows[i].cells[2].innerHTML-table.rows[i].cells[1].innerHTML*table.rows[i].cells[2].innerHTML).toFixed(2);
                        table.rows[i].cells[7].innerHTML=(((message[j].open)-(table.rows[i].cells[1].innerHTML))/100).toFixed(2);
                }}

            }

        });
        var sell=document.getElementById("sell_submit");
        sell.addEventListener("click",function (ev) {
            stompClient.send("/app/sell", {}, JSON.stringify({'username':localStorage.getItem("user"),
                'symbol':document.getElementById("sell_stockid").value,
                'quantity':document.getElementById("sell_quantity").value,
                'date':Date.now(),
                'sellPrice':document.getElementById("sell_total").value
            }));
            stompClient.subscribe('/user/queue/sell', function (message) {
                message = JSON.parse(message.body);

                // if(message==true){
                //     document.location.href="/Portfolio.html";
                // }
                // else{
                //     console.log("NOOO");
                // }

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
            });


        })
    });
}



connect();