# Truec
This App will fetch the HTML content from a webpage and perform the string manipulation.

[You can find this project under my git hub repo!](https://github.com/Manuramv/Truec)

### Componets and technologies used in this app.
* Kotlin
* Coroutines
* RxJava
* ScalarsConverterFactory(for HTML parsing)
* Espresso (UI testing)
* junit


We are fetching the APIs parallely and doing some string manipulation then we will show the vlues to the texstview. APIs are independent.

* TextView for each request will be updated as soon as the processing of the corresponding request finishes


![Values rendering based on the API response](https://user-images.githubusercontent.com/31012185/76546708-f1211200-64c6-11ea-9998-1167204cec28.gif)


### Brief Explanation about app workflow
* I made activity as dump as possible, activity is not doing any business logic.
* Once the viewmodel pass the result string after each APIs success textview will show that.
* Viewmodel calls APIs parallely and don't wait for each other to pass the result back to activity.
* Coroutinescope using to perform the parallel operation.
* I've written the very basic test cases.
* This app flow like Activity -> ViewModel -> Repo -> Network call


### Note
* I wrote my idea to count the words(3rd problem) but regex is not correct.


