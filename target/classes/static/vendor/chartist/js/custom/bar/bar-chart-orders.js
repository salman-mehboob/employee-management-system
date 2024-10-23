new Chartist.Bar('.barChartOrders', {
	labels: ['Q1', 'Q2', 'Q3', 'Q4'],
	series: [
		[
			{meta: 'Total Calls', value: 2000},
			{meta: 'Total Calls', value: 4000},
			{meta: 'Total Calls', value: 6000},
			{meta: 'Total Calls', value: 8000},
		],
		[
			{meta: 'Successful Calls', value: 2500},
			{meta: 'Successful Calls', value: 4500},
			{meta: 'Successful Calls', value: 6500},
			{meta: 'Successful Calls', value: 8500},
		],
        [
			{meta: 'Unsuccessful Calls', value: 3000},
			{meta: 'Unsuccessful Calls', value: 5000},
			{meta: 'Unsuccessful Calls', value: 7000},
			{meta: 'Unsuccessful Calls', value: 9000},
		],
	],
}, {
	reverseData: true,
	seriesBarDistance: 15,
	height: "121px",
	chartPadding: {
		right: 0,
		left: 20,
		top: 0,
		bottom: 0,
	},
	axisY: {
		offset: 30
	},
	plugins: [
		Chartist.plugins.tooltip()
	],
});
