var options = {
	chart: {
		height: 280,
		type: 'bar',
	},
	plotOptions: {
		bar: {
			horizontal: false,
			endingShape: 'rounded',
			columnWidth: '35%',
		},
	},
	dataLabels: {
		enabled: false
	},
	stroke: {
		show: true,
		width: 0,
		colors: ['transparent']
	},
	series: [{
		name: 'Registered',
		data: [44, 55, 57, 56, 61, 58, 63, 60, 66]
	}, {
		name: 'Resolved',
		data: [76, 85, 101, 98, 87, 105, 91, 114, 94]
	}, {
		name: 'Open',
		data: [35, 41, 36, 26, 45, 48, 52, 53, 41]
	},{
		name: 'Closed',
		data: [35, 41, 36, 26, 45, 48, 52, 53, 41]
	}],
	xaxis: {
		categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
	},
	yaxis: {
		title: {
			/*text: '$ (thousands)'*/
		}
	},
	fill: {
		opacity: 1
	},
	tooltip: {
		y: {
			formatter: function(val) {
				return "$ " + val + " thousands"
			}
		}
	},
	grid: {
		row: {
			colors: ['#f5f9fe', '#ffffff'], // takes an array which will be repeated on columns
			opacity: 0.5
		},
	},
	colors: ['#1a8e5f', '#c1920c', '#cc2626', '#63686f', '#868a90'],
}
var chart = new ApexCharts(
	document.querySelector("#basic-column-graph"),
	options
);
chart.render();
