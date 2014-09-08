# GlassCast

Live HLS Streaming proof-of-concept for Google Glass. Powered by [Kickflip](https://kickflip.io).

I recommended you connect your Glass to Wifi vs. forwarding data through the Glass mobile app.

## SECRETS.java
After you [sign up](https://kickflip.io) for a Kickflip account, copy your API keys to `SECRETS.java`:

```
$ touch ./app/src/main/java/io/kickflip/glasscast/SECRETS.java
```

```java
package io.kickflip.glasscast;
public class SECRETS {
    public static final String CLIENT_KEY = "YourKickflipKey";
    public static final String CLIENT_SECRET = "YourKickflipSecret";
}
```

## Build & Install

```
$ git clone https://github.com/OnlyInAmerica/GlassCast.git --recursive
$ ./gradlew assembleDebug
$ adb install -r ./app/build/apk/app-debug-unaligned.apk

```

## Run

GlassCast immediately starts broadcasting in response to the **Start Broadcasting** Voice Trigger.

To stop broadcasting and quit GlassClast, tap the side of your Glass.
