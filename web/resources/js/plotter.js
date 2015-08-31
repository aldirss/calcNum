/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var absc = [];
var fx = [];
var dfx = [];
var ub, lb;
var myLineChart;
var ctx;
var ub, lb;
var step;
var data;
var canvas;
var a = false;
var b = false;

function fun1(xx) {
    var f = document.getElementById("j_idt29:func");
    var expr;
    try {
        expr = Parser.parse(f.value);  //parse(f.value);
    }
    catch (err) {
        alert(err);
    }
    return  expr.evaluate({x: xx});
}
function fun2(xx) {
    var f = document.getElementById("j_idt29:diffFunc");
    var expr;
    try {
        expr = Parser.parse(f.value);  //parse(f.value);
    }
    catch (err) {
        alert(err);
    }
    return  expr.evaluate({x: xx});

}
function zoomUp() {
    a = false;
    b = false;
    
    step = step * 1.5;
    lb = 1.5 * lb;
    ub = 1.5 * ub;
    // alert(step+ "  "+lb+"  "+ub);
    draw();
}
function zoomDown() {
    a = false;
    b = false;
    
    step = step / 1.5;
    lb = lb / 1.5;
    ub = ub / 1.5;

    draw();
}
function clear2(){
    a = false;
    b = false;
    clear();
    document.getElementById("j_idt29:low").value=0;
    document.getElementById("j_idt29:high").value=0;
    
    
}
function limits(func) {
    lb = -4;
    ub = 4;
    var lg = "exp log lg log10".split(" ");
    for (var i = 0; i < lg.length; i++)
        if (func.search(lg[i]) > 0) {
            lb = 0;
            ub = 20;
            return;
        }
    // document.getElementById("demo").innerHTML = lb+"   "+ub;
    var trigo = "sin cos tan asin acos atan sinh cosh tanh asinh acosh         atanh".split(" ");
    for (var i = 0; i < trigo.length; i++)
        if (func.search(trigo[i]) > 0) {
            lb = -3.15;
            ub = 3.15;

        }

}
function clear() {
    try {
        canvas = document.getElementById("canvas");
        ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.fillStyle = "#ffffff";
        ctx.fillRect(0,0,canvas.width, canvas.height);
        ctx.beginPath();
        fx.length = 0;
        absc.length = 0;
        if (myLineChart !== null)
        myLineChart.destroy();
        
        canvas.addEventListener('click',function(evt){
             var rect = canvas.getBoundingClientRect();
              var x = evt.clientX - rect.left;
             
              x = x*(ub-lb)/(canvas.width)+lb;
            if (!a){
              document.getElementById("j_idt29:low").value=x;
              a = true;
            }else{
              if (document.getElementById("j_idt29:low")==="")
                  document.getElementById("j_idt29:low").value=x;
              document.getElementById("j_idt29:high").value=x;
              ;
          }
        },false); 
         
    } catch (err) {
       // alert(err);
    }

}
function buildFxAxes() {
    var x = lb;
    fx.length = 0;
    absc.length = 0;
    while (x <= ub) {
        fx.push(fun1(x));
        absc.push(x);
        x = x + step;
    }
}
function buildDFxAxes(low, high) {

    var x = -5 * (low + 1);
    dfx = [];
    absc = [];
    while (x <= 5 * (1 + high)) {
        dfx.push(fun1(x));
        absc.push(x);
        x = x + step;
    }
}
function draw() {
    clear();
    buildFxAxes();
    var options = {
        responsive: true
    };
    data = {
        labels: absc,
        datasets: [
            {
                label: "Dados secundários",
                fillColor: "rgba(151,187,205,0.2)",
                strokeColor: "rgba(151,187,205,1)",
                pointColor: "rgba(151,187,205,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: fx
            }
        ]};


    try {
        ctx = document.getElementById("canvas").getContext("2d");
        myLineChart = new Chart(ctx).Line(data, options);
        // ctx.addEventListener("custom", event, false); 
        ctx.onclick = function (evt) {
            try {
                var activePoints = myLineChart.getPointsAtEvent(evt);
                alert("activePoints");
                // => activePoints is an array of points on the canvas that are at the same position as the click event.
            }
            catch (err) {
                alert(err);
            }
        };
        

    }
    catch (err) {
        alert(err);
    }

}
function drawF() {
    a = false;
    
    limits(document.getElementById("j_idt29:func").value);
    step = 0.5;
    draw();
}

