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



## Affiliates

- Trezor
- https://affiliate.trezor.io/
- info@kryptotipy.sk
- C#


## Certificate Renew

```
certbot renew --dry-run
certbot renew
openssl pkcs12 -export -inkey /etc/letsencrypt/live/kryptotipy.sk/privkey.pem -in /etc/letsencrypt/live/kryptotipy.sk/fullchain.pem -CAfile letsencryptauthorityx1.pem -out cert.p12
```

## AHrefs
- finally will not follow nofollow links. So my clicks stats from page would be from 10.11.2021 accurate.

## Migration from UA (Universal analytics) to GA4 (Google analytics)
- [video guide](https://www.youtube.com/watch?v=_8rFUr_NDB0&ab_channel=AnalyticsMania-GoogleTagManager%26Analytics)
- deadline 1.7.2023
- 14 months of statistics


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

