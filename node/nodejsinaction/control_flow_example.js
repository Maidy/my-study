var flow = require('nimble'),
	exec = require('child_process');

function download(version, destination, callback) {
	var url = 'http://nodejs.org/dist/v' + version + '/node-v' + version + '.tar.gz';
	var path = destination + '/' + version + '.tgz';
	exec.exec('wget ' + url + ' -O ' + destination + '/' + version + '.tgz', callback);
}

flow.series([
	function(callback) {
		flow.parallel([
			function(callback) {
				console.log('Downloading 0.6.5...');
				download('0.6.5', '/tmp', callback);
			},
			function(callback) {
				console.log('Downloading 0.6.6...');
				download('0.6.6', '/tmp', callback);
			},
			function(callback) {
				console.log('Downloading 0.6.7...');
				download('0.6.7', '/tmp', callback);
			}
		], callback);
	},
	function(callback) {
		console.log('Creating archive of downloaded files...');
		exec.exec(
			'tar cvf node_distros.tar /tmp/0.4.5.tgz /tmp/0.4.6.tgz /tmp/0.4.7.tgz',
			function(err, stdout, stderr) {
				console.log('All done!');
				callback();
			});
	}
]);
