#!/usr/bin/env /usr/bin/bash


jb_fqdn=localhost
jb_port=19080

jb_ctx1=Context1
jb_ctx2=Context2

curl -v --http2 -H "X-Forwarded-Proto: https" -H "X-Jersey-Issue-ID: 5878" \
  http://${jb_fqdn}:${jb_port}/issue/jersey-5878/${jb_ctx1}/${jb_ctx2}

echo ""
