var kafka = require('kafka-node');
var events = require('events');
var emitter = new events.EventEmitter();
var Consumer = kafka.Consumer;
var Offset = kafka.Offset;
var Client = kafka.Client;
var argv = {
    topic: "pf"
};
var topic = argv.topic || 'pf';
var client = new Client('10.112.17.185:2181');
var topics = [{
        topic: topic,
        partition: 0,
        offset: 8000
    }],
    options = {
        autoCommit: false,
        fetchMaxWaitMs: 1000,
        fetchMaxBytes: 1024 * 1024,
        fromOffset: true
    };
var consumer = new Consumer(client, topics, options);
var offset = new Offset(client);
consumer.on('message', function(message) {
    var obj = message;
    var message = JSON.parse(message.value);
    var args = [];
    console.log('start');
    args.push(message);
    emitter.emit('load', args);
 
});
 
consumer.on('error', function(err) {
    console.log('error', err);
});
 
emitter.on('load', function(args) {
        console.log('listener2', args);
 
});
consumer.on('offsetOutOfRange', function(topic) {
    topic.maxNum = 2;
    offset.fetch([topic], function(err, offsets) {
        var min = Math.min.apply(null, offsets[topic.topic][topic.partition]);
        consumer.setOffset(topic.topic, topic.partition, min);
    });
});