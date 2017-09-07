exports.handler = (event, context, callback) => {
    var time_diff = Date.now() - event['timestamp']
    callback(null, time_diff);
};