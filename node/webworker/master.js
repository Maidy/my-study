var util = require('util');
var Worker = require('webworker').Worker;

var w = new Worker('./foo.js');

console.log(w);

w.onmessage = function(e) {
    util.debug('Received mesage: ' + util.inspect(e));
    w.terminate();
};

w.postMessage({ foo : 'bar' });
