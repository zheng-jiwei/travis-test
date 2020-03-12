#!/bin/sh
while ! tail -20 "/tmp/server.log" | grep 'Started' > /dev/null; do sleep 1; done;
ls -la /tmp/
sleep 2
cat "/tmp/server.log"
curl "http://localhost:8080/index.html"
