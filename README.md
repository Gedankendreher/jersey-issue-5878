# Jersey Issue 5878

Start the app and run the `send-request.sh` script. Sometimes it works
(HTTP status code 200 and output from the requested resource). Sometimes
you get the response but status 500. In this case, the NPE triggered
during cleanup phase of the request processing.