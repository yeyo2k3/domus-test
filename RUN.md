# Considerations.

## Issues 
movies endpoint needs to be paged

## Technology Solution
- Lombok was select to facilitate access and create objects
- Webflux dependency was selected to optimize webclient calling using 
concurrency over movies endpoint
- Jackson-Annotations dependency was selected to map data to java classes
- Spring doc dependency was selected because springfox was deprecated and was used to document API
- Mockito was selected to implement unit test and used refactor-test dependency to verify flux results

## procedure
- implement mapping class to access data received from movies service
- implement a webClient to get data from first page when client returns data,
  total pages was captured and used flux to apply concurrency and call evey page included to obtain all data.
  after that using flux to map directors grouping and counting to filter by threshold
  at end map the list.
- validate endpoint trying to capture a numberFormatException and sent a ResponseEntity with a error in cause.