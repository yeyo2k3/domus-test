# Domus Back-End Developer Challenge.

## Description 

In this challenge, the REST API contains information about a collection of movie released after the year 2010, directed by acclaimed directors.  
Given the threshold value, the goal is to use the API to get the list of the names of the directors with most movies directed. Specifically, the list of names of directors with movie count strictly greater than the given threshold.   
The list of names must be returned in alphabetical order.

To access the collection of users perform HTTP GET request to:
https://challenge.iugolabs.com/api/movies/search?page=<pageNumber>
where <pageNumber> is an integer denoting the page of the results to return.

The response to such request is a JSON with the following 5 fields:

- page: The current page of the results
- per_page: The maximum number of movies returned per page.
- total: The total number of movies on all pages of the result.
- total_pages: The total number of pages with results.
- data: An array of objects containing movies returned on the requested page

Each movie record has the following schema:

- Title: title of the movie
- Year: year the movie was released
- Rated: movie rating
- Released: movie release date
- Runtime: movie duration time in minutes
- Genre: move genre
- Director: movie director
- Writer: movie writers 
- Actors: movie actors  

##  What we want you to do

We want you to clone the repository and implement a REST API Endpoint with the template you have been given.

```
/api/directors?threshold=X
```

The endpoint must return a list of the names of the directors whose number of movies directed is strictly greater than the given threshold.

The directors name in the list must be ordered in alphabetical order.


Sample : `/api/directors?threshold=4`

Json response:
```
{  "directors": ["Martin Scorsese","Woody Allen"] }
```

## Criteria

Some things we'll evaluate are:

- Correctness: The solution should return the correct result for the given threshold, the route and query parameters should be respected.
- Fail proof: The solution should handle errors and edge cases gracefully. Negative threshold values should return an empty list. Non-number thresholds should return an error message.
- Tests: The solution should have tests.
- Use of newer technologies. We prefer Webflux before a generic Rest Template.
- Intelligent solution for pagination.
- Swagger documentation and descriptions.
- Documentation: Create a .md file explaining the solution and considerations.
- Use of external libraries that facilitate things like Lombok.
- Correct use of Spring decorators such as @Service and @Autowired.

## Submission

Please fork the solution and then do a pull request to this repository. The pull request should contain the code and the documentation file.