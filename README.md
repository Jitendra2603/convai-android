# ElevenLabs Convai Android App

A modern Android application that integrates the ElevenLabs Convai widget with full WebGL support and hardware acceleration.
https://github.com/user-attachments/assets/bcc1da3a-bbcc-40d6-83b4-938f2a8be893

## Features

- Latest Android SDK 34 support
- Full WebGL support for smooth animations
- Hardware acceleration enabled
- Modern WebView implementation
- Responsive design
- Audio permissions handling
- Transparent background support

## Requirements

- Android Studio or compatible IDE
- Android SDK 34
- Minimum Android version: 7.0 (API 24)
- Java Development Kit (JDK)

## Building

1. Clone the repository:

```bash
git clone https://github.com/Jitendra2603/convai-android.git
```
2. Add your agent ID:
   https://github.com/Jitendra2603/convai-android/blob/4ba99e8c0d8ced567a3c0c364ea6fc3b7f6cbe0f/app/src/main/java/com/elevenapp/MainActivity.java#L101
3. Open the project in Android Studio or use the provided build script:
```bash
.\build.bat
```

4. The APK will be generated at `app/build/bin/app-release.apk`

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── elevenapp/
│       │           └── MainActivity.java
│       ├── res/
│       │   └── ...
│       └── AndroidManifest.xml
└── build.bat
```

## Configuration

The app is configured to use the latest Android SDK and includes:
- Hardware acceleration
- WebGL support
- Modern web features
- Audio permissions
- Network access

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
