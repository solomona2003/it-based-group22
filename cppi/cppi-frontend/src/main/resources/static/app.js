var currentT = 0;
var refreshIntervalId;

var latestConfigurationId = null;
var dataServiceAddress = "http://192.168.99.100:8082/frontend/";

function sendUserConfiguration() {
    var F_T = document.getElementById("floorValue").value,
        m = document.getElementById("multiplier").value,
        b = document.getElementById("maxRiskyFraction").value,
        W_0 = document.getElementById("initInvestmentValue").value,
        T = document.getElementById("timeHorizon").value;

    var path = dataServiceAddress + "configuration";
    currentT = T;
	axios.post(path, {
        f_T: F_T,
        m: m,
        b: b,
        w_0: W_0,
        t: T
    })
    .then(function(response) {
        console.log(response);
        //clear div first
        document.getElementById("history").innerHTML = "";
        //start from reverse to print latest simulations first
        for(var i = response.data.history.length-1; i >= 0; i--) {
            latestConfigurationId = response.data.latestConfigurationId;
            printHistory(response.data.history[i]);
        }
        refreshIntervalId = window.setInterval(requestLatestUpdate, 1000);
    });
}

function requestHistoryUpdate() {
    var path = dataServiceAddress + "history";
    axios.get(path)
    .then(function(response) {
        //clear div first
        document.getElementById("history").innerHTML = "";
        //start from reverse to print latest simulations first
        for(var i = response.data.history.length-1; i >= 0; i--) {
            printHistory(response.data.history[i]);
        }
    });
}

function requestLatestUpdate() {
    var path = dataServiceAddress + "latestsimulation?configurationId=" + latestConfigurationId;
    axios.get(path)
    .then(function(response) {
        console.log(response.data);
        var latestResultTbody = document.getElementById("latestResult");
        latestResultTbody.innerHTML =  "";
        printResultsTable(latestResultTbody, response.data);
        if(response.data.simulationPeriodValues !== null &&
                response.data.simulationPeriodValues.length > currentT) {
                    clearInterval(refreshIntervalId);
        }
    });
}

function printHistory(data) {
    var historyDiv= document.getElementById("history");

    var titleH3 = document.createElement("h3");
    titleH3.innerHTML = "Run Id: " + data.id + " (Executed on: " + data.timeStamp + ")";
    historyDiv.appendChild(titleH3);

    var titleParameters = document.createElement("h4");
    titleParameters.innerHTML = "Parameters";
    historyDiv.appendChild(titleParameters);

    var userConfigurationP = document.createElement("p");
    userConfigurationP.innerHTML = "User Configuration: F_T=" + data.f_T +
            " , m=" + data.m + " , b=" + data.b + " , W_0=" + data.w_0 +
            " , T=" + data.t;
    historyDiv.appendChild(userConfigurationP);

    var planRulesP = document.createElement("p");
    planRulesP.innerHTML = "Plan Rules: R_t=" + data.r_t + " , d=" + data.d +
            " , T_0,T=" + data.t_0T;
    historyDiv.appendChild(planRulesP);

    var titleResults = document.createElement("h4");
    titleResults.innerHTML = "Results";
    historyDiv.appendChild(titleResults);

    var tableTemplate = document.getElementById("tableTemplate");
    var resultsTable = tableTemplate.cloneNode(true);
    resultsTable.removeAttribute("style"); //remove hidden tag
    historyDiv.appendChild(resultsTable);
    var tbody = resultsTable.getElementsByTagName('tbody')[0];
    tbody.innerHTML = "";
    printResultsTable(tbody, data);
}

function printResultsTable(tbody, data) {
    if(data.simulationPeriodValues===null) {
        return;
    }
    for(var i=0; i < data.simulationPeriodValues.length; i++) {
        var tr = document.createElement("tr");
        tbody.appendChild(tr);

        var tTd = document.createElement("td");
        tTd.innerHTML = round(data.simulationPeriodValues[i].t);
        tr.appendChild(tTd);

        var T_tT = document.createElement("td");
        T_tT.innerHTML = round(data.simulationPeriodValues[i].t_tT);
        tr.appendChild(T_tT);

        var f_t = document.createElement("td");
        f_t.innerHTML = round(data.simulationPeriodValues[i].f_t);
        tr.appendChild(f_t);

        var c_t = document.createElement("td");
        c_t.innerHTML = round(data.simulationPeriodValues[i].c_t);
        tr.appendChild(c_t);

        var x_rt = document.createElement("td");
        x_rt.innerHTML = round(data.simulationPeriodValues[i].x_rt);
        tr.appendChild(x_rt);

        var x_ft = document.createElement("td");
        x_ft.innerHTML = round(data.simulationPeriodValues[i].x_ft);
        tr.appendChild(x_ft);

        var s_t = document.createElement("td");
        s_t.innerHTML = round(data.simulationPeriodValues[i].s_t);
        tr.appendChild(s_t);

        var tSR_t = document.createElement("td");
        tSR_t.innerHTML = round(data.simulationPeriodValues[i].tsr_t);
        tr.appendChild(tSR_t);

        var w_t = document.createElement("td");
        w_t.innerHTML = round(data.simulationPeriodValues[i].w_t);
        tr.appendChild(w_t);
    }
}

function round(value) {
    return Math.round(value * 100) / 100;
}
