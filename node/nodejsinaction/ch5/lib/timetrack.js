var qs = require('querystring');

exports.sendHtml=function(res, html) {
    res.setHeader('Content-Type', 'text/html');
    res.setHeader('Content-Length', Buffer.byteLength(html));
    res.end(html);
};

exports.parseSentData=function(req, cb) {
    var body='';
    req.setEncoding('utf8');
    req.on('data', function(chunk) { body+=chunk; });
    req.on('end', function() {
	var data = qs.parse(body);
	cb(data);
    });
};

exports.actionForm=function(id, path, label) {
    var html = '<form method="POST" action="' + path + '">'
	+ '<input type="hidden" name="id" value="' + id + '" />'
	+ '<input type="submit" value="' + label + '" />'
	+ '</form>';
    return html;
};

exports.add=function(db, req, res) {
    exports.parseSentData(req, function(work) {
	db.run('INSERT INTO work (hours, date, description) '
	    + ' VALUES (?, ?, ?)',
	    [work.hours, work.date, work.description],
	    function(err) {
		if (err) { throw err; }
		exports.show(db, res);
	    });
    });
};

exports.remove=function(db, req, res) {
    exports.parseSentData(req, function(work) {
	db.run('DELETE FROM work WHERE id=?',
	    [work.id],
	    function(err) {
		if (err) { throw err; }
		exports.show(db, res);
	    });
    });
};

exports.archive=function(db, req, res) {
    exports.parseSentData(req, function(work) {
	db.run('UPDATE work SET archived=1 WHERE id=?',
	    [work.id],
	    function(err) {
		if (err) { throw err; }
		exports.show(db, res);
	    });
    });
};

exports.show=function(db, res, showArchived) {
    var query = 'SELECT * FROM work WHERE archived = ? ORDER BY date DESC';
    var archiveValue = (showArchived) ? 1 : 0;
    db.all(query,
	[archiveValue],
	function(err, rows) {
	    if (err) { throw err; }
	    html = (showArchived) ? '' : '<a href="/archived">Archived Work</a><br />';
	    html += exports.workHitlistHtml(rows);
//	    console.log(rows);
	    html += exports.workFormHtml();
	    exports.sendHtml(res, html);
	});
};

exports.showArchived=function(db, res) {
    exports.show(db, res, true);
};

exports.workHitlistHtml=function(rows) {
    var html = '<table>';
    for (var i in rows) {
	html += '<tr>';
	html += '<td>' + rows[i].date + '</td>';
	html += '<td>' + rows[i].hours + '</td>';
	html += '<td>' + rows[i].description + '</td>';
	if (!rows[i].archived) {
	    html += '<td>' + exports.workArchiveForm(rows[i].id) + '</td>';
	}
	html += '<td>' + exports.workDeleteForm(rows[i].id) + '</td>';
	html += '</tr>';
    }
    html += '</table>';
    return html;
};

exports.workArchiveForm=function(id) {
    return exports.actionForm(id, '/archive', 'Archive');
};

exports.workDeleteForm=function(id) {
    return exports.actionForm(id, '/delete', 'Delete');
};

exports.workFormHtml=function() {
    var html = '<form method="POST" action="/">'
	+ 'Date (YYYY-MM-DD): <input name="date" type="text" /><br />'
	+ 'Hours worked: <input name="hours" type="text" /><br />'
	+ '<p>Description:<br />'
	+ '<textarea name="description"></textarea>'
	+ '<input type="submit" value="Add" />'
	+ '</form>';
    return html;
};
