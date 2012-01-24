var querystring = require('querystring');
var formidable = require('formidable');
var fs = require('fs');

var start = function(req, res) {
    console.log("Request handler 'start' was called");

    var body = '<!DOCTYPE html>' +
	'<html>'+
	'<head>'+
	'<meta http-equiv="Content-Type" content="text/html; '+
	'charset=UTF-8" />'+
	'</head>'+
	'<body>'+
	'<form action="/upload" method="post" enctype="multipart/form-data">'+
	'<input type="file" multiple="multiple" name="upload" id="upload" />'+
	'<input type="submit" value="Submit image" />'+
	'</form>'+
	'</body>'+
	'</html>';

    res.writeHead(200, {'Content-Type' : 'text/html'});
    res.write(body);
    res.end();
};

var upload = function(req, res) {
    console.log("Request handler 'upload' was called");
    console.log(req);

    var form = formidable.IncomingForm();


    var onparse = function(err, fields, files) {
	console.log('parsing done');
		
	fs.renameSync(files.upload.path, '/tmp/test.png');
	res.writeHead(200, {'Content-Type' : 'text/html'});
	res.write('received image:<br />');
	res.write('<img src="/show" />');
	res.end();
    };

    // require error handling, GET request인 경우?
    form.parse(req, onparse);
};

var show = function(req, res) {

    var onreadfile = function(error, file) {
	if (error) {
	    res.writeHead(500, {'Content-Type':'text/plain'});
	    res.write(error + '\n');
	    res.end();
	} else {
	    res.writeHead(200, {'Content-Type':'image/png'});
	    res.write(file, 'binary');
	    res.end();
	}
    };

    fs.readFile('/tmp/test.png', 'binary', onreadfile);
};

exports.start = start;
exports.upload = upload;
exports.show = show;
