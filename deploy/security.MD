# How to install SSL certificate 

- certificate for free with Lets encrypt service
- installation done via certbot
- certificate valid till 2021-03-02

## Renew


### Dry

```
certbot renew --dry-run
```

### Do it

- do not forget both commands and password is in app-prod.properties file.

```
certbot renew
openssl pkcs12 -export -inkey /etc/letsencrypt/live/kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```

### Automatic renew

- check systemctl list-timers
- or check instruction on certbot site

## my own key pair self signed

```
keytool -genkeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore kryptotipy.p12 -validity 365
```

# run on server (debian 10)
```
apt install snapd
snap install core
snap install hello-world
snap install core; sudo snap refresh core
apt-get remove certbot
snap install --classic certbot
ln -s /snap/bin/certbot /usr/bin/certbot
certbot certonly --standalone -d sk.kryptotipy.sk -d kryptotipy.sk -d www.kryptotipy.sk -d de.kryptotipy.sk -d nl.kryptotipy.sk -d pl.kryptotipy.sk -d en.kryptotipy.sk -d hu.kryptotipy.sk -d fr.kryptotipy.sk -d es.kryptotipy.sk -d fi.kryptotipy.sk -d www.kryptotipy.com -d kryptotipy.com  -d sk.kryptotipy.com -d en.kryptotipy.com 
openssl pkcs12 -export -inkey /etc/letsencrypt/live/kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```

```
IMPORTANT NOTES:
 - Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/kryptotipy.sk/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/kryptotipy.sk/privkey.pem
   Your cert will expire on 2021-03-02. To obtain a new or tweaked
   version of this certificate in the future, simply run certbot
   again. To non-interactively renew *all* of your certificates, run
   "certbot renew"
 - If you like Certbot, please consider supporting our work by:

   Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
   Donating to EFF:                    https://eff.org/donate-le
```


# for more information see please
https://certbot.eff.org/lets-encrypt/debianbuster-other


# Lets encrypto problems

https://community.letsencrypt.org/t/could-not-obtain-directory-curl-error-6-could-not-resolve-host-acme-v02-api-letsencrypt-org-unknown-error/131147

curl -v https://acme-v02.api.letsencrypt.org/directory

curl: (6) Could not resolve host: acme-v02.api.letsencrypt.org

Starting Application v4.58.12 on 3pm9.c.time4vps.cloud

root@kryptotipy
root@3pm9:/home/web#

*   Trying 2606:4700:60:0:f53d:5624:85c7:3a2c...
* TCP_NODELAY set
* Expire in 149984 ms for 3 (transfer 0x5617b0a7dfb0)
* Expire in 200 ms for 4 (transfer 0x5617b0a7dfb0)
* Connected to acme-v02.api.letsencrypt.org (2606:4700:60:0:f53d:5624:85c7:3a2c) port 443 (#0)
* ALPN, offering h2
* ALPN, offering http/1.1
* successfully set certificate verify locations:
*   CAfile: none
    CApath: /etc/ssl/certs
* TLSv1.3 (OUT), TLS handshake, Client hello (1):
* TLSv1.3 (IN), TLS handshake, Server hello (2):
* TLSv1.3 (IN), TLS handshake, Encrypted Extensions (8):
* TLSv1.3 (IN), TLS handshake, Certificate (11):
* TLSv1.3 (IN), TLS handshake, CERT verify (15):
* TLSv1.3 (IN), TLS handshake, Finished (20):
* TLSv1.3 (OUT), TLS change cipher, Change cipher spec (1):
* TLSv1.3 (OUT), TLS handshake, Finished (20):
* SSL connection using TLSv1.3 / TLS_AES_256_GCM_SHA384
* ALPN, server accepted to use h2
* Server certificate:
*  subject: CN=acme-v01.api.letsencrypt.org
*  start date: Dec 17 20:26:28 2021 GMT
*  expire date: Mar 17 20:26:27 2022 GMT
*  subjectAltName: host "acme-v02.api.letsencrypt.org" matched cert's "acme-v02.api.letsencrypt.org"
*  issuer: C=US; O=Let's Encrypt; CN=R3
*  SSL certificate verify ok.
* Using HTTP2, server supports multi-use
* Connection state changed (HTTP/2 confirmed)
* Copying HTTP/2 data in stream buffer to connection buffer after upgrade: len=0
* Using Stream ID: 1 (easy handle 0x5617b0a7dfb0)
> GET /director HTTP/2
> Host: acme-v02.api.letsencrypt.org
> User-Agent: curl/7.64.0
> Accept: */*
>
* TLSv1.3 (IN), TLS handshake, Newsession Ticket (4):
* TLSv1.3 (IN), TLS handshake, Newsession Ticket (4):
* old SSL session ID is stale, removing
* Connection state changed (MAX_CONCURRENT_STREAMS == 128)!
  < HTTP/2 404
  < server: nginx
  < date: Sun, 19 Dec 2021 13:09:36 GMT
  < content-type: text/plain; charset=utf-8
  < content-length: 19
  < x-content-type-options: nosniff
  <
  404 page not found
* Connection #0 to host acme-v02.api.letsencrypt.org left intact


