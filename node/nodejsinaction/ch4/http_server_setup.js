var http = require('http'),
	formidable = require('formidable');

var server = http.createServer(function(req, res) {
	switch (req.method) {
		case 'GET':
			show(req, res);
			break;

		case 'POST':
			upload(req, res);
			break;
		
		default:
			badRequest(res);
			break;
	}
});
server.listen(3000);

var notFound = function(res) {
	res.statusCode = 404;
	res.setHeader('Content-Type', 'text/plain');
	res.end('Not Found');
};


var badRequest = function(res) {
	res.statusCode = 400;
	res.setHeader('Content-Type', 'text/plain');
	res.end('Bad Request');
};

function show(req, res) {
	
	var html = '<html><body><h1>Upload form</h1>'
		+ '<form method="post" action="/" enctype="multipart/form-data">'
		+ '<p><input type="text" name="item1" /></p>'
		+ '<p><input type="text" name="item2" /></p>'
		+ '<p><input type="file" name="file1" /></p>'
		+ '<p><input type="file" name="file2" /></p>'
		+ '<p><input type="submit" value="Upload" /></p>'
		+ '</form></body></html>';

	res.writeHead({
		'Content-Length': Buffer.byteLength(html),
		'Content-Type': 'text/html; charset="utf-8"'});
	
	res.write(html);
	res.end();
};

function upload(req, res) {
	if (!isFormData(req)) {
		badRequest(res);
	}

	var form = new formidable.IncomingForm;

	form.on('field', function(field, value) {
		console.log('onfield', field, value);
	});

	form.on('file', function(field, file) {
		console.log('onfile', field, file);
	});

	form.on('end', function() {
		res.end('upload complete!');
	});

	form.on('progress', function(received, expected) {
		var p = Math.floor(received / expected * 100);
		console.log(p);
	});

	form.parse(req, function(err, fields, files) {
		/*
		console.log(fields);
		console.log(files);
		res.end('upload complete!');
		*/
	});
};

function isFormData(req) {
	var type = req.headers['content-type'] || '';
	return -1 !== type.indexOf('multipart/form-data');
};

var add = function(req, res) {
	var data = '';
	req.setEncoding('utf-8');
	req.on('data', function(chunk) { data += chunk; });
	req.on('end', function() {
		var q = querystring.parse(data);
		items.push(q.item);
		show(res);
	});
};
