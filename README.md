This project includes `sdk` module(which project has own top level build.gradle and settings.gradle) as git submodule by following settings.gradle

```
include ':sdk'
// Resolves sdk module path
project(':sdk').projectDir = new File('android-app-sdk-sandbox/sdk')

include ':app'
```
