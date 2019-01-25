var kafka = require('kafka-node');
var Producer = kafka.Producer;
var KeyedMessage = kafka.KeyedMessage;
var Client = kafka.Client;
var client = new Client('39.104.189.84:2181');
var argv = {
    topic: "pf"
};
var topic = argv.topic || 'pf';
var p = argv.p || 0;
var a = argv.a || 0;
var producer = new Producer(client, {
    requireAcks: 1
});
producer.on('ready', function() {
    var args = {
        tenantId: '2',
        staffName: 'err',
        data:[{longtitude:101,latitude:51}]
    };
	for (var i = 0;i<180;i++){
         args.data[0].longtitude = i; 
		 producer.send([{
			topic: topic,
			partition: p,
			messages: [JSON.stringify(args)],
			attributes: a
		}], function(err, result) {
			console.log(err || result);
			process.exit();
		});
	}

});