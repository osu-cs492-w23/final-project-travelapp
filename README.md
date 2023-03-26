# TRAVLING APP

### Team members:
[Xin Zhang](https://github.com/Jason-0118)  
[Karin Ocheretny](https://github.com/karinocheretny)  
[Jianglong Yu](https://jaron-u.github.io/)  
[Jianbo Ning](https://github.com/Jianbo-Ning) 

### Description
A traveling app that fetches data from an API to display recommended restaurants and provide navigation service with embedded GPS or use third party GPS service, like google map and apple map. This application has a search functionality that enables users to search for restaurants based on their input location. GPS service ensures users can easily locate the restaurant they are interested in visiting. Thus, search and navigation are the core features in our app. We might come up with some minor features later to make our application more attractive and have better user experience.

### App Demo GIF
driving function:  
![drivingCap](driving.gif)
restaurant function:  
![restaurantCap](restaurant.gif)


### Figma UI design
Prototype: https://www.figma.com/proto/nBM4TCHWLUT35yAk4jImS0/traveling-app?node-id=56%3A3&scaling=scale-down&page-id=0%3A1&starting-point-node-id=56%3A3

### API Service Used in this APP
Yelp API: https://docs.developer.yelp.com/reference/v3_business_search 

Google map API: https://developers.google.com/maps/documentation/javascript/directions 

### Description of Pages: 
#### Main Activity: 
There are two input boxes, one is asking users to input the origin city name, another is taking the city name they want to go. The button after the “From” input box is the function not covered in our class, which is request location permissions. Below these two input fields, there will be two options: one for driving information and the other for restaurant information. There will have more detail on the next page. Users can choose the information they want to view based on their needs. Finally, when the user clicks the submit button, they will be redirected to the specific page. Additionally, there is a settings button in the upper right corner of the main page that takes the user to the settings page.
#### Preference Setting: 
This page can store user preferences. For units, users can select between imperial and metric systems. This will be applied to the driving information page. For restaurant sorting, users can decide whether to sort by rating or by number of reviews. This will be applied to the restaurant information page.
#### Driving Info: 
This page will receive three parameters, namely start point, end point, and unit. The program will pass these parameters to an API to obtain a JSON response. We need to extract the driving time and distance from the response (this is just the basic, more related information may be obtained in the future) and display this data on the page. Additionally, we will implement a feature to redirect users to the Google Maps application, allowing them to use the navigation feature directly.
#### Restaurant Info: 
This page needs to receive two parameters, namely destination and sorting. The program will pass these parameters to an API to obtain a JSON response. We need to extract the restaurant name, rating, price, and phone number from the response and display this restaurant data on the screen using a RecycleView.

### New feature
Request location permissions https://developer.android.com/training/location/permissions 
Please see Main Activity in Description of Pages for specific descriptions


All icon in the prototype can be found in this page, https://fonts.google.com/icons 
