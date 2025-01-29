# ElevenLabs Convai Android App

A modern Android application that integrates the ElevenLabs Convai widget with full WebGL support and hardware acceleration.

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

2. Open the project in Android Studio or use the provided build script:
```bash
.\build.bat
```

3. The APK will be generated at `app/build/bin/app-release.apk`

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

[Add your preferred license]

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
