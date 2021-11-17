# kryptotipy.sk web

- Spring Boot
- Maria DB
- thymeleaf
- websupport DNS
- time4vps hosting
- https://app.bitly.com/

## Affiliates

- Trezor
- https://affiliate.trezor.io/
- info@kryptotipy.sk
- C#

# Spolupracu neskor rozsir

- ak chcete rovnaky agregator 
    - web + zakladne sluzby enginu (4 slovenske + 4 ceske zdroje) 2000 e
    - krajina ( 100 e )
    - stranka krajiny ( 50 e )


## Certificate Renew

```
certbot renew --dry-run
certbot renew
openssl pkcs12 -export -inkey /etc/letsencrypt/live/kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```