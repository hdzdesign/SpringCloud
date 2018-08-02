var express = require('express');
var app = express();
app.get('/welcome', function(req, res){
            res.send('Hello World!');

});
app.get("/health", (req, res)=>res.status(200));
app.listen(3000, function(){
        console.log('Example app listening on port 3000!');
});





