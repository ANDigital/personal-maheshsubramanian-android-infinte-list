# Google book list application with infinite scrolling #

A simple Android application displaying a list of books in a list view.  The list view supports
infinite scrolling.

The API supports passing in different arguments to sort the retrieved data and this is invoked in the
application using a spinner which lists the possible options.

### Application dependencies ###

* Uses volley library for network calls
* Uses Picasso library for retrieving images efficiently
* Uses espresso for UI testing (instrumentation tests)
* Does not have unit tests yet
* Version 1.0

### Setup ###

Built for Android apps running on API 19 and above.  The application runs in a portrait mode.

Can be extended to work in an optimised manner for tablets (by using a different layout file).

### Known issues ###

The application is not optimised to handle scenarios where large number of items are displayed in the list view
and hence may result in out of memory scenarios. The list view does not maintain a moving window of items.
It keeps appending and does not clear entries.

### TODO ###

* Use SLF4J library for logging
* Introduce library to support with dependency injection
* Turn the infinite scrolling list view into a reusable view (library component)
* Enable proguard
* Introduce hooks for application distribution (testfairy / hockeyapp)
* Inroduce hooks for real time application monitoring (possibly new relic)
* Many more exciting features...

### Authors ###

* Mahesh Subramanian <msubramanian@and.digital>