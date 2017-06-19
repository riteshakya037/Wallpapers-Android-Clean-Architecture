# Wallpapers (Clean Architecture)
Example project on how to design Android application with clean architecture, MVP, RXJava and Dagger.

## What is Clean Architecture?

In Clean, code is separated into layers in an onion shape. The outer layers of the onion depend on the inner layers but the opposite is not true. It can have an arbitrary amount of layers but for most applications there are 3 layers:

- Outer: Implementation layer
- Middle:  Presenter/Controller layer
- Inner: Business logic layer

The **implementation layer** is where everything framework specific happens. Framework specific code includes every line of code that is not solving your problem, this includes all Android stuff like creating activities and fragments, sending intents, and more general code like networking code and databases. The purpose of the **presenter/controller layer** is to act as a connector between your business logic and framework specific code.

The most important layer is the **business logic layer**. This is where you actually solve the problem you want to solve building your app. This layer does not contain any framework specific code and you should be able to run it without an emulator. This way you can have your business logic code that is easy to test, develop and maintain. **That is the main benefit of Clean Architecture.**

More general info about Clean Architecture can be found on this [blog]. This is a general explanation so let me explain how should it look like specifically in Android and how exactly do I build apps using Clean.

## Connecting all together

Basic flow of most applications is fetching data from the server and presenting to the user, lets see how it will work.

User open the view, presenter will call the repository for getting data, repository can return cached data by the policy that will be defined and/or make network call, after the data is ready it will be transferred to model and passed to the presenter, presenter will update the view and user will see the updated data.
It sound complex but you get a lot of benefits from this, it is more testable code and flexible.

The image below shows how the architecture itself is layered. The architecture is structured as an onion because each layer depends on the layer below itself. Domain layer contains his core business logic and rules. This includes interfaces on how Data is accessed from a Repository. If there were other Services to external apis, these interfaces would be here as well. The Presentation and Data Layer sit on top of this as seen in the onion, so this is why the Data layer depends on the Domain layer.

<img src="screenshots/layer-separation.png?raw=true" width="600" height="434">

So our Domain contains the interfaces to the data, on how we store and access data. These are the business rules. But the actual implementation is not important to our business. This we create the actual backbone for the application without specifying how things are done, thus enabling us to swap the outer layers with ease.

For example, a change is required in which we swap out the Comment Loaded from firebase with an in house server, whithout having to change the core implementation we can just swap out this component. Basically we are changing how the app interprets a UseCase without actually changing what it does.


## Built using Libraries

* [GSON](https://github.com/google/gson) - Java library that can be used to convert Java Objects into their JSON representation.
* [Butter Knife](https://github.com/JakeWharton/butterknife) - View Injection Library
* [Crashlytics](https://fabric.io/kits/android/crashlytics/summary) - Crash reporting solution.
* [Glide](https://github.com/bumptech/glide) - Image loading framework.
* [RxAndroid + RxJava](https://github.com/ReactiveX/RxAndroid) - Reactive programming
* [Dagger2](https://github.com/google/dagger) - Dependency Injection
* [Firebase](https://firebase.google.com/) - Specifically Database, Remote Config and Analytics.
* [Parceler](https://github.com/johncarl81/parceler) - Android Parcelable code generation.
* [CircleImageView](https://github.com/hdodenhof/CircleImageView) - Circular ImageView

## Firebase Configurations required for the project to work best.

* To include FireBase integration, you need to add your google-services.json file to the project (presentation/google-services.json) and you can use it. Go to [Console](https://console.firebase.google.com) to create a new project.
* To enable comments you need to set read write permission on Firebase Realtime Database. Just copy the lines below to Database>Rules.
```
{
  "rules": {
    "table_comments": {
      ".read": "true",
      ".write": "true"
    }
  }
}
```
* Perform A/B testing by using Remote Config. (key - on_boarding_about, value - possible values (VARIANT_A,VARIANT_B,NONE)).

## Screenshot

<img src="screenshots/screenshots.png?raw=true" width="600" height="356">

OnBoarding design based on [this](https://dribbble.com/shots/2571585-Daily-UI-023-Onboarding-Free-PSD)  

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

[![forthebadge](http://forthebadge.com/badges/built-with-love.svg)](http://forthebadge.com)

