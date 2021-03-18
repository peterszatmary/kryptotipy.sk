# How to install SSL certificate 

- certificate for free with Lets encrypt service
- installation done via certbot
- certificate valid till 2021-03-02

## Renew

```
certbot renew --dry-run
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
certbot certonly --standalone -d kryptotipy.sk -d www.kryptotipy.sk -d de.kryptotipy.sk -d nl.kryptotipy.sk -d www.kryptotipy.com -d kryptotipy.com -d pl.kryptotipy.sk -d en.kryptotipy.sk -d hu.kryptotipy.sk -d fr.kryptotipy.sk -d es.kryptotipy.sk -d fi.kryptotipy.sk
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


