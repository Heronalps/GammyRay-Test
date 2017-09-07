var AWSXRay = require('aws-xray-sdk-core');
var AWS = AWSXRay.captureAWS(require('aws-sdk'));
var lambda = new AWS.Lambda({
    region : 'us-west-2'
});

exports.handler = (event, context, callback) => {

    var date = Date.now()

    lambda.invoke({
        FunctionName: 'xray_dest_NodeJS',
        Payload: JSON.stringify({'timestamp' : date}, null, 2)
    }, function(error, data) {
        if(error){
            context.done('error', error);
        }
        if(data.Payload){
            context.succeed(data.Payload);
        }
    });
};