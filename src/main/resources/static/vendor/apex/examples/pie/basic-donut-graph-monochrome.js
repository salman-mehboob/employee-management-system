var options = {
	chart: {
		width: 400,
		type: 'donut',
	},
	series: [75, 25],
	labels: ["Subscriptions", "UnSubscriptions"],
	theme: {
		monochrome: {
			enabled: true,
			color: '#1a8e5f',
		}
	},
	/*title: {
		text: "Weekly Sales",
	},*/
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
}
var chart = new ApexCharts(
	document.querySelector("#basic-donut-graph-monochrome"),
	options
);
chart.render();


