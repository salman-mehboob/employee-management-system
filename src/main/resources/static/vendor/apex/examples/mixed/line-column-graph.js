var options = {
	chart: {
		height: 300,
		type: 'line',
		zoom: {
			enabled: false
		},
	},
	toolbar: {
		show: false
	},
	series: [{
		name: 'Successful Calls',
		type: 'column',
		data: [440, 505, 414, 671, 227, 413, 201, 352, 752, 320, 257, 160]
	}, {
		name: 'Unsuccessful Calls',
		type: 'line',
		data: [23, 42, 35, 27, 43, 22, 17, 31, 22, 22, 12, 16]
	}],
	stroke: {
		width: [0, 4]
	},
	/*title: {
		text: 'Traffic Sources',
		align: 'center'
	},*/
    
    
	// labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
	labels: ['Jan 01', 'Feb 02','Mar 03','Apr 04','May 05','Jun 06','Jul 07','Aug 08','Sep 09 ','Oct 10','Nov 11','Dec 11'],
    
	xaxis: {
		type: 'datetime'
	},
	colors: ['#2eb9ba', '#eb5768', '#cc2626', '#cc2626', '#cc2626'],
	yaxis: [{
		title: {
			text: 'Successful Calls',
		},
	},{
		opposite: true,
		title: {
			text: 'Unsuccessful Calls'
		}
	}]
}
var chart = new ApexCharts(
	document.querySelector("#line-column-graph"),
	options
);
chart.render();