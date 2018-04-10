const app = angular.module('MyTravelApp', [])

app.controller('MainController', mainController);

mainController.$inject = ['$scope'];

function mainController($scope) {

    AmCharts.baseHref = true;

    const map = AmCharts.makeChart("chartdiv", {
        "type": "map",
        "theme": "light",
        "projection": "eckert3",
        "dataProvider": {
            "map": "worldHigh",
            "areas": [{
                "id": "RO",
                "showAsSelected": true
            }],
        },
        "areasSettings": {
            "autoZoom": true,
            "selectedColor": "#CC0000"
        },
        "listeners": [{
            "event": "descriptionClosed",
            "method": function (ev) {
                ev.chart.selectObject();
            }
        }]
    });

};