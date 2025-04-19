@echo off

set jb_fqdn=localhost
set jb_port=19080

set jb_ctx1=Context1
set jb_ctx2=Context2

: curl must be downloaded from curl.se or it may be crippled
: (missing http2 support)

curl -v --http2 -H "X-Forwarded-Proto: https" -H "X-Jersey-Issue-ID: 5878" ^
  http://%jb_fqdn%:%jb_port%/issue/jersey-5878/%jb_ctx1%/%jb_ctx2%

echo ""
