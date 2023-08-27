# TimeAdder

`TimeAdder` is a utility that allows users to add or subtract minutes to a given 12-hour format time string (e.g., "12:00 PM"). This tool can help users quickly compute the resulting time after adding/subtracting a specific number of minutes, ensuring accuracy and efficiency.

## Features

- Support for adding/subtracting minutes to/from a 12-hour formatted time string.
- Comprehensive test coverage ensuring reliability.
- Easy integration with Gradle build tools.
- Docker support for easy setup and execution.

## Requirements

- Java 20 or higher (Developed with OpenJDK 20.0.2)
- Gradle build tool (if not using Docker)
- Docker and Docker Compose (for Docker setup)

## Compilation

To compile the project without executing tests, run:

```bash
gradle build -x test
```

## Testing

### Using Gradle

Ensure the accuracy and reliability of `TimeAdder` by running the test suite:

```bash
gradle test
```

### Using Docker

To run the tests using Docker Compose:

```bash
docker-compose up --build test
```

## Execution

### Using Gradle

To use `TimeAdder`, provide the time and the number of minutes you wish to add or subtract. The format should be:

```
<time> <minutes>
```

For instance, to subtract 1 minute from "12:00 PM", you would execute:

```bash
gradle runMain --args="12:00 PM -1"
```

### Using Docker

To run the application using Docker Compose:

```bash
docker-compose up --build app
```

## Future Improvements

- Extend support to 24-hour formatted time strings.
- Introduce a user-friendly GUI to make the utility more accessible to non-developers.
- Optimize performance for exceptionally large values of minutes.
- Implement additional time manipulations, such as adding/subtracting hours or days.

## Contributing

If you find any bugs or wish to propose new features, please open an issue or send a pull request. All contributions are welcome!

## License

This project is open-source and available under the [MIT License](LICENSE).