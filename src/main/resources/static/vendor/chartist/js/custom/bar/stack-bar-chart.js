new Chartist.Bar('.stacked-bar', {
	labels: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
	series: [
		[
			{meta: 'Registered', value: 55},
			{meta: 'Registered', value: 83},
			{meta: 'Registered', value: 72},
			{meta: 'Registered', value: 68},
			{meta: 'Registered', value: 57},
			{meta: 'Registered', value: 41},
			{meta: 'Registered', value: 30}
		],
		[
			{meta: 'Resolved', value: 35},
			{meta: 'Resolved', value: 52},
			{meta: 'Resolved', value: 37},
			{meta: 'Resolved', value: 45},
			{meta: 'Resolved', value: 35},
			{meta: 'Resolved', value: 27},
			{meta: 'Resolved', value: 19}
		],
		[
			{meta: 'Open', value: 12},
			{meta: 'Open', value: 25},
			{meta: 'Open', value: 22},
			{meta: 'Open', value: 30},
			{meta: 'Open', value: 43},
			{meta: 'Open', value: 39},
			{meta: 'Open', value: 24}
		],
        [
			{meta: 'Closed', value: 12},
			{meta: 'Closed', value: 25},
			{meta: 'Closed', value: 22},
			{meta: 'Closed', value: 30},
			{meta: 'Closed', value: 43},
			{meta: 'Closed', value: 39},
			{meta: 'Closed', value: 24}
		],
	],
}, {
	stackBars: true,
	seriesBarDistance: 4,
	height: "176px",
	chartPadding: {
		left: 10,
		top: 0,
		bottom: 0,
	},
	axisX: {
		offset: 20,
	}, 
	axisY: {
		showLabel: true,
		showGrid: false,
		offset: 30,
	},
	plugins: [
		Chartist.plugins.tooltip()
	], 
}).on('draw', function(data) {
	if(data.type === 'bar') {
		data.element.attr({
			style: 'stroke-width: 32px'
		});
	}
});