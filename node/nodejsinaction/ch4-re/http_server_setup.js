var http = require('http'),
	formidable = require('formidable');

var server = http.createServer(function(req, res) {
	switch (req.method) {
	case 'GET':
		show(res);
		break;
	case 'POST':
		upload(req, res);
		break;
	default:
		badRequest(res);
		break;
	}
}).listen(3000);

function show(res) {
	var html = '<form method="post" action="/" enctype="multipart/form-data">' +
		'<p><label>name</label> <input type="text" name="name" /></p>' +
		'<p><label>file</label> <input type="file" name="file" /></p>' +
		'<p><input type="submit" value="Upload" /></p>' +
		'</form>';
	res.setHeader('Content-Type', 'text/html');
	res.setHeader('Content-Length', Buffer.byteLength(html));
	res.end(html);
};

function upload(req, res) {
	if (!isFormData(req)) {
		res.statusCode = 400;
		res.end('Bad Request: expecting multipart/form-data');
	}

	var form = new formidable.IncomingForm();

	form.on('field', function(field, value) {
//		console.log(field, value);
	});

	form.on('file', function(name, file) {
//		console.log(name, file);
	});

	form.on('end', function() {
		res.end('upload completed');
	});

	form.on('progress', function(bytesReceived, bytesExpected) {
		console.log(Math.floor(bytesReceived / bytesExpected * 100));
	});

	form.parse(req, function(err, fields, files) {
//		console.log(fields);
//		console.log(files);
	});

};

function isFormData(req) {
	var type = req.headers['content-type'] || '';
	return 0 === type.indexOf('multipart/form-data');
};

function badRequest(res) {
	res.statusCode = 400;
	res.setHeader('Content-Type', 'text/plain');
	res.end('Bad Request\n');
};

function notFound(res) {
	res.statusCode = 404;
	res.setHeader('Content-Type', 'text/plain');
	res.end('Not Found\n');
};
