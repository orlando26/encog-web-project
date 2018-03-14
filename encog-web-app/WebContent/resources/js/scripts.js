var errors = [];
$(document).ready(function(){
	
	
	$('.btn').click(function(){
		var btn = $(this).val();
		console.log(btn);
		$.get('EncogServlet', {
			btnName : btn
		}, function(response){
			
			console.log(response);
			errors = response;
			plotPattern()
		});
		
	});
		
	
	
	
});

function plotPattern() {
   
    var trace1 = {
        y: errors,
        type: 'scatter'
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

    Plotly.newPlot('plot', data, layout);
}