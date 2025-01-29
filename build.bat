@echo off
setlocal

REM Set Android SDK path
set ANDROID_SDK=C:\Users\dawna\reminder\android_sdk
set ANDROID_PLATFORM=%ANDROID_SDK%\platforms\android-34\android.jar
set BUILD_TOOLS=%ANDROID_SDK%\build-tools\34.0.0
set PROJECT_ROOT=%CD%
set ANDROIDX_CORE=%ANDROID_SDK%\extras\androidx\androidx-core.jar
set ANDROIDX_APPCOMPAT=%ANDROID_SDK%\extras\androidx\appcompat.jar
set ANDROIDX_WEBKIT=%ANDROID_SDK%\extras\androidx\webkit.jar

REM Create necessary directories if they don't exist
if not exist "app\build\gen" mkdir app\build\gen
if not exist "app\build\obj" mkdir app\build\obj
if not exist "app\build\bin" mkdir app\build\bin
if not exist "app\libs" mkdir app\libs

REM Copy necessary support libraries if they don't exist
if not exist "app\libs\androidx-core.jar" copy "%ANDROIDX_CORE%" "app\libs\androidx-core.jar"
if not exist "app\libs\appcompat.jar" copy "%ANDROIDX_APPCOMPAT%" "app\libs\appcompat.jar"
if not exist "app\libs\webkit.jar" copy "%ANDROIDX_WEBKIT%" "app\libs\webkit.jar"

REM Generate R.java
echo Generating R.java...
call %BUILD_TOOLS%\aapt.exe package -f -m -J app\build\gen -M app\src\main\AndroidManifest.xml -S app\src\main\res -I %ANDROID_PLATFORM%

REM Wait a moment for R.java to be generated
timeout /t 2 /nobreak > nul

REM Find all Java files
echo Finding Java files...
dir /s /b app\src\main\java\com\elevenapp\*.java > sources.txt
if exist "app\build\gen\com\elevenapp\R.java" echo app\build\gen\com\elevenapp\R.java >> sources.txt

REM Compile Java files
echo Compiling Java files...
javac -d app\build\obj -cp "%ANDROID_PLATFORM%" @sources.txt

REM Create a JAR file from the class files
echo Creating JAR file...
cd app\build\obj
jar cf ..\bin\classes.jar com
cd ..\..\..

REM Create DEX file
echo Creating DEX file...
mkdir app\build\bin\dex
call %BUILD_TOOLS%\d8.bat --output app\build\bin\dex --lib %ANDROID_PLATFORM% app\build\bin\classes.jar
move app\build\bin\dex\classes.dex app\build\bin\classes.dex
rmdir app\build\bin\dex

REM Create base APK
echo Creating base APK...
call %BUILD_TOOLS%\aapt.exe package -f -M app\src\main\AndroidManifest.xml -S app\src\main\res -I %ANDROID_PLATFORM% -F app\build\bin\base.apk

REM Add DEX to APK
echo Adding classes.dex to APK...
cd app\build\bin
call %BUILD_TOOLS%\aapt.exe add base.apk classes.dex
cd ..\..\..

REM Sign APK
echo Signing APK...
call %BUILD_TOOLS%\apksigner.bat sign --ks %USERPROFILE%\.android\debug.keystore --ks-key-alias androiddebugkey --ks-pass pass:android --key-pass pass:android --out app\build\bin\app-release.apk app\build\bin\base.apk

echo Build complete! APK location: app\build\bin\app-release.apk

REM Install APK if device is connected
echo Installing APK...
call %ANDROID_SDK%\platform-tools\adb.exe install -r app\build\bin\app-release.apk

REM Clean up
cd %PROJECT_ROOT%
del sources.txt
del app\build\bin\base.apk 