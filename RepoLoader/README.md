# RepoLoader :globe_with_meridians:

RepoLoader is a Android Application for downloading online repositories to local folder.

Use Initial page to search and display repositories. Then you can check the repo in browser, or start download.
Downloaded projects can be viewed from "Downloads" page.
"Notifications" page is a temporary placeholder.

## Installation

Works like any other android app. If SplashScreen doesn't show, launch from device.

Tested on:
- Android 7, 10, 12

## Used tools

- Android SplashScreen
- Usage of Material3 Guidelines, Icons, Colors
- RecyclerView to display lists

- Timber for logging

- For Requests used Hilt for DI, Retrofit2, OkHttp3, Gson

## Possible additions

Since Technical Requirements didn't mention followings, they are not yet implemented but under a consideration.
- Handling different fail states better than showing snackBar, from request errors or permission denials
- RoomDB, Datastore usage
- Shown Loader while Downloading repo is very basic, could be updated
- Deletion\Rename of downloaded files
- Paging for large sets
- Services for loading state
- Updated design, based on Figma maybe
- Open Links from custom WebView


## Screenshots

![Search Page Screenshot](https://github.com/Farad2020/kotlin_android_customs/blob/main/RepoLoader/screenshots/screen_search.png)

![Example Screenshot](https://github.com/Farad2020/kotlin_android_customs/blob/main/RepoLoader/screenshots/screen_downloads.png)
