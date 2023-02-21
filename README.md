# TraveAPP Proposal

### Team members:
#### Xin Zhang, 
#### Karin Ocheretny, 
#### Jianglong Yu, 
#### Jianbo Ning

### Description
A traveling app that fetches data from an API to display recommended restaurants and provide navigation service with embedded GPS or use third party GPS service, like google map and apple map. This application has a search functionality that enables users to search for restaurants based on their input location. GPS service ensures users can easily locate the restaurant they are interested in visiting. Thus, search and navigation are the core features in our app. We might come up with some minor features later to make our application more attractive and have better user experience.

### API Link
&ensp;Yelp API: https://docs.developer.yelp.com/reference/v3_business_search 
&emsp;BASE_URL: “https://api.yelp.com/” 
&emsp;@GET(“v3/businesses/search?”) 
&emsp;@Query(“location”): String = “place_name”.
&emsp;//@Query(“latitude”): Double = 
&emsp;//@Query(“longitude”): Double = 
&emsp;@Query(“term”): String = “restaurants” // default
&emsp;@Query(“sort_by”) : String = “review_count” or “rating”
&emsp;@Query(“limit”): Int = 20 //default
&emsp;Once you have obtained your Yelp API key, you will typically need to include it in the request headers of your API calls in order to authenticate your requests. Specifically, you will need to include an "Authorization" header in your request, with the value "Bearer <your API key>".
&emsp;If we use retrofit, we need to set a “okhttpclient” to take two headers first, one for “Authorization”, another is “accept”. Accept just set it as “application/json”
&emsp;Response:
&emsp;restaurant_name: String = response[“businesses”][0][“categories”][1][“title”]
&emsp;rating: Double = response[“businesses”][0][“rating”]
&emsp;Price: String = response[“businesses”][0][“price”]
&emsp;Phone: String = response[“businesses”][0][“phone”]

&ensp;Google map API: https://developers.google.com/maps/documentation/javascript/directions 
&emsp;BASE_URL: “https://maps.googleapis.com/”
&emsp;@GET(“maps/api/directions/json?”)
&emsp;@Query(“key”): String = your_key
&emsp;@Query(“origin”): String = “LatLng” or  “place_name”.
&emsp;@Query(“destination”): String = “LatLng” or  “place_name”.
&emsp;@Query(“units”) : String = “metric” or “imperial” 
// the google office doc is “unitSystem” but it is not working, weird. 
&emsp;Example of the full URL: https://maps.googleapis.com/maps/api/directions/json?key=Your_key&origin=Corvallis&destination=Portland&units=metric 
&emsp;Response:
&emsp;Driving_time: String = response[“routes”][0][“legs”][“duration”][“text”]
// Int type =  response[“routes”][0][“legs”][“duration”][“value”]
&emsp;Distance: String = response[“routes”][0][“legs”][“distance”][“text”]


Description how the UI will be organized, there are four page in our project: 
Main Activity: There are two input boxes, one is asking users to input the origin city name, another is taking the city name they want to go. The button after the “From” input box is the function not covered in our class, which is request location permissions. Below these two input fields, there will be two options: one for driving information and the other for restaurant information. There will have more detail on the next page. Users can choose the information they want to view based on their needs. Finally, when the user clicks the submit button, they will be redirected to the specific page. Additionally, there is a settings button in the upper right corner of the main page that takes the user to the settings page.
Preference Setting: This page can store user preferences. For units, users can select between imperial and metric systems. This will be applied to the driving information page. For restaurant sorting, users can decide whether to sort by rating or by number of reviews. This will be applied to the restaurant information page.
Driving Info: This page will receive three parameters, namely start point, end point, and unit. The program will pass these parameters to an API to obtain a JSON response. We need to extract the driving time and distance from the response (this is just the basic, more related information may be obtained in the future) and display this data on the page. Additionally, we will implement a feature to redirect users to the Google Maps application, allowing them to use the navigation feature directly.
Restaurant Info: This page needs to receive two parameters, namely destination and sorting. The program will pass these parameters to an API to obtain a JSON response. We need to extract the restaurant name, rating, price, and phone number from the response and display this restaurant data on the screen using a RecycleView.

Additional feature not covered in class
Request location permissions https://developer.android.com/training/location/permissions 
Please see 4.a for specific descriptions

Figma UI design
Prototype: https://www.figma.com/proto/nBM4TCHWLUT35yAk4jImS0/traveling-app?node-id=56%3A3&scaling=scale-down&page-id=0%3A1&starting-point-node-id=56%3A3 
All icon in the prototype can be found in this page, https://fonts.google.com/icons 

Division of labor
Main activity: Jianglong Yu
Get start city name and destination from users
Set different button to navigate to other three pages
Integrate the other three pages. 
Request permission to obtain a user's location
Implement activity lifecycle methods and the ViewModel architecture


Preference setting activity: Jianbo Ning
Unit setting
Sort setting
Store the user setting

Navigation activity: Karin Ocheretny
Fetch the data from the google map, 
Driving time and distance. This is just the basic, more related information may be obtained in the future
Display the value fetched from the google map API.
Navigation jump button. (implicit intent to launch google map app)
This active need take three parameters: startCityName, destination, unit

Restaurants list activity: Xin Zhang
Fetch the data from yelp
Received data from yelp API
Display restaurants with address, rating, price and phone number by using RecycleView
This is just the basic, more related information may be obtained in the future
Display destination name and sorted method on the top
This active need take two parameters: destination, sort_by