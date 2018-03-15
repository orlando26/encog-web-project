var errors = [];
var ideal = [];
var predicted = [];
$(document).ready(function(){


	$('.btn').click(function(){
		var btn = $(this).val();
		console.log(btn);
		$.get('EncogServlet', {
			btnName : btn
		}, function(response){
			errors = [];
			ideal = [];
			predicted = [];
			console.log(response);
			if(btn == 'Train'){
				errors = response;
				plotErrors();
			}else{
				$.each(response, function(i, obj){
					ideal.push(obj.ideal);
					predicted.push(obj.predicted);
				});
				plotExperiments()
			}

		});

	});




});

function plotErrors() {

	var trace1 = {
			y: errors,
			type: 'lines+markers'
	};

	var data = [trace1];

	var layout = {
			title: 'Errors',
			xaxis: {
				title: 'epoch'
			},
			yaxis: {
				title: 'Error'
			}
	}

	Plotly.newPlot('plotError', data, layout);
}

function plotExperiments() {

	var trace1 = {
			y: ideal,
			type: 'scatter',
			name: 'ideal'
	};

	var trace2 = {
			y: predicted,
			type: 'scatter',
			name: 'predicted'
	};

	var data = [trace1, trace2];

	var layout = {
			title: 'Experiments',
			xaxis: {
				title: 'Evaluation'
			},
			yaxis: {
				title: 'MPG'
			}
	}

	Plotly.newPlot('plotExperiments', data, layout);
}