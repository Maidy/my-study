var mongodb = require('mongodb'),
	server = new mongodb.Server('127.0.0.1', 27017, {});

var client = new mongodb.Db('mtest', server);

client.open(function(err) {
	client.collection('test_insert', function(err, collection) {
		if (err) { throw err; }
		console.log('We are now able to perform queries');

		collection.insert({a:2}, {safe:true}, function(err, documents) {
			if (err) { throw err; }
			console.log('Document ID is: ' + documents[0]._id);
			// client.close();
		});

		collection.find({a:2}).toArray(function(err, results) {
			if (err) { throw err; }
			console.log(results);
		});

		var _id = new client.bson_serializer.ObjectID('4f34954c59a10e7119000001');
		collection.remove({_id:_id}, {safe:true}, function(err) {
			if (err) { throw err; }
			console.log('Delete!.');
		});
	});
});
