var options = {
	chart: {
		width: 400,
		type: 'pie',
	},
	labels: ['Subscriptions', 'UnSubscriptions'],
	series: [80, 20],
	responsive: [{
		breakpoint: 480,
		options: {
			chart: {
				width: 200
			},
			legend: {
				position: 'bottom'
			}
		}
	}],
	stroke: {
		width: 0,
	},
	colors: ['#2eb9ba', '#eb5768'],
}
var chart = new ApexCharts(
	document.querySelector("#basic-pie-graph"),
	options
);
chart.render();