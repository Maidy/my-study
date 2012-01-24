console.log('foo.js');

onmessage = function(e) {
    console.log('foo onmessage');
    postMessage({ test : 'this is a test' });
};

onclose = function() {
    console.log('shttuing down');
    sys.debug('Worker shuttting down.');
};
