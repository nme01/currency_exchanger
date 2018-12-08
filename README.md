# Currency exchanger
Server providing REST API for currency exchanging.

## Building
To run the application navigate to the root directory of the project and run ./gradlew build

## Running
Application doesn't require any application server to run (Tomcat is built-in).

To run the application navigate to the root directory of the project and run ./gradlew bootRun

## API
To have the amount in one currency calculated into another currency one needs to send the following request:
```
GET [serverAddress]/exchange?amount=[amount]&sourceCurrency=[sourceCurrency]&targetCurrency=[targetCurrency]
```

Where:
- `serverAddress` - address of the server that you run the application on
- `amount`- amount of money that should be converted
- `sourceCurrency` - currency of the amount being converted
- `targetCurrency`- destination currency
