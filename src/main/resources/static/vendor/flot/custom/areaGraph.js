'use strict'

var flotAreaData1 = [
	[0,19.57749563156254],
	[1,21.990117798360984],
	[2,16.33951429212372],
	[3,20.81299261981016],
	[4,18.43049180497279],
	[5,20.50742948537699],
	[6,22.15321230561721],
	[7,13.734038382708317],
	[8,12.48248771261796],
	[9,19.406002456692214],
	[10,21.59886277727973],
	[11,12.156859927914581],
	[12,9.229765251904174],
	[13,5.183401848384374],
	[14,9.605706708466142],
	[15,10.832074796645134],
	[16,13.268792742800557],
	[17,18.216203428328363],
	[18,13.963666987062208],
	[19,18.712081450016612],
	[20,13.72401606510321],
	[21,11.305095416130975],
	[22,13.773906992422056],
	[23,15.834031310396583],
	[24,12.926545228583812],
	[25,17.595569228566347],
	[26,21.90850212276817],
	[27,18.29990271583387],
	[28,14.340994854410802],
	[29,18.22389641710976],
	[30,14.883609800856053],
	[31,13.019139849150623],
	[32,14.553083951054631],
	[33,15.417025583778472],
	[34,16.640872368623782],
	[35,19.456813236353057],
	[36,14.595960349995135],
	[37,17.729784515799526],
	[38,13.86824197051369],
	[39,9.492952801660538],
	[40,11.912479814449945],
	[41,9.798782954230068],
	[42,6.117552232900492],
	[43,1.4130313413037507],
	[44,2.3640186232524685],
	[45,2.3620174492590778],
	[46,4.523611468529182],
	[47,3.7627065666017216],
	[48,5.7686167365911043],
	[49,5.08594242151745846],
	[50,1.905264426860338],
	[51,8.27642052341136036],
	[52,7.9183672429606382],
	[53,5.027341617316905],
	[54,2.8449308083068967],
	[55,6.827661569105635],
	[56,6.215632967625112],
	[57,9.831855054294463],
	[58,9.393752601741996],
	[59,11.962549642491954],
	[60,10.01016629019579],
	[61,9.03698508678906],
	[62,6.053332776990388],
	[63,4.56216961329746],
	[64,2.7601184969979364],
	[65,4.345620131013858],
	[66,3.6626759042117385],
	[67,4.27936456640813],
	[68,2.0166954203189142],
	[69,1.4881023513956224],
	[70,3.7196511214339196],
	[71,1.5333390837655454],
	[72,5.780072548768565],
	[73,4.904719814229008],
	[74,1.0799012554825165],
	[75,4.72338119051706],
	[76,6.314725021867233],
	[77,4.277597816664166],
	[78,5.1544567140954225],
	[79,5.239845249502064],
	[80,3.877879174711641],
	[81,8.225872226683242],
	[82,7.264487465012946],
	[83,6.504325850409032],
	[84,1.7088839316517497],
	[85,11.49433994707275364],
	[86,10.5002886069980867],
	[87,3.8186248032905223],
	[88,4.790166662214078],
	[89,8.584014466610698],
	[90,10.231484497623207],
	[91,11.085662593015236],
	[92,15.692957864072707],
	[93,19.729820239992353],
	[94,18.14728404932766],
	[95,13.557879905430191],
	[96,12.0222003194338],
	[97,7.527743748664358],
	[98,3.7125580070986235],
	[99,9.7561429229810717],
	[100,9.24510598794585],
	[101,13.491288627936356],
	[102,18.422574259759138],
	[103,22.48813237262491],
	[104,18.7617308169055],
	[105,15.200987690731651],
	[106,14.567673790440317],
	[107,14.493364129654488],
	[108,10.06862995100759],
	[109,13.792354597964184],
	[110,13.398123710429495]
];

var flotAreaData2 = [
	[0,49.331065063219285],
	[1,48.79814898366035],
	[2,50.61793547911337],
	[3,53.31696317779434],
	[4,54.78560952831719],
	[5,53.84293992505776],
	[6,54.682958355082874],
	[7,56.742547193381654],
	[8,56.99677491680908],
	[9,56.144488388681445],
	[10,56.567122269843885],
	[11,60.355022877262684],
	[12,58.7457726121753],
	[13,61.445407102315514],
	[14,61.112870581452086],
	[15,58.57202276349258],
	[16,54.72497594269612],
	[17,52.070341498681124],
	[18,51.09867716530438],
	[19,47.48185519192089],
	[20,48.57861168097493],
	[21,48.99789250679436],
	[22,53.582491800119456],
	[23,50.28407438696142],
	[24,46.24606628705599],
	[25,48.614330310543856],
	[26,51.75313497797672],
	[27,51.34463925296746],
	[28,50.217320673443936],
	[29,54.657281647073304],
	[30,52.445057217757245],
	[31,53.063914668561345],
	[32,57.07494250387825],
	[33,52.970403392565515],
	[34,48.723854145068756],
	[35,52.69064629353968],
	[36,53.590890118378205],
	[37,58.52332126105745],
	[38,55.1037709679581],
	[39,58.05347017020425],
	[40,61.350810521199946],
	[41,57.746188675088575],
	[42,60.276910973029786],
	[43,61.00841651851749],
	[44,57.786733623457636],
	[45,56.805721677811356],
	[46,58.90301959619822],
	[47,62.45091969566289],
	[48,58.75007922945926],
	[49,58.405842466185355],
	[50,56.746633122658444],
	[51,52.76631598845634],
	[52,52.3020769891715],
	[53,50.56370473325533],
	[54,55.407205992344544],
	[55,50.49825590435839],
	[56,52.4975614755482],
	[57,48.79614749316488],
	[58,47.46776704767111],
	[59,43.317880548036456],
	[60,38.96296121124144],
	[61,34.73218432559628],
	[62,31.033700732272116],
	[63,32.637987000382296],
	[64,36.89513637594264],
	[65,35.89701755609185],
	[66,32.742284578187544],
	[67,33.20516407297906],
	[68,30.82094321791933],
	[69,28.64770271525896],
	[70,28.44679026902145],
	[71,27.737654438195236],
	[72,27.755190738237744],
	[73,25.96228929938593],
	[74,24.38197394166947],
	[75,21.95038772723346],
	[76,22.08944448751686],
	[77,23.54611335622507],
	[78,27.309610481106425],
	[79,30.276849322378055],
	[80,27.25409223418214],
	[81,29.920374921780102],
	[82,25.143447932376702],
	[83,23.09444253479626],
	[84,23.79459089729409],
	[85,23.46775072519832],
	[86,27.9908486073969],
	[87,23.218855925354447],
	[88,23.9163141686872],
	[89,19.217667423877607],
	[90,15.135179958932145],
	[91,15.08666008920407],
	[92,11.006269617032526],
	[93,9.201671310476282],
	[94,7.475865090236113],
	[95,11.645754524211824],
	[96,15.76161040821357],
	[97,13.995208323029495],
	[98,12.59338056489445],
	[99,13.536707176236195],
	[100,15.01308268888571],
	[101,13.957161242832626],
	[102,13.237091619700053],
	[103,18.10178875669874],
	[104,20.634765519499563],
	[105,21.064946755449817],
	[106,25.370593801826132],
	[107,25.321453557866203],
	[108,20.947464543531186],
	[109,18.750516645477425],
	[110,15.382042945356737]
];

$(function(){
	'use strict'

	// Area Chart
	$.plot('#flotAreaGraph', [{
		data: flotAreaData1,
		color: ['#1a8e5f', '#262b31', '#434950', '#63686f', '#868a90'],
	},{
		data: flotAreaData2,
		color: '#2e323c'
	}],{
		series: {
			stack: 0,
			lines: {
				show: true,
				lineWidth: 0,
				fill: 1
			},
			shadowSize: 0
		},
		grid: {
			hoverable: true,
			clickable: true,
			borderColor: '#f0f1f7',
			borderWidth: 0,
			labelMargin: 5
		},
		yaxis: {
			max: 120,
			tickColor: '#f0f1f7',
			ticks: 8,
			font: {
				color: '#666666',
				size: 10
			}
		},
		xaxis: {
			min: 0,
			max: 100,
			tickColor: '#f0f1f7',
			font: {
				color: '#666666',
				size: 10
			}
		},
		tooltip: {
			show: true,
			shifts: {
			  x: 20,
			  y: 0
			},
			defaultTheme: false
		}
	});

});

