var getSensors = function() {
    $.ajax({
        url: "http://localhost:5000/getSensors",
        cache: false,
        type: "GET",
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        },
        success: updateSensor
    });
}

var updateSensor = function(data) {
    data = data.replace(/\'/g, '"')
    data = JSON.parse(data)
    data.forEach(function(e) {
        console.log(e)
        $("." + e.sensorId).html(e.value);
    });
    
    setTimeout(getSensors, 1000);
}

getSensors();