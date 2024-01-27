# Kryptotipy.sk web

- Spring Boot 3.0.2
- Java 17
- Maria DB
- Thymeleaf
- Websupport DNS
- Time4vps hosting
- https://app.bitly.com/

## Sources behaviour on the page

### Approved sources

- approved sources have green flag on each post.
- approved means now also have **direct redirect** to their original pages.
- TODO this bind between approved + redirect to original is not good. split it into two logics


### Link to post

- link to post is calculated from ID from DB.
- that the reason why this is not testable on other machines, local ...
- calculated to not be stored. storage optimalization
- if it will be stored in storage DB it will be testable on other machines too

## Affiliates

- Trezor
- https://affiliate.trezor.io/
- info@kryptotipy.sk
- C#


## Certificate Renew

```
certbot renew --dry-run
certbot renew
openssl pkcs12 -export -inkey /etc/letsencrypt/live/sk.kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/sk.kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```

## AHrefs
- finally will not follow nofollow links. So my clicks stats from page would be from 10.11.2021 accurate.

## Migration from UA (Universal analytics) to GA4 (Google analytics)
- [video guide](https://www.youtube.com/watch?v=_8rFUr_NDB0&ab_channel=AnalyticsMania-GoogleTagManager%26Analytics)
- deadline 1.7.2023
- 14 months of statistics
- more months in BigQuery


## Sitemap

- sitemap is generated
- not working if there is https://kryptotipy.sk
- working with www. en. pl. sk. and so on.

```xml
<urlset xmlns:ns2="http://www.sitemaps.org/schemas/sitemap/0.9">
<url>
<loc>https://en.kryptotipy.sk/</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>1.0</priority>
</url>
<url>
<loc>https://en.kryptotipy.sk/posts/neo</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>0.8</priority>
</url>
<url>
<loc>https://en.kryptotipy.sk/posts/litecoin</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>0.8</priority>
</url>
<url>
<loc>https://en.kryptotipy.sk/posts/polkadot</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>0.8</priority>
</url>
<url>
<loc>https://en.kryptotipy.sk/posts/ethereum</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>0.8</priority>
</url>
<url>
<loc>https://en.kryptotipy.sk/posts/bitcoin</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>0.8</priority>
</url>
<url>
<loc>https://en.kryptotipy.sk/posts/kusama</loc>
<lastMod>2023-02-14T07:24:37.947+01:00</lastMod>
<priority>0.8</priority>
</url>
```

# Spolupracu neskor rozsir

- agregator sa predavat nebude
- data cez rest alebo graphQL. s tym, ze max viem vratit 100 clankov na request.
- aj requesty je potrebne pocitat a zapisovat a spoplatnovat. cize cela infrastruktura.


## Catalogs

- e-katalog.sk
- https://www.naj-katalog.eu/
- https://www.davaj.sk/
- https://www.pozri.sk/
- http://vsetko.info/
- http://webdir.sk/
- https://katalogstranok.sk/
- https://webkatalog.xyz/

### Content for catalogs

- Agregátor najlepších článkov z celého sveta o kryptomenách na jednom mieste.
- Agregátor najlepších článkov z celého sveta o kryptomenách na jednom mieste. Na našich stránkach nájdete okrem článkov aj odporúčané externé krypto služby, slovník vysvetľujúci základy krypto sveta a rubriku vedeli ste, že.
- kryptomeny, bitcoin, články, agregátor, kryptotipy, investície, financie, ethereum, neo, monero, litecoin
- https://www.kryptotipy.sk
- info@kryptotipy.sk
- Kryptotipy
- https://www.facebook.com/kryptotipysk/


## Security

### How to install SSL certificate

- certificate for free with Lets encrypt service
- installation done via certbot
- certificate valid till 2021-03-02

#### Renew

Stop the application and run

```
certbot renew
openssl pkcs12 -export -inkey /etc/letsencrypt/live/sk.kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/sk.kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```

if you would like to try

```
certbot renew --dry-run
```


#### Automatic renew (TODO)

- check systemctl list-timers
- or check instruction on certbot site

### my own key pair self signed

```
keytool -genkeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore kryptotipy.p12 -validity 365
```

### run on server (debian 10)
```
apt install snapd
snap install core
snap install hello-world
snap install core; sudo snap refresh core
apt-get remove certbot
snap install --classic certbot
ln -s /snap/bin/certbot /usr/bin/certbot
certbot certonly --standalone -d sk.kryptotipy.sk -d kryptotipy.sk -d www.kryptotipy.sk -d de.kryptotipy.sk -d nl.kryptotipy.sk -d pl.kryptotipy.sk -d en.kryptotipy.sk -d hu.kryptotipy.sk -d fr.kryptotipy.sk -d es.kryptotipy.sk -d fi.kryptotipy.sk  
openssl pkcs12 -export -inkey /etc/letsencrypt/live/sk.kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/sk.kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```

```
IMPORTANT NOTES:
 - Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/sk.kryptotipy.sk/fullchain.pem
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


### For more information see please
https://certbot.eff.org/lets-encrypt/debianbuster-other
https://certbot.eff.org/instructions?ws=other&os=debianbuster



## Testing multi-extraction (more extraction in one time)

```
13.12.2021 ->>>>>>>>>>

- I was trying  mysqlcheck

- multi threading to one thread extraction
  mysqlcheck --all-databases -u root -p -o

- it helped
- but in few days problems came back
- I restarted server but mysql was slow
- repair again and it helped!!!
- now I am trying again I deleted thread pools from post-extractor (maybe the problem)

- spring.datasource.hikari.minimum-idle= 11
- spring.datasource.hikari.maximum-pool-size= 13

- will see in few days today is (13.12) for sure the issue is database but what causes databases running into troubles?

- mentioned conf
- low CPU or RAM ?


there is a lot of data but database is able to work relatively fine but after few days it is horrible.
```

### QUICK FIX

```
service mysql restart
mysqlcheck --all-databases -u root -p -o
```


IT SEEMS THAT I NEED NEW  DATABASE

- overloaded cpu

-restarts
- 17.12.2021 00:00


### Server memory

```
ps -o pid,user,%mem,command ax | sort -b -k3 -r | less
pmap <NUM>
```

big anon - write to support
diff between container and KVL instances



### TODO

- canonical
    - maybe not possible when /news but actually already f.e. czech news (not present in url). but there
      are just few 9 not working canonicals from all.


- bugs
- java.io.IOException: Duplicate accept detected. This is a known OS bug. Please consider reporting that you are affected: https://bugs.launchpad.net/ubuntu/+source/linux/+bug/1924298


