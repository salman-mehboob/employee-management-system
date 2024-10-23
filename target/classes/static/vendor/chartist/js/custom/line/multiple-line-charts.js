// Line Chart Visitors
var chart = new Chartist.Line('.line-chart-visitors', {
	labels: [1, 2, 3, 4],
	series: [
		[
			{meta: 'Registered', value: 700 },
			{meta: 'Registered', value: 1500},
			{meta: 'Registered', value: 900},
			{meta: 'Registered', value: 1800}
		]
	]
}, {
	// Remove this configuration to see that chart rendered with cardinal spline interpolation
	// Sometimes, on large jumps in data values, it's better to use simple smoothing.
	lineSmooth: Chartist.Interpolation.simple({
		divisor: 2
	}),
	height: "65px",
	fullWidth: true,
	chartPadding: {
		right: 5,
		left: 5,
		top: 0,
		bottom: 0,
	},
	axisX: {
		offset: 0,
		showGrid: false,
		showLabel: false,
	}, 
	axisY: {
		offset: 0,
		showLabel: false,
		showGrid: false,
	},
	plugins: [
		Chartist.plugins.tooltip()
	],
	low: 0
});
chart.on('draw', function(data) {
	if(data.type === 'line' || data.type === 'area') {
		data.element.animate({
			d: {
				begin: 2000 * data.index,
				dur: 2000,
				from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
				to: data.path.clone().stringify(),
				easing: Chartist.Svg.Easing.easeOutQuint
			}
		});
	}
});




// Line chart Likes
var chart = new Chartist.Line('.line-chart-likes', {
	labels: [1, 2, 3, 4],
	series: [
		[
			{meta: 'Resolved', value: 900 },
			{meta: 'Resolved', value: 500},
			{meta: 'Resolved', value: 1400},
			{meta: 'Resolved', value: 800}
		]
	]
}, {
	// Remove this configuration to see that chart rendered with cardinal spline interpolation
	// Sometimes, on large jumps in data values, it's better to use simple smoothing.
	lineSmooth: Chartist.Interpolation.simple({
		divisor: 2
	}),
	height: "65px",
	fullWidth: true,
	chartPadding: {
		right: 5,
		left: 5,
		top: 0,
		bottom: 0,
	},
	axisX: {
		offset: 0,
		showGrid: false,
		showLabel: false,
	}, 
	axisY: {
		offset: 0,
		showLabel: false,
		showGrid: false,
	},
	plugins: [
		Chartist.plugins.tooltip()
	],
	low: 0
});
chart.on('draw', function(data) {
	if(data.type === 'line' || data.type === 'area') {
		data.element.animate({
			d: {
				begin: 2000 * data.index,
				dur: 2000,
				from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
				to: data.path.clone().stringify(),
				easing: Chartist.Svg.Easing.easeOutQuint
			}
		});
	}
});




// Line chart Orders
var chart = new Chartist.Line('.line-chart-orders', {
	labels: [1, 2, 3, 4],
	series: [
		[
			{meta: 'Open', value: 800 },
			{meta: 'Open', value: 500},
			{meta: 'Open', value: 1200},
			{meta: 'Open', value: 1000}
		]
	]
}, {
	// Remove this configuration to see that chart rendered with cardinal spline interpolation
	// Sometimes, on large jumps in data values, it's better to use simple smoothing.
	lineSmooth: Chartist.Interpolation.simple({
		divisor: 2
	}),
	height: "65px",
	fullWidth: true,
	chartPadding: {
		right: 5,
		left: 5,
		top: 0,
		bottom: 0,
	},
	axisX: {
		offset: 0,
		showGrid: false,
		showLabel: false,
	}, 
	axisY: {
		offset: 0,
		showLabel: false,
		showGrid: false,
	},
	plugins: [
		Chartist.plugins.tooltip()
	],
	low: 0
});
chart.on('draw', function(data) {
	if(data.type === 'line' || data.type === 'area') {
		data.element.animate({
			d: {
				begin: 2000 * data.index,
				dur: 2000,
				from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
				to: data.path.clone().stringify(),
				easing: Chartist.Svg.Easing.easeOutQuint
			}
		});
	}
});


// Line chart Reviews
var chart = new Chartist.Line('.line-chart-reviews', {
	labels: [1, 2, 3, 4],
	series: [
		[
			{meta: 'Closed', value: 300},
			{meta: 'Closed', value: 1600},
			{meta: 'Closed', value: 1400},
			{meta: 'Closed', value: 800}
		]
	]
}, {
	// Remove this configuration to see that chart rendered with cardinal spline interpolation
	// Sometimes, on large jumps in data values, it's better to use simple smoothing.
	lineSmooth: Chartist.Interpolation.simple({
		divisor: 2
	}),
	height: "65px",
	fullWidth: true,
	chartPadding: {
		right: 5,
		left: 5,
		top: 0,
		bottom: 0,
	},
	axisX: {
		offset: 0,
		showGrid: false,
		showLabel: false,
	}, 
	axisY: {
		offset: 0,
		showLabel: false,
		showGrid: false,
	},
	plugins: [
		Chartist.plugins.tooltip()
	],
	low: 0
});

chart.on('draw', function(data) {
	if(data.type === 'line' || data.type === 'area') {
		data.element.animate({
			d: {
				begin: 2000 * data.index,
				dur: 2000,
				from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
				to: data.path.clone().stringify(),
				easing: Chartist.Svg.Easing.easeOutQuint
			}
		});
	}
});