var options = {
    chart: {
        height: 280,
        type: 'line',
        zoom: {
            enabled: false
        },
        toolbar: {
            show: false,
        },
    },
    series: [
        {
            name: 'Assigned',
            type: 'column',
            data: [440, 505, 414, 571, 227, 413, 201, 352, 652, 320, 257, 160]
        },
        {
            name: 'Pending',
            type: 'column',
            data: [200, 300, 259, 327, 129, 413, 201, 352, 652, 320, 129, 150]
        },
        {
            name: 'Complete',
            type: 'column',
            data: [190, 500, 159, 347, 159, 463, 271, 382, 692, 109, 117, 120]
        },
    ],
    stroke: {
        width: [0, 4, 4]
    },
    labels: ['01 Jan 2001', '02 Jan 2001', '03 Jan 2001', '04 Jan 2001', '05 Jan 2001', '06 Jan 2001', '07 Jan 2001', '08 Jan 2001', '09 Jan 2001', '10 Jan 2001', '11 Jan 2001', '12 Jan 2001'],
    xaxis: {
        type: 'datetime'
    },
    colors: ['#1a8e5f', '#c1920c', '#cc2626'],
}
var chart = new ApexCharts(
    document.querySelector("#tasksAss"),
    options
);
chart.render();
