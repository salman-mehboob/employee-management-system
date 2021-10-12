var options = {
	chart: {
		height: 274,
		type: 'line',
		stacked: false,
		toolbar: {
			show: false,
		},
	},
	dataLabels: {
		enabled: false
	},
	series: [{
		name: 'Total Calls',
		type: 'column',
		data: [4, 2, 2, 5, 6, 8, 8, 7]
	},{
		name: 'Talk Time (Mins)',
		type: 'column',
		data: [2, 3, 1, 4, 5, 9, 5, 8]
	},{
		name: 'Missed Calls',
		type: 'line',
		data: [20, 10, 15, 36, 44, 45, 50, 58]
	},
    {
		name: 'ACD',
		type: 'line',
		data: [20, 12, 18, 37, 49, 45, 55, 67]
	}],
	stroke: {
		width: [1, 1, 3, 5]
	},
	/*title: {
		text: 'Overall income in millon dollors form online and offline sales from 2010 to 2018.',
		align: 'center',
	},*/
	colors: ['#2eb9ba', '#262b31', '#eb5768', '#63686f', '#868a90'],
	xaxis: {
		/*categories: [2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018],*/
		categories: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
	},
	yaxis: [{
		axisTicks: {
			show: true,
		},
		axisBorder: {
			show: true,
			color: '#1a8e5f'
		},
		labels: {
			style: {
				color: '#1a8e5f',
			}
		},
		title: {
			text: "Total Calls",
			style: {
				color: '#1a8e5f',
			}
		},
		tooltip: {
			enabled: true
		}
	},{
			seriesName: 'Orders',
			opposite: true,
			axisTicks: {
				show: true,
			},
			axisBorder: {
				show: true,
				color: '#262b31'
			},
			labels: {
				style: {
					color: '#262b31',
				}
			},
			title: {
				text: "Talk Time (Mins)",
				style: {
					color: '#262b31',
				}
			},
		},{
			seriesName: 'Revenue',
			opposite: true,
			axisTicks: {
				show: true,
			},
			axisBorder: {
				show: true,
				color: '#eb5768'
			},
			labels: {
				style: {
					color: '#eb5768',
				},
			},
			title: {
				text: "Missed Calls",
				style: {
					color: '#eb5768',
				}
			}
		},
            {
			seriesName: 'Revenue',
			opposite: true,
			axisTicks: {
				show: true,
			},
			axisBorder: {
				show: true,
				color: '#63686f'
			},
			labels: {
				style: {
					color: '#63686f',
				},
			},
			title: {
				text: "ACD",
				style: {
					color: '#63686f',
				}
			}
		},
	],
	legend: {
		horizontalAlign: 'center',
		offsetY: 10
	}
}

var chart = new ApexCharts(
	document.querySelector("#multiple-yaxis"),
	options
);
chart.render();