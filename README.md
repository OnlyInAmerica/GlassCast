# GlassCast

Live HLS Streaming proof-of-concept for Google Glass. Powered by [Kickflip](https://kickflip.io).

It's reccomended to have your Glass connected to Wifi before proceeding.

## SECRETS.java
After you [sign up](https://kickflip.io) for a Kickflip account, copy your API keys to `SECRETS.java`:

```
$ touch ./app/src/main/java/io/kickflip/sample/SECRETS.java
```

```java
		package io.kickflip.sample;
		public class SECRETS {
		    public static final String CLIENT_KEY = "YourKickflipKey";
		    public static final String CLIENT_SECRET = "YourKickflipSecret";
		}
```

## Build & Install

```
$ ./gradlew assembleDebug
$ adb install -r ./app/build/apk/app-debug-unaligned.apk

```

## Run

GlassCast immediately starts broadcasting in response to the **Start Broadcasting** Voice Trigger.

To stop broadcasting and quit GlassClast, tap the side of your Glass.