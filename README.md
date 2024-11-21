# Covid-19 Tracer

Covid-19 Tracer is an Android application designed to fetch and display COVID-19 data for various districts in India. The app retrieves data from a public API and presents it in a user-friendly format, helping users stay informed about the pandemic's impact in their region.

## Features

- **Fetch COVID-19 Data**: Retrieves real-time COVID-19 statistics (active, confirmed, deceased, and recovered cases) for districts in India.
- **District-Level Data**: Focuses on detailed district-wise information for better granularity.
- **User-Friendly Interface**: Displays data using a clean and simple ListView layout.
- **Asynchronous Data Loading**: Implements background tasks to fetch data without blocking the user interface.
- **Error Handling**: Provides feedback in case of data retrieval or parsing errors.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/drish1001/Covid-19-Tracer.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle files to download dependencies.
4. Build and run the app on an Android device or emulator.

## Dependencies

The project uses the following dependencies:

- `androidx.appcompat:appcompat:1.0.2`
- `androidx.constraintlayout:constraintlayout:1.1.3`
- `junit:junit:4.12`
- `androidx.test.ext:junit:1.1.0`
- `androidx.test.espresso:espresso-core:3.1.1`

These are managed in the `build.gradle` file.


## How It Works

1. **API Integration**:
   - The app fetches data from the public API endpoint:
     ```
     https://api.covid19india.org/state_district_wise.json
     ```
   - The `HttpHandler` class handles HTTP requests and responses.

2. **Data Parsing**:
   - JSON data is parsed to extract district-wise statistics for a specific state (e.g., Rajasthan).
   - The `MainActivity` class processes this data and populates it into a list.

3. **UI Display**:
   - A `ListView` displays the parsed data using a custom layout (`list_item.xml`), showing details like active, confirmed, deceased, and recovered cases for each district.

4. **Error Handling**:
   - Displays appropriate messages if there are issues with API connectivity or JSON parsing.

## Project Structure

```
Covid19_tracer/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/covid_19_tracer/
│   │   │   │   ├── HttpHandler.java  # Handles HTTP requests
│   │   │   │   ├── MainActivity.java # Main logic for fetching and displaying data
│   │   │   ├── res/
│   │   │       ├── layout/           # XML layouts (activity_main.xml, list_item.xml)
│   │   │       ├── values/           # Colors, strings, and styles
│   │   │       ├── mipmap/           # App icons
│   ├── build.gradle                  # Module-level Gradle config
├── build.gradle                      # Project-level Gradle config
├── gradle.properties                 # Gradle settings
```

## Permissions

The app requires the following permissions:

- **Internet Access**:
  ```xml
  <uses-permission android:name="android.permission.INTERNET" />
  ```
- **Network State Access**:
  ```xml
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
  ```

## Customization

You can modify the app to fetch data for other states or customize its UI by editing:

1. **API Endpoint**: Update the URL in `MainActivity.java`.
2. **UI Layouts**: Modify XML files in the `res/layout/` directory.
3. **Themes and Colors**: Customize colors in `res/values/colors.xml` and styles in `res/values/styles.xml`.


## Areas for Improvement

- The app currently focuses on Rajasthan's district-level data by default.
- No offline functionality; requires an active internet connection.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve this project.

---

## License

This project is licensed under the MIT License.


Citations : 

[1] https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/33876508/d7346fae-a678-4dbb-8489-6f4d586fa3af/paste.txt
