#!/usr/bin/env bash

keytool -genkeypair -alias selfsigned_localhost_sslserver -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore kryptotipy-ssl-key.p12 -validity 3650