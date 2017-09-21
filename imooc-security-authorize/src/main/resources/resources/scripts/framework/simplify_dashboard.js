$(function	()	{

  	//Flot Chart (Total Sales)
	var d1 = [];
	for (var i = 0; i <= 10; i += 1) {
		//d1.push([i, parseInt(Math.random() * 30)]);
		d1 = [[0,700],[1,1200],[2,1100],[3,900],[4,500],[5,700],[6,500],[7,600],[8,1200],[9,1700],[10,1200]];
	}

	function plotWithOptions() {
		$.plot("#placeholder", [d1], {
			series: {
				lines: {
					show: true,
					fill: true,
					fillColor: '#eee',
					steps: false,
					
				},
				points: { 
					show: true, 
					fill: false 
				}
			},

			grid: {
				color: '#fff',
				hoverable: true,
    			autoHighlight: true,
			},
			colors: [ '#bbb'],
		});
	}

	$("<div id='tooltip'></div>").css({
		position: "absolute",
		display: "none",
		border: "1px solid #222",
		padding: "4px",
		color: "#fff",
		"border-radius": "4px",
		"background-color": "rgb(0,0,0)",
		opacity: 0.90
	}).appendTo("body");

	$("#placeholder").bind("plothover", function (event, pos, item) {

		var str = "(" + pos.x.toFixed(2) + ", " + pos.y.toFixed(2) + ")";
		$("#hoverdata").text(str);
	
		if (item) {
			var x = item.datapoint[0],
				y = item.datapoint[1];
			
				$("#tooltip").html("Total Sales : " + y)
				.css({top: item.pageY+5, left: item.pageX+5})
				.fadeIn(200);
		} else {
			$("#tooltip").hide();
		}
	});

});
